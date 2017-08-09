package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.system.dao.SysRoleDao;
import com.lovecws.mumu.system.entity.SysRole;
import com.lovecws.mumu.system.service.SysRoleMenuService;
import com.lovecws.mumu.system.service.SysRolePermissionService;
import com.lovecws.mumu.system.service.SysRoleService;
import com.lovecws.mumu.system.service.SysUserRoleService;
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
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao roleDao;
	@Autowired
	private SysRoleMenuService roleMenuService;
	@Autowired
	private SysRolePermissionService rolePermissionService;
	@Autowired
	private SysUserRoleService userRoleService;

	@Override
	public List<SysRole> querySysRoleByCondition(String roleType, String roleCode, String roleName, String roleStatus) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleType", roleType);
		paramMap.put("roleCode", roleCode);
		paramMap.put("roleName", roleName);
		paramMap.put("roleStatus", roleStatus);
		return roleDao.listByColumn(paramMap);
	}

	@Override
	@Transactional(readOnly=false)
	public SysRole addSysRole(SysRole role) {
		role.setCreateTime(new Date());
		role.setRoleStatus(PublicEnum.NORMAL.value());
		return roleDao.insert(role);
	}

	@Override
	public SysRole getSysRoleById(String roleId) {
		return roleDao.getById(roleId);
	}

	@Override
	@Transactional(readOnly=false)
	public SysRole updateSysRoleById(SysRole role) {
		role.setEditTime(new Date());
		roleDao.update(role);
		return role;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteById(String roleId) {
		//删除角色权限
		rolePermissionService.deleteRolePermissionByRoleId(roleId);
		//删除角色菜单
		roleMenuService.deleteRoleMenuByRoleId(roleId);
		//删除用户角色
		userRoleService.deleteUserRoleByRoleId(roleId);
		//删除角色
		roleDao.delete(roleId);
	}

	@Override
	public List<SysRole> getSysRoleByUserId(String userId, String roleStatus) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("roleStatus", roleStatus);
		return roleDao.selectList("getSysRoleByUserId", paramMap);
	}
	
	@Override
	public List<SysRole> getSysRoleByGroupId(String groupId, String roleStatus) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("groupId", groupId);
		paramMap.put("roleStatus", roleStatus);
		return roleDao.selectList("getSysRoleByGroupId", paramMap);
	}

	@Override
	public PageBean<SysRole> listPage(String roleType, String roleName, int curPage, int pageSize) {
		PageParam pageParam=new PageParam(curPage, pageSize);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleType", roleType);
		paramMap.put("roleName", roleName);
		return roleDao.listPage(pageParam, paramMap);
	}

}
