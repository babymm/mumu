package com.lovecws.mumu.common.excel.beans;

/**
 * excel 类型
 * @author lovecws
 */
public enum ExcelTypeEnum {

	/**
	 * xls 格式excel
	 */
	XLS("application/vnd.ms-excel"),

	/**
	 * xls 格式excel
	 */
	XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

	public String type;

	private ExcelTypeEnum(String type) {
		this.type = type;
	}
	
	public String value(){
		return type;
	}

}
