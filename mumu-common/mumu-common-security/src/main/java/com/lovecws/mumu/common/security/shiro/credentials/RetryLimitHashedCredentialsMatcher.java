package com.lovecws.mumu.common.security.shiro.credentials;

import com.lovecws.mumu.common.core.utils.ValidateUtils;
import com.lovecws.mumu.common.redis.JedisClient;
import com.lovecws.mumu.system.entity.SysUser;
import com.lovecws.mumu.system.service.SysUserService;
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

	@Autowired(required = false)
	private SysUserService userService;
	@Autowired
	private JedisClient jedisClient;

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
		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// clear retry count
			jedisClient.del(cacheName);
			//从数据库获取用户信息 将用户信息保存到session中
			/*List<SysUser> users =null;
			if(ValidateUtils.isEmail(loginName)){
				users = userService.querySysUserByCondition(null, null, loginName, null, null);
			}else if(ValidateUtils.isMobile(loginName)){
				users = userService.querySysUserByCondition(null, null, null, loginName, null);
			}else{
				users = userService.querySysUserByCondition(loginName, null, null, null, null);
			}
			if(users!=null&&users.size()==1){
				SecurityUtils.getSubject().getSession().setAttribute(SysUser.SYS_USER, users.get(0));
			}else{
				throw new IllegalArgumentException();
			}*/
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
