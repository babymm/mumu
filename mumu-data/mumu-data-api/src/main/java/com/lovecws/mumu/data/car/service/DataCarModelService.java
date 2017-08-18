package com.lovecws.mumu.data.car.service;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.data.car.entity.DataCarModel;

import java.util.List;

public interface DataCarModelService {
	/** ========app后台接口======= */
	/**
	 * 根据父id获取汽车实体
	 * @param parentId
	 * @return
	 */
	public List<DataCarModel> getSystemCarModelByParentId(String parentId);
	
	/**
	 * 获取所有的汽车实体
	 * @return
	 */
	public List<DataCarModel> getAllSystemCarModel();
	
	/** ========web后台接口======= */
	/**
	 * 获取汽车车型
	 * @param parentId
	 * @param searchContent
	 * @param page
	 * @return
	 */
	public PageBean<DataCarModel> getSystemCarModel(String parentId, String searchContent, PageParam page);
	
	/**
	 * 添加汽车车型
	 * @param systemCarModel
	 * @return
	 */
	public DataCarModel addSystemCarModel(DataCarModel systemCarModel);
	
	/**
	 * 修改汽车车型
	 * @param systemCarModel
	 * @return
	 */
	public boolean updateSystemCarModel(DataCarModel systemCarModel);
	
	/**
	 * 删除汽车车型
	 * @param id
	 * @return
	 */
	public boolean deleteSystemCarModel(String id);
	
	/**
	 * 根据id获取汽车车型
	 * @param id
	 * @return
	 */
	public DataCarModel getSystemCarModelById(Integer id);
}
