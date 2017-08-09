package com.lovecws.mumu.system.service;


import com.lovecws.mumu.system.entity.SysDBField;
import com.lovecws.mumu.system.entity.SysDBTable;

import java.util.List;

public interface SysCommonService {

	/**
	 * 获取当前连接数据库所有的表
	 * @return
	 */
	public List<SysDBTable> getAllTable();

	/**
	 * 获取表下的所有字段
	 * @param tableName 表名
	 * @return
	 */
	public List<SysDBField> getAllField(String tableName);

	/**
	 * 获取表下的所有数据
	 * @param tableName 表名(sys_user)
	 * @param fields 字段(id,name,status)
	 * @param params 参数（where status=1）
	 * @return
	 */
	public List<List<Object>> getAllData(String tableName, String fields, String params);
}
