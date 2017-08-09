package com.lovecws.mumu.system.service;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.system.entity.SysRole;

import java.util.List;

public interface SysRoleService {

	/**
	 * 查询角色列表
	 * @param roleType 角色类型
	 * @param roleCode 角色内码
	 * @param roleName 角色名称
	 * @param roleStatus 角色状态
	 * @return
	 */
	public List<SysRole> querySysRoleByCondition(String roleType, String roleCode, String roleName, String roleStatus);

	/**
	 * 添加角色
	 * @param role 角色实体对象
	 * @return
	 */
	public SysRole addSysRole(SysRole role);

	/**
	 * 获取角色详情
	 * @param roleId 角色id
	 * @return
	 */
	public SysRole getSysRoleById(String roleId);

	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	public SysRole updateSysRoleById(SysRole role);

	/**
	 * 删除角色
	 * @param roleId
	 */
	public void deleteById(String roleId);

	/**
	 * 获取用户下的角色列表
	 * @param userId 用户id
	 * @param roleStatus 角色状态
	 * @return
	 */
	public List<SysRole> getSysRoleByUserId(String userId, String roleStatus);

	/**
	 * 获取用户组下的角色
	 * @param groupId 用户组id
	 * @param roleStatus 角色状态
	 * @return
	 */
	public List<SysRole> getSysRoleByGroupId(String groupId, String roleStatus);
	
	/**
	 * 分页获取角色
	 * @param roleType 角色类型
	 * @param roleName 角色名称
	 * @param curPage 当前页
	 * @param pageSize 一页大小
	 * @return
	 */
	public PageBean<SysRole> listPage(String roleType, String roleName, int curPage, int pageSize);

}
