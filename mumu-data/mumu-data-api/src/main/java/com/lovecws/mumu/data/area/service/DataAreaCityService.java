package com.lovecws.mumu.data.area.service;

import com.lovecws.mumu.data.area.entity.DataAreaCity;

import java.util.List;

public interface DataAreaCityService {

	/**
	 * 根据省份code，获取省下的所有城市
	 * @param provinceCode
	 * @return
	 */
	public List<DataAreaCity> getAllCityByProvinceCode(String provinceCode);
}
