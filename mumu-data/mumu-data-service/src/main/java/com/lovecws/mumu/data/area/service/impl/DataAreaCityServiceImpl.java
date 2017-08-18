package com.lovecws.mumu.data.area.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lovecws.mumu.data.area.dao.DataAreaCityDao;
import com.lovecws.mumu.data.area.entity.DataAreaCity;
import com.lovecws.mumu.data.area.service.DataAreaCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.SUPPORTS,readOnly=true)
public class DataAreaCityServiceImpl implements DataAreaCityService{
	
	@Autowired
	private DataAreaCityDao dataAreaCityDao;

	@Override
	public List<DataAreaCity> getAllCityByProvinceCode(String provinceCode) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("provinceCode", provinceCode);
		return dataAreaCityDao.listByColumn(paramMap);
	}

}
