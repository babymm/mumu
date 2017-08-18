package com.lovecws.mumu.data.area.service;

import com.lovecws.mumu.data.area.entity.DataAreaRegion;

import java.util.List;

public interface DataAreaRegionService {

	/**
	 * 根据城市code，获取城市下的所有区
	 * @param cityCode
	 * @return
	 */
	public List<DataAreaRegion> getAllAreaByCityCode(String cityCode);
}
