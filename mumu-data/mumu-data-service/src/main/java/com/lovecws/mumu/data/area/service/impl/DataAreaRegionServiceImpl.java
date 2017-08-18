package com.lovecws.mumu.data.area.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lovecws.mumu.data.area.dao.DataAreaRegionDao;
import com.lovecws.mumu.data.area.entity.DataAreaRegion;
import com.lovecws.mumu.data.area.service.DataAreaRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.SUPPORTS,readOnly=true)
public class DataAreaRegionServiceImpl implements DataAreaRegionService{
	
	@Autowired
	private DataAreaRegionDao dataAreaRegionDao;

	@Override
	public List<DataAreaRegion> getAllAreaByCityCode(String cityCode) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("cityCode", cityCode);
		return dataAreaRegionDao.listByColumn(paramMap);
	}

}
