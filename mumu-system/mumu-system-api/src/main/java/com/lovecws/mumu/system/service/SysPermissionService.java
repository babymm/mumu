package com.lovecws.mumu.system.service;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.system.entity.SysPermission;

import java.util.List;

public interface SysPermissionService {

	/**
	 * 查询权限集合
	 * @param menuId 菜单id
	 * @param permissionCode 权限内码
	 * @param permissionName 权限名称
	 * @param permissionStatus 权限状态
	 * @return
	 */
	public List<SysPermission> querySysPermissionByCondition(String menuId, String permissionCode, String permissionName, String permissionStatus);

	/**
	 * 保存权限
	 * @param permission 权限实体
	 * @return
	 */
	public SysPermission addPermission(SysPermission permission);

	/**
	 * 更新权限
	 * @param permission 权限实体
	 * @return
	 */
	public SysPermission updatePermissionById(SysPermission permission);

	/**
	 * 获取权限详情
	 * @param permissionId 权限
	 * @return
	 */
	public SysPermission getSysPermissionById(String permissionId);

	/**
	 * 删除权限
	 * @param permissionId 权限id
	 */
	public void deletePermissionById(String permissionId);

	/**
	 * 根据角色获取权限列表
	 * @param roleId 角色id
	 * @param permissionStatus 权限状态
	 * @return
	 */
	public List<SysPermission> getSysPermissionByRoleId(String roleId, String permissionStatus);

	/**
	 * 获取用户下的所有权限
	 * @param userId 用户id
	 * @param permissionStatus 权限状态
	 * @return
	 */
	public List<SysPermission> getSysPermissionByUserId(Integer userId, String permissionStatus);

	/**
	 * 删除菜单下的权限
	 * @param menuId 菜单列表
	 */
	public void deletePermissionByMenuId(String menuId);

	/**
	 * 添加权限
	 * @param menuId 菜单id
	 * @param permissionCode 菜单内码
	 * @param permissionName 菜单名称
	 * @param pageNum 当前分页
	 * @param pageSize 一页的数量
	 * @return
	 */
	public PageBean<SysPermission> listPage(String menuId, String permissionCode, String permissionName, int pageNum, int pageSize);

}
