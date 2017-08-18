package com.lovecws.mumu.common.excel.beans;

import java.io.InputStream;
import java.util.List;

/**
 * excel实体
 * @author lovecws
 */
public class ExcelParserBean {

	// 表格类型
	private ExcelTypeEnum excelType;

	// 输入流
	private InputStream input;

	// 待解析文件
	private String filePath;

	// 表格请求头
	private List<Object> headers = null;

	// 表格请求数据
	private List<List<Object>> data = null;

	
	public ExcelParserBean() {
		super();
	}

	public ExcelParserBean(ExcelTypeEnum excelType, InputStream input) {
		super();
		this.excelType = excelType;
		this.input = input;
	}

	public ExcelParserBean(String filePath) {
		super();
		this.filePath = filePath;
	}

	public ExcelTypeEnum getExcelType() {
		return excelType;
	}

	public void setExcelType(ExcelTypeEnum excelType) {
		this.excelType = excelType;
	}

	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<Object> getHeaders() {
		return headers;
	}

	public void setHeaders(List<Object> headers) {
		this.headers = headers;
	}

	public List<List<Object>> getData() {
		return data;
	}

	public void setData(List<List<Object>> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ExcelParserBean [excelType=" + excelType + ", input=" + input + ", filePath=" + filePath + ", headers="
				+ headers + ", data=" + data + "]";
	}

}
