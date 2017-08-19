package com.lovecws.mumu.common.security.shiro.realm;

import com.alibaba.fastjson.JSON;
import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.utils.DateUtils;
import com.lovecws.mumu.common.core.utils.StringUtil;
import com.lovecws.mumu.common.core.utils.ValidateUtils;
import com.lovecws.mumu.common.security.shiro.exception.AccountUnActiveException;
import com.lovecws.mumu.system.entity.SysPermission;
import com.lovecws.mumu.system.entity.SysRole;
import com.lovecws.mumu.system.entity.SysUser;
import com.lovecws.mumu.system.service.SysPermissionService;
import com.lovecws.mumu.system.service.SysRoleService;
import com.lovecws.mumu.system.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @desc 自定义realm
 * @author ganliang
 * @version 2016年8月29日 上午11:11:00
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired(required = false)
	private SysUserService userService;
	@Autowired(required = false)
	private SysRoleService roleService;
	@Autowired(required = false)
	private SysPermissionService permissionService;

	/**
	 * 获取当前用户的角色集合,权限集合
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//获取保存在session中的用户信息
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(SysUser.SYS_USER);
		if (user == null) {
			throw new IllegalArgumentException();
		}
		//获取当前用户拥有的所有角色
		List<SysRole> roles = roleService.getSysRoleByUserId(user.getUserId().toString(), PublicEnum.NORMAL.value());
		for (SysRole sysRole : roles) {
			authorizationInfo.addRole(sysRole.getRoleCode());
		}
		//获取当前用户拥有的所有权限
		List<SysPermission> permissions = permissionService.getSysPermissionByUserId(user.getUserId(), PublicEnum.NORMAL.value());
		for (SysPermission sysPermission : permissions) {
			authorizationInfo.addStringPermission(sysPermission.getPermission());
		}
		System.out.println("用户权限:"+ JSON.toJSONString(authorizationInfo));
		return authorizationInfo;
	}

	/**
	 * 校验登录用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String loginName = (String) token.getPrincipal();
		if (StringUtil.isEmpty(loginName)) {
			throw new UnknownAccountException();// 没找到帐号
		}
		//从数据库获取用户信息
		List<SysUser> users =null;
		if(ValidateUtils.isEmail(loginName)){
			users = userService.querySysUserByCondition(null, null, loginName, null, null);
		}else if(ValidateUtils.isMobile(loginName)){
			users = userService.querySysUserByCondition(null, null, null, loginName, null);
		}else{
			users = userService.querySysUserByCondition(loginName, null, null, null, null);
		}
		//用户不存在
		if(users==null||users.size()==0){
            throw new UnknownAccountException();
        }
		//删除过期或者已经被标记删除的用户
        Iterator<SysUser> iterator = users.iterator();
		while (iterator.hasNext()){
            SysUser sysUser = iterator.next();
            //未激活 并且过期的账户
            if(SysUser.USER_STATUS_UNACTIVE.equals(sysUser.getUserStatus())&& DateUtils.truncatedCompareTo(new Date(),sysUser.getCreateTime(), Calendar.HOUR)>48){
                iterator.remove();
            }
            //被标记删除的用户
            if(SysUser.USER_STATUS_DELETE.equals(sysUser.getUserStatus())){
                iterator.remove();
            }
        }
        //删除 过期和被标记删除的用户之后 还有多余的用户  那么系统异常了。
        if(users.size()>1){
            throw new UnknownAccountException();
        }
        //校验 账户密码信息
        SysUser user = users.get(0);
        //账户被锁异常
        if(user.getUserStatus().equals(SysUser.USER_STATUS_FORBIT)){
            throw new DisabledAccountException();
        }
        //账户未激活
        if(SysUser.USER_STATUS_UNACTIVE.equals(user.getUserStatus())){
            throw new AccountUnActiveException();
        }
        //将用户信息保存到session中
        SecurityUtils.getSubject().getSession().setAttribute(SysUser.SYS_USER, user);
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), // 登录名
                user.getPassword(), // 密码
                ByteSource.Util.bytes(user.getUserName()+user.getSalt()), // salt=username+salt
                getName() // realm name
        );
        return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}
}
