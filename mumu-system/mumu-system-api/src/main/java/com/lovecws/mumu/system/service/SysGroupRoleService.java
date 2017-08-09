package com.lovecws.mumu.system.service;

public interface SysGroupRoleService {

	/**
	 * 保存用户组角色
	 * @param groupId 用户组id
	 * @param roleIds 角色id
	 * @param creator 创建者
	 */
	public void saveGroupRole(String groupId, String roleIds, String creator);

	/**
	 * 删除用户组角色
	 * @param groupId 用户组id
	 * @param roleId 角色id
	 */
	void deleteGroupRoleByGroupIdOrRoleId(String groupId, String roleId);

}
