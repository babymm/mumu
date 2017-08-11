package com.lovecws.mumu.common.core.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模块接口访问限制
 * @author ganliang
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MumuLimit {

	/**
	 * 模块限制
	 * @return
	 */
	public boolean limit() default true;
	
	/**
	 * 前缀
	 * @return
	 */
	public String prefix();
	
	/**
	 * 缓存的key参数在接口请求参数的排序(以0计数)
	 * @return
	 */
	public int[] order() default {0};
	
	/**
	 * 限制次数
	 * @return
	 */
	public int count() default 3;
	
	/**
	 * 过期时间 默认3600s
	 * @return
	 */
	public int expire() default 3600;
}
