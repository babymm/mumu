package com.lovecws.mumu.system.service;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.system.entity.SysUserLog;

import java.util.List;

public interface SysUserLogService {

	/**
	 * 分页获取数据
	 * @param startDate 日志开始时间
	 * @param endDate 日志结束时间
	 * @param pageNum 开始索引
	 * @param pageSize 一页的数量
	 * @return
	 */
	public PageBean<SysUserLog> listPage(String startDate, String endDate, int pageNum, int pageSize);

	
	/**
	 * 保存日志
	 * @param sysUserLog 系统日志
	 */
	public void addSysUserLog(SysUserLog sysUserLog);

	/**
	 * 删除用户操作日志
	 * @param userLogId 日志id
	 */
	public void deleteSysUserLogById(String userLogId);


	/**
	 * 日志统计数据
	 * @return
	 */
	public List<SysUserLog> getLogStatisticsData();

}
