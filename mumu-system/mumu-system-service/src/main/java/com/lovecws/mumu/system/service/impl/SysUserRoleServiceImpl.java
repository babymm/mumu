package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.system.dao.SysUserRoleDao;
import com.lovecws.mumu.system.entity.SysUserRole;
import com.lovecws.mumu.system.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class SysUserRoleServiceImpl implements SysUserRoleService {

	@Autowired
	private SysUserRoleDao userRoleDao;

	@Override
	@Transactional(readOnly = false)
	public void saveUserRole(String userId, String roleIds, String creator) {
		// 删除 用户角色
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		userRoleDao.delete(paramMap);
		
		if (roleIds != null && !"".equals(roleIds)) {
			String[] roleArray = roleIds.split(",");
			List<SysUserRole> userRoles = new ArrayList<SysUserRole>();
			for (String roleId : roleArray) {
				userRoles.add(new SysUserRole(PublicEnum.NORMAL.value(), creator, new Date(),
						Integer.parseInt(roleId), Integer.parseInt(userId)));
			}
			// 添加角色权限
			userRoleDao.insert(userRoles);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteUserRoleByRoleId(String roleId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleId", roleId);
		userRoleDao.delete(paramMap);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteUserRoleByUserId(String userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		userRoleDao.delete(paramMap);
	}

}
