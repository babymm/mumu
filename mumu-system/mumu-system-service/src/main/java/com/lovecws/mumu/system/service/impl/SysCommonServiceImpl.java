package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.system.dao.SysCommonDao;
import com.lovecws.mumu.system.entity.SysDBField;
import com.lovecws.mumu.system.entity.SysDBTable;
import com.lovecws.mumu.system.service.SysCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class SysCommonServiceImpl implements SysCommonService {

	@Autowired
	private SysCommonDao commonDao;
	
	@Override
	public List<SysDBTable> getAllTable(){
		return commonDao.getAllTable();
	}
	
	@Override
	public List<SysDBField> getAllField(String tableName){
		return commonDao.getAllField(tableName);
	}

	@Override
	public List<List<Object>> getAllData(String tableName, String fields,String params) {
		return commonDao.getAllData(tableName, fields,params);
	}
	
}
