package com.lovecws.mumu.data.car.service;

import com.lovecws.mumu.data.car.entity.DataCarSizeType;

import java.util.List;

public interface DataCarSizetypeService {
	/** ========app后台接口======= */
	/**
	 * 根据推荐的状态，获取汽车型号列表（1、推荐；2、不推荐）
	 * 
	 * @param recomm
	 * @return
	 */
	public List<DataCarSizeType> getSystemCarSizetypeByRecomm(String recomm);

	/** ========web后台接口======= */
	/**
	 * 获取所有的汽车型号
	 * 
	 * @return
	 */
	public List<DataCarSizeType> getAllSystemCarSizeType();
}
