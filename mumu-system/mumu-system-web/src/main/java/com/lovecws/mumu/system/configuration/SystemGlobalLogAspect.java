package com.lovecws.mumu.system.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @desc 全局日志记录
 */
@Component
@Aspect
public class SystemGlobalLogAspect {

    private static final Logger log = Logger.getLogger(SystemGlobalLogAspect.class);
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Pointcut("execution(* com.lovecws.mumu.system.controller.*.*.*Controller.*(..))")
    public void globalPointcut(){

    }

    @Around("globalPointcut()")
    public Object globalAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        String method = signature.getDeclaringTypeName()+" "+signature.getName();
        log.info("method{"+method+"}");

        String parameter= StringUtils.join(joinPoint.getArgs(),",");
        log.info("parameter{"+parameter+"}");

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        String result=gson.toJson(proceed);
        log.info("result{"+result+"}");
        long end = System.currentTimeMillis();

        String usetime=(end - start) + "ms";
        log.info("usetime{"+usetime+"}");
        return proceed;
    }
}
