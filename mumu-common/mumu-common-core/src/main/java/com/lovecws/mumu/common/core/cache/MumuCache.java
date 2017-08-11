package com.lovecws.mumu.common.core.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模块是否缓存注解
 * @author ganliang
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MumuCache {

	/**
	 * 是否缓存 ;默认缓存
	 * @return
	 */
	public boolean cache() default true;
	
	/**
	 * 缓存的key前缀
	 * @return
	 */
	public String prefix();
	
	/**
	 * 缓存的key参数在接口请求参数的排序(以0计数)
	 * @return
	 */
	public int[] order() default {0};
	
	/**
	 * -1 默认永不过期
	 * >0 设置缓存时间
	 * 0 设置删除缓存
	 * @return
	 */
	public int expire() default -1;
}
