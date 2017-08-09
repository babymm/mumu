package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.system.dao.SysDDLDao;
import com.lovecws.mumu.system.entity.SysDDL;
import com.lovecws.mumu.system.service.SysDDLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
public class SysDDLServiceImpl implements SysDDLService {

	@Autowired
	private SysDDLDao systemDDLDao;

	@Override
	public List<SysDDL> getSystemDDLs() {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("status", PublicEnum.NORMAL.value());
		return systemDDLDao.listByColumn(paramMap);
	}

	@Override
	public SysDDL getSystemDDLById(String ddlId) {
		return systemDDLDao.getById(ddlId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	public SysDDL addSystemDDL(SysDDL systemDDL) {
		systemDDL.setStatus(PublicEnum.NORMAL.value());
		systemDDL.setCreateTime(new Date());
		return systemDDLDao.insert(systemDDL);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	public boolean updateSystemDDL(SysDDL systemDDL) {
		return systemDDLDao.update(systemDDL)>0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	public boolean deleteSystemDDL(String ddlId) {
		return systemDDLDao.delete(ddlId)>0;
	}

	@Override
	public PageBean<SysDDL> listPage(String ddlCode,String parentCode,String withDDLTables, PageParam pageParam) {
		PageBean<SysDDL> pageBean=null;
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("status", PublicEnum.NORMAL.value());
		paramMap.put("ddlCode", ddlCode);
		paramMap.put("parentCode", parentCode);
		paramMap.put("withDDLTables", withDDLTables);
		return systemDDLDao.listPage(pageParam, paramMap);
	}

	@Override
	public List<SysDDL> getSystemDDLByCondition(String ddlCode) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("status", PublicEnum.NORMAL.value());
		paramMap.put("parentCode", ddlCode);
		return systemDDLDao.listByColumn(paramMap);
	}

	@Override
	public List<SysDDL> getSystemDDLByParentCode(List<String> parentCode) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("status", PublicEnum.NORMAL.value());
		paramMap.put("list", parentCode);
		return systemDDLDao.selectList("getSystemDDLByParentCode",paramMap);
	}

	@Override
	public List<SysDDL> getSystemDDLByParentCodes(String parentCodes) {
		String[] parentCodeStr = parentCodes.split(",");
		List<String> parentCodeList = new ArrayList<String>();
		parentCodeList = Arrays.asList(parentCodeStr);
		return getSystemDDLByParentCode(parentCodeList);
	}
}
