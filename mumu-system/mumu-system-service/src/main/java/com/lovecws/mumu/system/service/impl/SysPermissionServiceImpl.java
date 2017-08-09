package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.system.dao.SysPermissionDao;
import com.lovecws.mumu.system.entity.SysPermission;
import com.lovecws.mumu.system.service.SysPermissionService;
import com.lovecws.mumu.system.service.SysRolePermissionService;
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
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
public class SysPermissionServiceImpl implements SysPermissionService {

	@Autowired
	private SysPermissionDao permissionDao;
	@Autowired
	private SysRolePermissionService rolePermissionService;

	@Override
	public List<SysPermission> querySysPermissionByCondition(String menuId, String permissionCode,
															 String permissionName, String permissionStatus) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		paramMap.put("permissionCode", permissionCode);
		paramMap.put("permissionName", permissionName);
		paramMap.put("permissionStatus", permissionStatus);
		return permissionDao.listByColumn(paramMap);
	}

	@Override
	@Transactional(readOnly=false)
	public SysPermission addPermission(SysPermission permission) {
		permission.setCreateTime(new Date());
		permission.setPermissionStatus(PublicEnum.NORMAL.value());
		permission.setPermissionPath(permission.getPermission());
		return permissionDao.insert(permission);
	}

	@Override
	@Transactional(readOnly=false)
	public SysPermission updatePermissionById(SysPermission permission) {
		permission.setEditTime(new Date());
		permissionDao.update(permission);
		return permission;
	}

	@Override
	public SysPermission getSysPermissionById(String permissionId) {
		return permissionDao.getById(permissionId);
	}

	@Override
	@Transactional(readOnly=false)
	public void deletePermissionById(String permissionId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("permissionId", permissionId);
		//删除角色权限
		rolePermissionService.deleteRolePermissionByPermissionId(permissionId);
		//删除权限
		permissionDao.delete(permissionId);
	}

	@Override
	public List<SysPermission> getSysPermissionByRoleId(String roleId, String permissionStatus) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		paramMap.put("permissionStatus", permissionStatus);
		return permissionDao.selectList("getSysPermissionByRoleId", paramMap);
	}

	@Override
	public List<SysPermission> getSysPermissionByUserId(Integer userId, String permissionStatus) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("permissionStatus", permissionStatus);
		return permissionDao.selectList("getSysPermissionByUserId", paramMap);
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePermissionByMenuId(String menuId) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		permissionDao.delete(paramMap);
	}

	@Override
	public PageBean<SysPermission> listPage(String menuId, String permissionCode, String permissionName, int pageNum,
											int pageSize) {
		PageParam pageParam=new PageParam(pageNum, pageSize);
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		paramMap.put("permissionCode", permissionCode);
		paramMap.put("permissionName", permissionName);
		paramMap.put("permissionStatus", PublicEnum.NORMAL.value());
		return permissionDao.listPage(pageParam, paramMap);
	}
}
