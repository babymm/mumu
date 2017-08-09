package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.system.dao.SysGroupRoleDao;
import com.lovecws.mumu.system.entity.SysGroupRole;
import com.lovecws.mumu.system.service.SysGroupRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class SysGroupRoleServiceImpl implements SysGroupRoleService {

	@Autowired
	private SysGroupRoleDao groupRoleDao;

	@Override
	@Transactional(readOnly = false)
	public void saveGroupRole(String groupId, String roleIds, String creator) {
		// 删除 用户组下的角色
		deleteGroupRoleByGroupIdOrRoleId(groupId,null);
		
		if (roleIds != null && !"".equals(roleIds)) {
			String[] roleArray = roleIds.split(",");
			List<SysGroupRole> groupRoles = new ArrayList<SysGroupRole>();
			for (String roleId : roleArray) {
				groupRoles.add(new SysGroupRole(String.valueOf(PublicEnum.NORMAL.value()), creator, new Date(), Integer.parseInt(groupId),Integer.parseInt(roleId)));
			}
			// 添加角色权限
			addSysGroupRoles(groupRoles);
		}
	}
	
	@Transactional(readOnly = false)
	private void addSysGroupRoles(List<SysGroupRole> groupRoles) {
		groupRoleDao.insert(groupRoles);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteGroupRoleByGroupIdOrRoleId(String groupId,String roleId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("groupId", groupId);
		paramMap.put("roleId", roleId);
		groupRoleDao.delete(paramMap);
	}
}
