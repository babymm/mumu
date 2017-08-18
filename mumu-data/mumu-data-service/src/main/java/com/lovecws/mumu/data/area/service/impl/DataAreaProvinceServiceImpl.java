package com.lovecws.mumu.data.area.service.impl;

import java.util.HashMap;
import java.util.List;

import com.lovecws.mumu.data.area.dao.DataAreaProvinceDao;
import com.lovecws.mumu.data.area.entity.DataAreaProvince;
import com.lovecws.mumu.data.area.service.DataAreaProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.SUPPORTS,readOnly=true)
public class DataAreaProvinceServiceImpl implements DataAreaProvinceService {
	
	@Autowired
	private DataAreaProvinceDao dataAreaProvinceDao;

	@Override
	public List<DataAreaProvince> getAllProvinces() {
		return dataAreaProvinceDao.listByColumn(new HashMap<String,Object>());
	}

}
