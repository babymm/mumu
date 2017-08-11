package com.lovecws.mumu.system.service;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.system.entity.SysGroup;

import java.util.List;

public interface SysGroupService {

	/**
	 * 分页获取用户组织
	 * @param orgId 组织id
	 * @param groupName 组名称
	 * @param pageNum 当前页数
	 * @param pageSize 一页大小
	 * @return
	 */
	public PageBean<SysGroup> listPage(String orgId, String groupName, int pageNum, int pageSize);

	/**
	 * 添加用户组
	 * @param group
	 */
	public SysGroup addSysGroup(SysGroup group);

	/**
	 * 查询用户组
	 * @param orgId 机构id
	 * @param groupName 用户组名称
	 * @return
	 */
	public List<SysGroup> querySysGroupByCondition(String orgId, String groupName);

	/**
	 * 获取用户id
	 * @param groupId 用户组id
	 * @return
	 */
	public SysGroup getSysGroupById(String groupId);

	/**
	 * 更新用户组id
	 * @param group
	 */
	public void updateSysGroupById(SysGroup group);

	/**
	 * 删除用户组
	 * @param groupId 用户组id
	 */
	public void deleteSysGroupById(String groupId);

	/**
	 * 群组分布统计图
	 */
	public List<SysGroup> queryGroupStatistics();

}
