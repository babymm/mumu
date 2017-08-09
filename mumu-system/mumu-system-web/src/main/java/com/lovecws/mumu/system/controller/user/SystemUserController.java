package com.lovecws.mumu.system.controller.user;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.common.core.tree.ZTreeBean;
import com.lovecws.mumu.common.security.shiro.entity.BaseRealm;
import com.lovecws.mumu.common.security.shiro.utils.PasswordHelper;
import com.lovecws.mumu.system.entity.SysMenu;
import com.lovecws.mumu.system.entity.SysPermission;
import com.lovecws.mumu.system.entity.SysRole;
import com.lovecws.mumu.system.entity.SysUser;
import com.lovecws.mumu.system.service.SysRoleService;
import com.lovecws.mumu.system.service.SysUserRoleService;
import com.lovecws.mumu.system.service.SysUserService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/system/user")
public class SystemUserController {

	private static final Logger log =Logger.getLogger(SystemUserController.class);
	@Autowired
	private SysUserService userService;
	@Autowired
	private SysUserRoleService userRoleService;
	@Autowired
	private SysRoleService roleService;
	
	/**
	 * 用户列表
	 * @return
	 */
	@RequestMapping(value={"/index"},method=RequestMethod.GET)
	public String users(){
		return "system/user/index";
	}
	
	/**
	 * 分页获取用户数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/page"},method=RequestMethod.GET)
	public Map<String,Object> userPage(String userName,String userEmail,String userPhone,int beginIndex,int pageSize){
		PageBean<SysUser> pageBean = userService.listPage(userName,userEmail,userPhone, beginIndex / pageSize + 1, pageSize);
		Map<String, Object> page = new HashMap<String, Object>();
		page.put("total", pageBean.getTotalCount());
		page.put("rows", pageBean.getRecordList());
		return page;
	}
	
	/**
	 * 跳转到添加用户页面
	 * @return
	 */
	@RequestMapping(value={"/add"},method=RequestMethod.GET)
	public String addUser(){
		return "system/user/add";
	}
	
	/**
	 * 保存用户
	 * @param user 用户实体
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/add"},method=RequestMethod.POST)
	public ResponseEntity saveUser(SysUser user){
		List<SysUser> users = userService.querySysUserByCondition(user.getUserName(), null, user.getEmail(), user.getPhone(), null);
		if(users!=null&&users.size()>0){
			String errorMsg="用户名称、用户手机号码、用户邮箱不可重复";
			for (SysUser sysUser : users) {
				if(sysUser.getUserName().equals(user.getUserName())){
					errorMsg="用户名称不可重复";
					break;
				}else if(sysUser.getPhone().equals(user.getPhone())){
					errorMsg="用户手机号码不可重复";
					break;
				}else if(sysUser.getEmail().equals(user.getEmail())){
					errorMsg="用户邮箱不可重复";
					break;
				}
			}
			return new ResponseEntity(500, errorMsg, null);
		}
		//加密密码
		BaseRealm baseRealm = PasswordHelper.encryptPassword(new BaseRealm(user.getUserName(), user.getPassword()));
		user.setPassword(baseRealm.getPassword());
		user.setSalt(baseRealm.getSalt());
		try {
			user.setCreator(SecurityUtils.getSubject().getPrincipal().toString());
			userService.addUser(user);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "保存用户出现异常", null);
		}
		return new ResponseEntity(200, "添加用户操作成功", null);
	}
	
	/**
	 * 跳转到查看用户详情页面
	 * @return
	 */
	@RequestMapping(value={"/view/{userId}"},method=RequestMethod.GET)
	public String userView(@PathVariable String userId,HttpServletRequest request){
		SysUser user = userService.getSysUserById(userId);
		request.setAttribute("user", user);
		return "system/user/view";
	}
	
	/**
	 * 跳转到编辑用户页面
	 * @return
	 */
	@RequestMapping(value={"/edit/{userId}"},method=RequestMethod.GET)
	public String userEdit(@PathVariable String userId,HttpServletRequest request){
		SysUser user = userService.getSysUserById(userId);
		request.setAttribute("user", user);
		return "system/user/edit";
	}
	
	/**
	 * 更新用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/edit"},method=RequestMethod.PUT)
	public ResponseEntity updateUser(SysUser user){
		List<SysUser> users = userService.querySysUserByCondition(user.getUserName(), null, user.getEmail(), user.getPhone(), null);
		if(users!=null&&users.size()>0){
			String errorMsg=null;
			for (SysUser sysUser : users) {
                if(user.getUserId().equals(sysUser.getUserId())){
                    continue;
                }
				if(sysUser.getUserName().equals(user.getUserName())){
					errorMsg="用户名称不可重复";
					break;
				}else if(sysUser.getPhone().equals(user.getPhone())){
					errorMsg="用户手机号码不可重复";
					break;
				}else if(sysUser.getEmail().equals(user.getEmail())){
					errorMsg="用户邮箱不可重复";
					break;
				}
			}
			if(errorMsg!=null){
                return new ResponseEntity(500, errorMsg, null);
            }
		}
		try {
			user.setEditor(SecurityUtils.getSubject().getPrincipal().toString());
			userService.updateById(user);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "更新用户出现异常", null);
		}
		return new ResponseEntity(200, "更新用户操作成功", null);
	}

	/**
	 * 更新用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/editStatus"}, method = RequestMethod.PUT)
	public ResponseEntity updateUserStatus(SysUser user) {
		try {
			user.setEditor(SecurityUtils.getSubject().getPrincipal().toString());
			userService.updateById(user);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "修改用户状态出现异常", null);
		}
		return new ResponseEntity(200,"用户修改状态操作成功",null);
	}
	
	/**
	 * 跳转到编辑用户页面
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/delete/{userId}"},method=RequestMethod.DELETE)
	public ResponseEntity userDelete(@PathVariable String userId){
		try {
			userService.deleteById(userId);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "删除用户出现异常", null);
		}
		return new ResponseEntity(200, "删除用户操作成功", null);
	}
	
	/**
	 * 重置密码
	 * @param userId 用户id
	 * @param userName 用户名称
	 * @param password 用户密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/resetPwd"},method=RequestMethod.PUT)
	public ResponseEntity userResetPwd(String userId,String userName,String password){
		try {
			SysUser user=new SysUser();
			user.setUserId(Integer.parseInt(userId));
			user.setUserName(userName);
			//加密密码
			BaseRealm baseRealm = PasswordHelper.encryptPassword(new BaseRealm(userName, password));
			user.setPassword(baseRealm.getPassword());
			user.setSalt(baseRealm.getSalt());
			user.setEditor(SecurityUtils.getSubject().getPrincipal().toString());
			userService.updateById(user);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "用户重置密码出现异常", null);
		}
		return new ResponseEntity(200,"用戶重置密码成功",null);
	}


	/**
	 * 用户分配角色
	 * @return
	 */
	@RequestMapping(value = {"/allowRole"}, method = RequestMethod.GET)
	public String allowRole(String userId, HttpServletRequest request) {
		request.setAttribute("userId", userId);
		return "system/user/allowRole";
	}

	/**
	 * 用户分配角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/allowRole/{userId}"}, method = RequestMethod.GET)
	public ZTreeBean userRole(@PathVariable String userId) {
		//获取所有的角色
		List<SysRole> allRoles = roleService.querySysRoleByCondition(null, null, null, PublicEnum.NORMAL.value());
		//获取当前用户的角色
		List<SysRole> selectedRoles = roleService.getSysRoleByUserId(userId, PublicEnum.NORMAL.value());
		List<ZTreeBean> beans = new ArrayList<ZTreeBean>();
		for (SysRole sysRole : allRoles) {
			boolean checked = false;
			for (SysRole selectedRole : selectedRoles) {
				if (selectedRole.getRoleId() == sysRole.getRoleId()) {
					checked = true;
					break;
				}
			}
			beans.add(new ZTreeBean(String.valueOf(sysRole.getRoleId()), "topRole", sysRole.getRoleName(), true, "", checked));
		}
		ZTreeBean bean = new ZTreeBean("topRole", "0", "角色树", true, "", "", false, beans);
		return bean;
	}

	/**
	 * 用户分配角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"/allowRole"}, method = RequestMethod.POST)
	public ResponseEntity saveUserRole(String userId, String roleIds) {
		try {
			String loginName = SecurityUtils.getSubject().getPrincipal().toString();
			userRoleService.saveUserRole(userId, roleIds, loginName);
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(200, "用户角色保存出现异常", null);
		}
		return new ResponseEntity(200, "用户角色保存成功", null);
	}
}
