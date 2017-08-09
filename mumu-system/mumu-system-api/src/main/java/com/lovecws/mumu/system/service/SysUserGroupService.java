package com.lovecws.mumu.system.service;

import com.lovecws.mumu.system.entity.SysUserGroup;

public interface SysUserGroupService {

	/**
	 * 用户组添加用户
	 * @param userGroup
	 * @return
	 */
	public SysUserGroup addSysUserGroup(SysUserGroup userGroup);

	/**
	 * 移除组成员
	 * @param userGroupId id
	 */
	public void deleteSysUserGroupById(String userGroupId);

	/**
	 * 查看详情
	 * @param userGroupId
	 * @return
	 */
	public SysUserGroup getSysUserGroupById(String userGroupId);

	/**
	 * 编辑组成员信息
	 * @param userGroup
	 */
	public void updateSysUserGroupById(SysUserGroup userGroup);

	/**
	 * 删除用户组和用户的关系
	 * @param userId 用户id
	 * @param groupId 用户组id
	 */
	public void deleteSysUserGroupByUserIdOrGroupId(String userId, String groupId);

}
