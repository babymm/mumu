package com.lovecws.mumu.common.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @desc spring WebApplicationContext工具类
 * @author ganliang
 * @version 2016年9月27日 上午11:51:37
 */
@Component
public class WebApplicationContextUtil implements ApplicationContextAware{

	// spring的容器上下文
    private static ApplicationContext applicationContext;
    
	/**
	 * 获取bean 实体
	 * @param clazz 字节码对象
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz){
		return applicationContext.getBean(clazz);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 WebApplicationContextUtil.applicationContext = applicationContext;
	}
}
