package com.lovecws.mumu.common.core.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MumuCore {

	/**
	 * 方法模块内码
	 * @return
	 */
	public String code() default "";
	
	/**
	 * 方法模块名称
	 * @return
	 */
	public String name() default "";
}
