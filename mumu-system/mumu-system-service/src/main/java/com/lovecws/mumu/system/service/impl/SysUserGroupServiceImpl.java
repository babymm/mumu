package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.system.dao.SysUserGroupDao;
import com.lovecws.mumu.system.entity.SysUserGroup;
import com.lovecws.mumu.system.service.SysUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
public class SysUserGroupServiceImpl implements SysUserGroupService {

	@Autowired
	private SysUserGroupDao userGroupDao;

	@Override
	@Transactional(readOnly=false)
	public SysUserGroup addSysUserGroup(SysUserGroup userGroup) {
		userGroup.setCreateTime(new Date());
		userGroup.setUserGroupStatus(PublicEnum.NORMAL.value());
		return userGroupDao.insert(userGroup);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteSysUserGroupById(String userGroupId) {
		userGroupDao.delete(userGroupId);
	}

	@Override
	public SysUserGroup getSysUserGroupById(String userGroupId) {
		return userGroupDao.getById(userGroupId);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateSysUserGroupById(SysUserGroup userGroup) {
		userGroup.setEditTime(new Date());
		userGroupDao.update(userGroup);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteSysUserGroupByUserIdOrGroupId(String userId,String groupId) {
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("groupId", groupId);
		userGroupDao.delete(paramMap);
	}
}
