package com.lovecws.mumu.system.dao;


import com.lovecws.mumu.system.entity.SysDBField;
import com.lovecws.mumu.system.entity.SysDBTable;

import java.util.List;

public interface SysCommonDao {

	public List<SysDBTable> getAllTable();

	public List<SysDBField> getAllField(String tableName);

	public List<List<Object>> getAllData(String tableName, String fields, String params);

}
