package com.lovecws.mumu.common.security.shiro.exception;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ajax请求异常
 * @desc
 * @author ganliang
 * @version 2016年11月4日 下午11:38:20
 */
public class GlobalHandlerException extends SimpleMappingExceptionResolver{
	private static final Logger log=Logger.getLogger(GlobalHandlerException.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//String requestedWith = request.getHeader("X-Requested-With");
		//如果是ajax异步请求
		/*if(requestedWith!=null&&requestedWith.equals("XMLHttpRequest")){
			try {
				WebUtils.issueRedirect(request, response, "/portal/account/logining");
			} catch (IOException e) {
				//
			}
		}*/
		log.error(ex);
		return super.resolveException(request, response, handler, ex);
	}

}
