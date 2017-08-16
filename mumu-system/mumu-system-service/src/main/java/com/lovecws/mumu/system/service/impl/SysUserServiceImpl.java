package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.system.dao.SysUserDao;
import com.lovecws.mumu.system.entity.SysUser;
import com.lovecws.mumu.system.service.SysGroupService;
import com.lovecws.mumu.system.service.SysUserGroupService;
import com.lovecws.mumu.system.service.SysUserRoleService;
import com.lovecws.mumu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao userDao;
	@Autowired
	private SysUserRoleService userRoleService;
	@Autowired
	private SysUserGroupService userGroupService;

	@Override
	public List<SysUser> querySysUserByCondition(String quserName, String qnickName, String qemail, String qphone, String userStatus) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", quserName);
		paramMap.put("nickName", qnickName);
		paramMap.put("email", qemail);
		paramMap.put("phone", qphone);
		if(userStatus!=null){
			paramMap.put("userStatus", userStatus.split(","));
		}
		return userDao.listByColumn(paramMap);
	}

	@Override
	public SysUser getSysUserById(String userId) {
		return userDao.getById(userId);
	}

	@Transactional(readOnly = false)
	@Override
	public SysUser updateById(SysUser user) {
		user.setEditTime(new Date());
		user.setDetailArea(user.getProvince()+user.getCity()+user.getArea());
		userDao.update(user);
		return user;
	}

	@Transactional(readOnly = false)
	@Override
	public SysUser addUser(SysUser user) {
		user.setCreateTime(new Date());
		/*user.setUserStatus(PublicEnum.NORMAL.value());
		user.setPhoneActive("0");
		user.setEmailActive("0");*/
		user.setDetailArea(user.getProvince()+user.getCity()+user.getArea());
		user=userDao.insert(user);
		return user;
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(String userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		//删除用户角色中间表数据
		userRoleService.deleteUserRoleByUserId(userId);
		//删除用户群组中间表数据
		userGroupService.deleteSysUserGroupByUserIdOrGroupId(userId,null);
		//删除用户表数据
		userDao.delete(userId);
	}

	@Override
	public SysUser getUserByUserName(String loginName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", loginName);
		return userDao.getBy(paramMap);
	}

	@Override
	public SysUser getUserByUserPhone(String userPhone) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("phone", userPhone);
		List<SysUser> users=userDao.listByColumn(paramMap);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}

	@Override
	public SysUser getUserByCondition(String userName,String userPhone,String userEmail){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userName);
		paramMap.put("phone", userPhone);
		paramMap.put("email", userEmail);
		List<SysUser> users=userDao.listByColumn(paramMap);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}

	@Override
	public PageBean<SysUser> listPage(String userName, String email, String phone, int pageNum, int pageSize) {
		PageParam pageParam=new PageParam(pageNum, pageSize);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userName);
		paramMap.put("email", email);
		paramMap.put("phone", phone);
		return userDao.listPage(pageParam, paramMap);
	}
	
	@Override
	public List<SysUser> querySysUserByGroupId(String groupId, String userStatus) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("groupId", groupId);
		if(userStatus!=null){
			paramMap.put("userStatus", userStatus.split(","));
		}
		return userDao.selectList("querySysUserByGroupId", paramMap);
	}

}
