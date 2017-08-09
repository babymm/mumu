package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.system.dao.SysOrganizeDao;
import com.lovecws.mumu.system.entity.SysOrganize;
import com.lovecws.mumu.system.service.SysOrganizeService;
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
public class SysOrganizeServiceImpl implements SysOrganizeService {

	@Autowired
	private SysOrganizeDao organizeDao;
	
	@Override
	public PageBean<SysOrganize> listPage(String orgName, int pageNum, int pageSize) {
		PageParam pageParam=new PageParam(pageNum, pageSize);
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("orgName", orgName);
		return organizeDao.listPage(pageParam, paramMap);
	}

	@Override
	@Transactional(readOnly=false)
	public SysOrganize addSysOrganize(SysOrganize organize) {
		organize.setCreateTime(new Date());
		organize.setOrgStatus(PublicEnum.NORMAL.value());
		return organizeDao.insert(organize);
	}

	@Override
	public List<SysOrganize> querySysOrganizeByCondition(String orgName) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("orgName", orgName);
		return organizeDao.listByColumn(paramMap);
	}

	@Override
	public SysOrganize getSysOrganizeById(String orgId) {
		return organizeDao.getById(orgId);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateSysOrganize(SysOrganize organize) {
		organize.setEditTime(new Date());
		organizeDao.update(organize);
	}
	
	@Override
	@Transactional(readOnly=false)
	public void deleteSysOrganizeById(String orgId) {
		organizeDao.delete(orgId);
	}
}
