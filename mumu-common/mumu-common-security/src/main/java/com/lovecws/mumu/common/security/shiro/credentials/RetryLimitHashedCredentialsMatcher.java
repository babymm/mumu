package com.lovecws.mumu.common.security.shiro.credentials;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @desc 自定义的市容凭证匹配器 
 * @author ganliang
 * @version 2016年8月29日 上午10:44:49
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	/**
	 * 做认证匹配
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		//获取缓存key
		String loginName=(String) token.getPrincipal();
		String cacheName=getCacheName(loginName);

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {

		}
		return matches;
	}

	private String getCacheName(String username){
		return keyPrefix+username;
	}
	private String keyPrefix;

	public String getKeyPrefix() {
		return keyPrefix;
	}


	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
}
