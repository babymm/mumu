package com.lovecws.mumu.data.car.service;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.data.car.entity.DataCarBrand;

import java.util.List;

public interface DataCarBrandService {

	/** ========app后台接口======= */
	/**
	 * 获取所有的汽车品牌
	 * 
	 * @return
	 */
	public List<DataCarBrand> getAllSystemCarBrands();

	/**
	 * 获取热门推荐的汽车品牌
	 * 
	 * @return
	 */
	public List<DataCarBrand> getHotSystemCarBrands();

	/** ========web后台接口======= */
	/**
	 * 获取所有的汽车品牌（分页效果+搜索）
	 * 
	 * @param page
	 * @param searchContent
	 * @return
	 */
	public PageBean<DataCarBrand> getSystemCarBrands(String searchContent, PageParam page);

	/**
	 * 添加汽车品牌
	 * 
	 * @param systemCarBrand
	 * @return
	 */
	public DataCarBrand addSystemCarBrand(DataCarBrand systemCarBrand);

	/**
	 * 修改汽车品牌(修改+删除：状态)
	 * 
	 * @param systemCarBrand
	 * @return
	 */
	public boolean udpateSystemCarBrand(DataCarBrand systemCarBrand);

	/**
	 * 根据id获取汽车品牌
	 * 
	 * @param id
	 * @return
	 */
	public DataCarBrand getSystemCarBrandById(Integer id);

}
