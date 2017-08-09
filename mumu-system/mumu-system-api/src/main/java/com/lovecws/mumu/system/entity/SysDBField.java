package com.lovecws.mumu.system.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库字段属性
 * 
 * @author lgan
 */
public class SysDBField {

	private String tableName;// 表名
	private String fieldName;// 字段名称
	private String fieldType;// 字段类型 int float double long string blob clob
	private int fieldSize;// 字段的长度
	private String remarks;// 字段的备注

	public SysDBField() {
		super();
	}
	
	public SysDBField(ResultSet rs) {
		try {
			this.tableName = rs.getString("TABLE_NAME");
			this.fieldName = rs.getString("COLUMN_NAME");
			this.fieldType = rs.getString("TYPE_NAME");
			this.fieldSize = rs.getInt("COLUMN_SIZE");
			this.remarks = rs.getString("REMARKS");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public SysDBField(String tableName, String fieldName, String fieldType, int fieldSize, String remarks) {
		super();
		this.tableName = tableName;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldSize = fieldSize;
		this.remarks = remarks;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public int getFieldSize() {
		return fieldSize;
	}

	public void setFieldSize(int fieldSize) {
		this.fieldSize = fieldSize;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "SysDBField [tableName=" + tableName + ", fieldName=" + fieldName + ", fieldType=" + fieldType
				+ ", fieldSize=" + fieldSize + ", remarks=" + remarks + "]";
	}
	
}
