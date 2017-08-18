package com.lovecws.mumu.data.car.service;

import java.util.List;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.data.car.entity.DataCarCars;

public interface DataCarCarsService {
	/** ========app后台接口======= */
	/**
	 * 根据父id获取汽车车系
	 * 
	 * @param parentId
	 * @return
	 */
	public List<DataCarCars> getSystemCarsByParentId(String parentId);

	/**
	 * 获取热门推荐的汽车车系
	 * 
	 * @return
	 */
	public List<DataCarCars> getHotSystemCars();

	/**
	 * 根据汽车型号来查询汽车车系(做分页处理)
	 * 
	 * @param sizetype
	 * @return
	 */
	public List<DataCarCars> getSystemCarsBySizetype(String sizetype, PageParam page);

	/**
	 * 根据搜索的内容，搜索汽车车系
	 * 
	 * @param searchContent
	 * @param page
	 * @return
	 */
	public List<DataCarCars> getSystemCarsBySearch(String searchContent, PageParam page);

	/** ========web后台接口======= */
	/**
	 * 获取汽车车系
	 * 
	 * @param parentId
	 * @param searchContent
	 * @param page
	 * @return
	 */
	public PageBean<DataCarCars> getCars(String parentId, String searchContent, PageParam page);

	/**
	 * 添加汽车车系
	 * 
	 * @param carsEntity
	 * @return
	 */
	public DataCarCars addSystemCars(DataCarCars carsEntity);

	/**
	 * 修改+删除汽车车系
	 * 
	 * @param carsEntity
	 * @return
	 */
	public boolean updateSystemCars(DataCarCars carsEntity);

	/**
	 * 根据id获取汽车车系
	 * 
	 * @param id
	 * @return
	 */
	public DataCarCars getSystemCarsById(Integer id);
}
