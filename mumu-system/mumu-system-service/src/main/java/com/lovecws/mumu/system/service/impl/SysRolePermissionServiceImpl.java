package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.system.dao.SysRolePermissionDao;
import com.lovecws.mumu.system.entity.SysRolePermission;
import com.lovecws.mumu.system.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class SysRolePermissionServiceImpl implements SysRolePermissionService {

	@Autowired
	private SysRolePermissionDao rolePermissionDao;

	@Override
	@Transactional(readOnly = false)
	public void saveRolePermission(String roleId, String permissionIds, String creator) {
		// 删除 角色权限
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		rolePermissionDao.delete(paramMap);
		if (permissionIds != null && !"".equals(permissionIds)) {
			String[] permissionArray = permissionIds.split(",");
			List<SysRolePermission> rolePermissions = new ArrayList<SysRolePermission>();
			for (String permissionId : permissionArray) {
				rolePermissions.add(new SysRolePermission(PublicEnum.NORMAL.value(), creator, new Date(),
						Integer.parseInt(roleId), Integer.parseInt(permissionId)));
			}
			// 添加角色权限
			rolePermissionDao.insert(rolePermissions);
		}

	}

	@Override
	@Transactional(readOnly = false)
	public void deleteRolePermissionByMenuId(String menuId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuId", menuId);
		rolePermissionDao.delete(paramMap);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void deleteRolePermissionByPermissionId(String permissionId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("permissionId", permissionId);
		rolePermissionDao.delete(paramMap);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteRolePermissionByRoleId(String roleId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		rolePermissionDao.delete(paramMap);
	}

}
