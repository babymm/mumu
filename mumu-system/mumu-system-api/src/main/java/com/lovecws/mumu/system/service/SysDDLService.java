package com.lovecws.mumu.system.service;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.system.entity.SysDDL;

import java.util.List;

public interface SysDDLService {

	/**
	 * 获取数据字典列表
	 *
	 * @return
	 */
	public List<SysDDL> getSystemDDLs();

	/**
	 * 根据id查询数据字典
	 *
	 * @param ddlId
	 * @return
	 */
	public SysDDL getSystemDDLById(String ddlId);

	/**
	 * 添加数据字典
	 * @param systemDDL
	 * @return
	 */
	public SysDDL addSystemDDL(SysDDL systemDDL);

	/**
	 * 修改数据字典
	 *
	 * @param systemDDL
	 * @return
	 */
	public boolean updateSystemDDL(SysDDL systemDDL);

	/**
	 * 删除数据字典
	 *
	 * @param ddlId
	 * @return
	 */
	public boolean deleteSystemDDL(String ddlId);

	/**
	 * 分页获取数据字典
	 * @param ddlCode 字典内码
	 * @param parentCode 父字典内码
	 * @param pageParam 分页信息
	 * @return
	 */
	public PageBean<SysDDL> listPage(String ddlCode, String parentCode, String withDDLTables, PageParam pageParam);

	/**
	 * 根据数据字典内码来删除
	 * @param ddlCode 数据字典内码
	 * @return
	 */
	public List<SysDDL> getSystemDDLByCondition(String ddlCode);

	/**
	 * 根据传入的parentCode，查询字典内码列表
	 * @param parentCode
	 * @return
	 */
	public List<SysDDL> getSystemDDLByParentCode(List<String> parentCode);

	/**
	 * 根据传入的parentCode，查询字典内码
	 * @param parentCodes parentCode字符串，中间以","隔开
	 * @return
	 */
	public List<SysDDL> getSystemDDLByParentCodes(String parentCodes);

}
