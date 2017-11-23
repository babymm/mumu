package com.lovecws.mumu.common.core.log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author babymm
 * @version 1.0-SNAPSHOT
 * @Description: 日志记录
 * @date 2017-11-22 10:34
 */
public abstract class MumuLogComponent {
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    /**
     * 日志记录
     *
     * @param joinPoint
     * @param mumulog   是否记录一些特定的日志
     * @return
     * @throws Throwable
     */
    public Object log(ProceedingJoinPoint joinPoint, boolean mumulog) throws Throwable {
        String name = "";
        String operater = "";
        if (mumulog) {
            Method requestMethod = getMethod(joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), joinPoint.getArgs());
            MumuLog mumuLogAnnotation = requestMethod.getAnnotation(MumuLog.class);
            if (mumuLogAnnotation == null || !mumuLogAnnotation.required()) {
                return joinPoint.proceed();
            }
            name = mumuLogAnnotation.name();
            operater = mumuLogAnnotation.operater();
        }
        Signature signature = joinPoint.getSignature();
        String typename = signature.getDeclaringTypeName();
        String method = signature.getName();

        String parameter = StringUtils.join(joinPoint.getArgs(), ",");

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        String result = gson.toJson(proceed);

        long end = System.currentTimeMillis();
        String usetime = (end - start) + "ms";

        record(new MumuLogEntity(typename, method, parameter, result, usetime, new Date(), name, operater));
        return proceed;
    }

    public abstract void record(MumuLogEntity mumuLogEntity);

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
}
