package com.lovecws.mumu.system.service;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.system.entity.SysOrganize;

import java.util.List;

public interface SysOrganizeService {

	/**
	 * 分页获取组织机构列表
	 * @param orgName 组织名称
	 * @param pageNum 当前分页
	 * @param pageSize 一页大小
	 * @return
	 */
	public PageBean<SysOrganize> listPage(String orgName, int pageNum, int pageSize);

	/**
	 * 保存组织机构
	 * @param organize 组织实体
	 * @return
	 */
	public SysOrganize addSysOrganize(SysOrganize organize);

	/**
	 * 查询
	 * @param orgName 组织机构名称
	 * @return
	 */
	public List<SysOrganize> querySysOrganizeByCondition(String orgName);

	/**
	 * 获取组织机构实体
	 * @param orgId id
	 * @return
	 */
	public SysOrganize getSysOrganizeById(String orgId);

	/**
	 * 更新组织机构
	 * @param organize
	 */
	public void updateSysOrganize(SysOrganize organize);

	/**
	 * 删除组织机构
	 * @param orgId 组织机构id
	 */
	public void deleteSysOrganizeById(String orgId);

}
