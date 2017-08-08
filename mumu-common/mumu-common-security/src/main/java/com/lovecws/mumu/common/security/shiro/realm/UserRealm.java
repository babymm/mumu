package com.lovecws.mumu.common.security.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @desc 自定义realm
 * @author ganliang
 * @version 2016年8月29日 上午11:11:00
 */
public class UserRealm extends AuthorizingRealm {

	public static final String authorizationInfoKey="mumu:cms:permissions:";
	/**
	 * 获取当前用户的角色集合,权限集合
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取缓存key
		byte[] authorizationInfoKeyBytes=(authorizationInfoKey+SecurityUtils.getSubject().getPrincipal().toString()).getBytes();
		SimpleAuthorizationInfo authorizationInfo= null;
		return authorizationInfo;
	}

	/**
	 * 校验登录用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String loginName = (String) token.getPrincipal();

		throw new UnknownAccountException();
	}
}
