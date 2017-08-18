package com.lovecws.mumu.data.car.service;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.data.car.entity.DataCarSubBrand;

import java.util.List;

public interface DataCarSubBrandService {
	
	/**=========================app后台接口=======================*/
	/**
	 * 根据父id或者品牌名称获取汽车子公司
	 * @param parentId
	 * @return
	 */
	public List<DataCarSubBrand> getSystemCarSubBrandByParentId(String parentId);
	
	/**=========================web后台接口=======================*/
	/**
	 * 根据父id获取汽车子公司（分页+搜索）
	 * @param parentId
	 * @param searchContent
	 * @param page
	 * @return
	 */
	public PageBean<DataCarSubBrand> getCarSubBrandByParentId(String parentId, String searchContent, PageParam page);
	
	/**
	 * 添加汽车子公司
	 * @param carSubBrandEntity
	 * @return
	 */
	public DataCarSubBrand addSystemCarSubBrand(DataCarSubBrand carSubBrandEntity);
	
	/**
	 * 修改+删除汽车子公司
	 * @param carSubBrandEntity
	 * @return
	 */
	public boolean updateSystemCarSubBrand(DataCarSubBrand carSubBrandEntity);
	
	/**
	 * 根据id获取汽车子公司
	 * @param id
	 * @return
	 */
	public DataCarSubBrand getCarSubBrandById(Integer id);
}
