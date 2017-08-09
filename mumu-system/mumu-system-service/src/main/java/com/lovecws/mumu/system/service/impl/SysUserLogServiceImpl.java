package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.system.dao.SysUserLogDao;
import com.lovecws.mumu.system.entity.SysUserLog;
import com.lovecws.mumu.system.service.SysUserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class SysUserLogServiceImpl implements SysUserLogService {

	@Autowired
	private SysUserLogDao userLogDao;

	@Override
	public PageBean<SysUserLog> listPage(String startDate, String endDate, int pageNum, int pageSize) {
		PageParam pageParam=new PageParam(pageNum, pageSize);
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("startDate", startDate==null?"":startDate);
		paramMap.put("endDate", endDate==null?"":endDate);
		paramMap.put("userLogStatus", PublicEnum.NORMAL.value());
		return userLogDao.listPage(pageParam, paramMap);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void addSysUserLog(SysUserLog sysUserLog) {
		userLogDao.insert(sysUserLog);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteSysUserLogById(String userLogId) {
		userLogDao.delete(userLogId);
	}

	@Override
	public List<SysUserLog> getLogStatisticsData() {
		return userLogDao.selectList("logStatistics", new HashMap<String,Object>());
	}


}
