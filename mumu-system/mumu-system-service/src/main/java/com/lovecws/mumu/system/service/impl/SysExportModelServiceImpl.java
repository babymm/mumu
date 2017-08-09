package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.system.dao.SysExportModelDao;
import com.lovecws.mumu.system.entity.SysExportModel;
import com.lovecws.mumu.system.service.SysExportModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
public class SysExportModelServiceImpl implements SysExportModelService {

	@Autowired
	private SysExportModelDao sysExportModelDao;
	
	@Override
	public PageBean<SysExportModel> listPage(String modelName, int pageNum, int pageSize) {
		PageParam pageParam=new PageParam(pageNum, pageSize);
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("modelName", modelName);
		paramMap.put("ddlStatus", PublicEnum.NORMAL.value());
		return sysExportModelDao.listPage(pageParam, paramMap);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteById(String modelId) {
		sysExportModelDao.delete(modelId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void addExportModel(SysExportModel exportModel) {
		exportModel.setCreateTime(new Date());
		exportModel.setModelStatus(String.valueOf(PublicEnum.NORMAL.value()));
		sysExportModelDao.insert(exportModel);
	}

	@Override
	public SysExportModel getSysExportModelById(String modelId) {
		return sysExportModelDao.getById(modelId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateSysExportModel(SysExportModel exportModel) {
		exportModel.setEditTime(new Date());
		sysExportModelDao.update(exportModel);
	}

	@Override
	public List<SysExportModel> queryExportModelByCondition(String modelName) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("modelName", modelName);
		map.put("modelStatus", PublicEnum.NORMAL.value());
		return sysExportModelDao.listByColumn(map);
	}

}
