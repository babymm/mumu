package com.lovecws.mumu.system.service;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.system.entity.SysExportModel;

import java.util.List;


public interface SysExportModelService {

	/**
	 * 分页获取数据字典
	 * @param modelName 模型名称
	 * @param pageNum 当前分页
	 * @param pageSize 一页数量
	 * @return
	 */
	public PageBean<SysExportModel> listPage(String modelName, int pageNum, int pageSize);

	
	/**
	 * 删除模型
	 * @param modelId 模型id
	 */
	public void deleteById(String modelId);

	/**
	 * 添加模型
	 * @param exportModel 模型实体
	 */
	public void addExportModel(SysExportModel exportModel);

	/**
	 * 获取模型
	 * @param modelId 模型id
	 * @return
	 */
	public SysExportModel getSysExportModelById(String modelId);

	/**
	 * 更新模型
	 * @param exportModel 模型实体
	 */
	public void updateSysExportModel(SysExportModel exportModel);


	/**
	 * 获取所有的导出模型
	 * @param modelName 模型名称
	 * @return
	 */
	public List<SysExportModel> queryExportModelByCondition(String modelName);

}
