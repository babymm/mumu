package com.lovecws.mumu.common.core.log;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/4/24.
 * 记录日志（将一些重要的操作记录到日志里面）
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MumuLog {
    /**
     * 日志名称
     * @return
     */
    public String name() default "";

    /**
     * 是否记录日志
     * @return
     */
    public boolean required() default true;

    /**
     * 日志请求方式
     * @return
     */
    public String operater() default "POST";
}
