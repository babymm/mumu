package com.lovecws.mumu.system.service;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.system.entity.SysMenu;

import java.util.List;

public interface SysMenuService {

	/**
	 * 获取顶级菜单 
	 * @param menuStatus 菜单状态 参见PublicEnum
	 * @return
	 */
	public List<SysMenu> getTopSysMenu(String menuStatus);

	/**
	 * 保存菜单
	 * @param menu 菜单实体对象
	 * @return
	 */
	public SysMenu addMenu(SysMenu menu);

	/**
	 * 获取菜单详情
	 * @param menuId 菜单id
	 * @return
	 */
	public SysMenu getSysMenuById(String menuId);

	/**
	 * 更新菜单
	 * @param menu 菜单实体对象
	 * @return
	 */
	public SysMenu updateMenuById(SysMenu menu);

	/**
	 * 删除菜单
	 * @param menuId 菜单id
	 */
	public void deleteMenuById(String menuId);

	/**
	 * 获取所有的子菜单
	 * @param menuStatus 权限状态 参见PublicEnum
	 * @return
	 */
	public List<SysMenu> getSubSysMenu(String menuStatus);

	/**
	 * 根据角色id获取菜单
	 * @param roleId 角色id
	 * @param menuStatus 菜单状态 
	 * @return
	 */
	public List<SysMenu> getSysMenuByRoleId(String roleId, String menuStatus);

	/**
	 * 获取菜单集合
	 * @param userId 用户id
	 * @return
	 */
	public List<SysMenu> getSysMenuByUserId(Integer userId);

	/**
	 * 分页获取菜单列表
	 * @param parentMenuId 父菜单id
	 * @param menuName 菜单名称
	 * @param pageNum 开视页
	 * @param pageSize 一页数量
	 * @return
	 */
	public PageBean<SysMenu> listPage(String parentMenuId, String menuName, int pageNum, int pageSize);

	/**
	 * 查询菜单
	 * @param menuCode 菜单内码
	 * @param menuName 菜单名称
	 * @return
	 */
	public List<SysMenu> querySysMenuByCondition(String parentMenuId, String menuCode, String menuName);

}
