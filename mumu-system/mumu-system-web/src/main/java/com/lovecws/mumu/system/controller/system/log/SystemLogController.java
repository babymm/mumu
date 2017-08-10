package com.lovecws.mumu.system.controller.system.log;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.system.entity.SysUserLog;
import com.lovecws.mumu.system.service.SysUserLogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/log")
public class SystemLogController {

	private static final Logger log=Logger.getLogger(SystemLogController.class);
	@Autowired
	private SysUserLogService userLogService;

	/**
	 * 日志列表
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String list() {
		return "system/log/index";
	}

	/**
	 * 分页获取日志列表
	 * @param startDate 日志开始时间
	 * @param endDate 日志结束时间
	 * @param beginIndex 开始索引
	 * @param pageSize 一页的数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/page" })
	public Map<String, Object> page(String startDate, String endDate, int beginIndex, int pageSize) {
		// 分页查询
		PageBean<SysUserLog> pageBean = userLogService.listPage(startDate,endDate, beginIndex / pageSize + 1, pageSize);
		Map<String, Object> page = new HashMap<String, Object>();
		page.put("total", pageBean.getTotalCount());
		page.put("rows", pageBean.getRecordList());
		return page;
	}
	
	/**
	 * 删除日志
	 * @param userLogId 日志id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete/{userLogId}",method=RequestMethod.DELETE)
	public ResponseEntity logDelete(@PathVariable String userLogId){
		try {
			userLogService.deleteSysUserLogById(userLogId);
			return new ResponseEntity();
		} catch (Exception e) {
			log.error(e);
			return new ResponseEntity(500, "删除日志出现异常", null);
		}
	}
	
	/**
	 * 日志统计
	 * @return
	 */
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public String logStatistics(){
		return "system/log/statistics";
	}
	
	/**
	 * 日志统计数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/statisticsData", method = RequestMethod.GET)
	public List<Object> logStatisticsData(){
		List<SysUserLog> data=userLogService.getLogStatisticsData();
		List<Object> list=new ArrayList<Object>();
		for (SysUserLog sysUserLog : data) {
			list.add(new Object[]{sysUserLog.getLogTime().getTime(),sysUserLog.getLogCount()});
		}
		return list;
	}
}
