package com.lovecws.mumu.system.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lovecws.mumu.common.core.log.MumuLogComponent;
import com.lovecws.mumu.common.core.log.MumuLogEntity;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @desc 全局日志记录 打印日志记录
 */
@Component
@Aspect
public class SysLogConsoleAspect extends MumuLogComponent {

    private static final Logger log = Logger.getLogger(SysLogConsoleAspect.class);
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Pointcut("execution(* com.lovecws.mumu.system.controller.*.*.*Controller.*(..))")
    public void SysLogConsoleAspect(){
    }

    @Override
    public void record(final MumuLogEntity mumuLogEntity) {
        System.out.println(mumuLogEntity);
    }

    @Around("SysLogConsoleAspect()")
    public Object globalAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return log(joinPoint, false);
    }
}
