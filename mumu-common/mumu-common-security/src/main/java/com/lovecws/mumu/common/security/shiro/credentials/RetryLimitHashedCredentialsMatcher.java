package com.lovecws.mumu.common.security.shiro.credentials;

import com.lovecws.mumu.common.redis.JedisClient;
import com.lovecws.mumu.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @desc 自定义的市容凭证匹配器 
 * @author ganliang
 * @version 2016年8月29日 上午10:44:49
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	@Autowired
	private JedisClient jedisClient;

	@Autowired(required = false)
	private LoginCredentialsHandler loginCredentialsHandler;
	/**
	 * 做认证匹配
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		//获取缓存key
		String loginName=(String) token.getPrincipal();
		String cacheName=getCacheName(loginName);
		// retry count + 1
		String retryCount=jedisClient.get(cacheName);
		if (retryCount == null) {
			//缓存两小时
			jedisClient.incr(cacheName);
			jedisClient.expire(cacheName,60*60*2);
		}else{
			int counter=Integer.parseInt(retryCount);
			if(counter<5){
				jedisClient.incr(cacheName);
			}else{
				throw new ExcessiveAttemptsException();
			}
		}
		loginCredentialsHandler.before();
		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// clear retry count
			jedisClient.del(cacheName);

			//用户认证成功之后 进行相关操作
			loginCredentialsHandler.after();
		}else{
			SysUser unloginUser=new SysUser();
			unloginUser.setUserName(loginName);
			unloginUser.setPassword(token.getCredentials().toString());
			SecurityUtils.getSubject().getSession(true).setAttribute(SysUser.SYS_USER, unloginUser);
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
