package com.lovecws.mumu.system.service;

public interface SysRoleMenuService {

	/**
	 * 保存角色菜单
	 * @param roleId 角色id
	 * @param menuIds 菜单（以逗号分隔）
	 * @param creator 创建者
	 */
	public void saveRoleMenu(String roleId, String menuIds, String creator);

	/**
	 * 删除菜单下的权限
	 * @param menuId
	 */
	public void deleteRoleMenuByMenuId(String menuId);

	/**
	 * 删除角色菜单
	 * @param roleId 角色id
	 */
	public void deleteRoleMenuByRoleId(String roleId);

}
