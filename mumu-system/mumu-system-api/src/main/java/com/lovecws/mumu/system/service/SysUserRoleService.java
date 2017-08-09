package com.lovecws.mumu.system.service;

public interface SysUserRoleService {

	/**
	 * 添加用户角色
	 * @param userId 用户id
	 * @param roleIds 角色id
	 * @param creator 创建者
	 */
	public void saveUserRole(String userId, String roleIds, String creator);

	/**
	 * 删除角色用户关系
	 * @param roleId 角色id
	 */
	public void deleteUserRoleByRoleId(String roleId);

	/**
	 * 删除用户角色关系
	 * @param userId 用户id
	 */
	public void deleteUserRoleByUserId(String userId);

}
