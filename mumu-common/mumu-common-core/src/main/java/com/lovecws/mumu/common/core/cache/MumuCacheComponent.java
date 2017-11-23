
package com.lovecws.mumu.common.core.cache;

import com.lovecws.mumu.common.core.serialize.JavaSerializeUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import javax.swing.text.Document;
import java.lang.reflect.Method;

public abstract class MumuCacheComponent {

    private static final Logger log = Logger.getLogger(Document.class);
    private static volatile boolean CACHE_UNUSERD = false;
    private static volatile long CACHE_UNUSERD_TIME = 0;

    public Object cache(ProceedingJoinPoint joinPoint) {
        try {
            if (CACHE_UNUSERD) {
                //缓存熔断一个小时之后 重试
                if (System.currentTimeMillis() - CACHE_UNUSERD_TIME < 1 * 60 * 60 * 1000) {
                    return joinPoint.proceed();
                }
            }
            Signature signature = joinPoint.getSignature();
            Object[] args = joinPoint.getArgs();
            //获取请求方法
            Method requestMethod = getMethod(joinPoint.getTarget().getClass(), signature.getName(), args);

            Object proceed = null;
            //缓存注解
            MumuCache modularCacheAnnotation = requestMethod.getAnnotation(MumuCache.class);
            if (modularCacheAnnotation != null && modularCacheAnnotation.cache()) {
                proceed = handler(joinPoint, modularCacheAnnotation, args);
            }
            //用户未设置缓存 直接请求接口
            else {
                proceed = joinPoint.proceed();
            }
            return proceed;
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


    /**
     * 处理缓存数据
     *
     * @param joinPoint              spring连接点
     * @param modularCacheAnnotation 请求方法缓存注解
     * @param args
     * @return
     * @throws Throwable
     */
    public Object handler(ProceedingJoinPoint joinPoint, MumuCache modularCacheAnnotation, Object[] args) throws Throwable {
        byte[] cacheKey = getCacheKey(modularCacheAnnotation, args);
        //启动redis熔断机制【当redis服务器不可用时 暂停缓存服务】
        byte[] cacheKeyBytes = null;
        try {
            cacheKeyBytes = getCache(cacheKey);
        } catch (Exception e) {
            log.error(e);
            CACHE_UNUSERD = true;
            CACHE_UNUSERD_TIME = System.currentTimeMillis();
            return joinPoint.proceed();
        }
        Object proceed = null;
        //如果缓存存在 则删除缓存；如果缓存不存在 则使用缓存
        if (modularCacheAnnotation.expire() == 0) {
            deleteCache(cacheKey);
            return joinPoint.proceed();
        } else {
            //缓存内容为空 则缓存对象
            if (cacheKeyBytes == null) {
                proceed = joinPoint.proceed();
                cache(cacheKey, JavaSerializeUtil.serialize(proceed), modularCacheAnnotation.expire());
            }
            //缓存不为空 则获取缓存对象
            else {
                try {
                    proceed = JavaSerializeUtil.deserialize(cacheKeyBytes);
                } catch (RuntimeException e) {
                    //当缓存的类对象 和当前的对象不匹配的时候，删除缓存（通常是缓存之后，修改了类属性）
                    deleteCache(cacheKey);
                    proceed = joinPoint.proceed();
                }
                log.info("cache[" + new String(cacheKey) + "] hit..........");
            }
        }
        return proceed;
    }

    /**
     * 设置缓存
     *
     * @param cacheKey
     */
    public abstract void cache(byte[] cacheKey, byte[] bytes, int expire);

    /**
     * 获取缓存
     *
     * @param cacheKey
     */
    public abstract byte[] getCache(byte[] cacheKey);

    /**
     * 删除缓存
     *
     * @param cacheKey
     */
    public abstract void deleteCache(byte[] cacheKey);


    /**
     * 获取请求的方法
     *
     * @param clazz      字节码
     * @param methodName 方法名称
     * @param args       方法参数数组
     * @return
     */
    public Method getMethod(Class<? extends Object> clazz, String methodName, Object[] args) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            method.setAccessible(true);
            if (method.getName().equals(methodName) && method.getParameters().length == args.length) {
                return method;
            }
        }
        return null;
    }

    /**
     * 获取缓存对象key
     *
     * @param modularCacheAnnotation
     * @param args
     * @return
     */
    private byte[] getCacheKey(MumuCache modularCacheAnnotation, Object[] args) {
        //缓存前缀
        String cacheKey = modularCacheAnnotation.prefix();
        if (!cacheKey.endsWith(":")) {
            cacheKey = cacheKey + ":";
        }
        //缓存参数
        int[] orders = modularCacheAnnotation.order();
        if (args.length > 0 && orders.length > 0) {
            for (int order : orders) {
                if (order >= args.length) {
                    order = 0;
                }
                Object arg = args[order];
                if (arg == null) {
                    arg = "";
                }
                cacheKey = cacheKey + arg + ":";
            }
        }
        //去除最后的:号
        if (cacheKey.endsWith(":")) {
            int lastIndexOf = cacheKey.lastIndexOf(":");
            cacheKey = cacheKey.substring(0, lastIndexOf);
        }
        return cacheKey.getBytes();
    }
}
