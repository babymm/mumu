package com.lovecws.mumu.system.service;

public interface SysRolePermissionService {

	/**
	 * 保存角色权限
	 * @param roleId 角色id
	 * @param permissionIds 权限id（多条以逗号隔开）
	 * @param creator 创建者
	 * @return
	 */
	public void saveRolePermission(String roleId, String permissionIds, String creator);

	/**
	 * 删除角色权限
	 * @param menuId 菜单id
	 */
	public void deleteRolePermissionByMenuId(String menuId);

	/**
	 * 删除权限
	 * @param permissionId 权限id
	 */
	public void deleteRolePermissionByPermissionId(String permissionId);

	/**
	 * 删除角色权限
	 * @param roleId 角色id
	 */
	public void deleteRolePermissionByRoleId(String roleId);

}
