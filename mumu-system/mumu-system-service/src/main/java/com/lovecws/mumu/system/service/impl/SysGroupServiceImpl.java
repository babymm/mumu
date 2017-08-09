package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.system.dao.SysGroupDao;
import com.lovecws.mumu.system.entity.SysGroup;
import com.lovecws.mumu.system.service.SysGroupRoleService;
import com.lovecws.mumu.system.service.SysGroupService;
import com.lovecws.mumu.system.service.SysUserGroupService;
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
public class SysGroupServiceImpl implements SysGroupService {

	@Autowired
	private SysGroupDao groupDao;
	@Autowired
	private SysUserGroupService userGroupService;
	@Autowired
	private SysGroupRoleService groupRoleService;
	
	@Override
	public PageBean<SysGroup> listPage(String orgId, String groupName, int pageNum, int pageSize) {
		PageParam pageParam=new PageParam(pageNum, pageSize);
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("orgId", orgId);
		paramMap.put("groupName", groupName);
		return groupDao.listPage(pageParam, paramMap);
	}

	@Override
	@Transactional(readOnly=false)
	public SysGroup addSysGroup(SysGroup group) {
		group.setCreateTime(new Date());
		group.setGroupStatus(PublicEnum.NORMAL.value());
		return groupDao.insert(group);
	}

	@Override
	public List<SysGroup> querySysGroupByCondition(String orgId, String groupName) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("orgId", orgId);
		paramMap.put("groupName", groupName);
		return groupDao.listByColumn(paramMap);
	}

	@Override
	public SysGroup getSysGroupById(String groupId) {
		return groupDao.getById(groupId);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateSysGroupById(SysGroup group) {
		group.setEditTime(new Date());
		groupDao.update(group);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteSysGroupById(String groupId) {
		//删除用户组和用户的关系
		userGroupService.deleteSysUserGroupByUserIdOrGroupId(null,groupId);
		//删除用户组和角色的关系
		groupRoleService.deleteGroupRoleByGroupIdOrRoleId(groupId,null);
		//删除用户组
		groupDao.delete(groupId);
	}

}
