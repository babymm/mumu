package com.lovecws.mumu.common.security.shiro.filter;

import com.lovecws.mumu.common.core.utils.WebUtil;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @desc 自定义form表单认证过滤器
 * @author ganliang
 * @version 2016年8月29日 上午10:52:13
 */
public class RcFormAuthenticationFilter extends FormAuthenticationFilter {
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);

		if(getLoginUrl().equals(httpServletRequest.getServletPath())&&"get".equalsIgnoreCase(httpServletRequest.getMethod())){
			return true;
		}
		if (request.getAttribute(getFailureKeyAttribute()) != null) {
			return true;
		}
		request.setAttribute("shiroLoginFailure", "用户未登录");
		return super.onAccessDenied(request, response, mappedValue);
	}
}
