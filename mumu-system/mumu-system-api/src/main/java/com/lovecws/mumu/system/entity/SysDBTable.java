package com.lovecws.mumu.system.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 数据库表的属性
 * @author lgan
 * @Param tableName
 * @Param tableType
 * @Param remarks
 */
public class SysDBTable {

	private String tableName;// 表名
	private String tableType;// 表的类型 view ,table,system table
	private String remarks;// 表的描述

	public SysDBTable() {
		super();
	}
	public SysDBTable(ResultSet rs) {
		try {
			this.tableName = rs.getString("TABLE_NAME");
			this.tableType =rs.getString("TABLE_TYPE");
			this.remarks = rs.getString("REMARKS");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public SysDBTable(String tableName, String tableType, String remarks) {
		super();
		this.tableName = tableName;
		this.tableType = tableType;
		this.remarks = remarks;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "SystemDBTable [tableName=" + tableName + ", tableType=" + tableType + ", remarks=" + remarks + "]";
	}

}
