package com.lovecws.mumu.common.excel.beans;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * excel实体
 * 
 * @author lovecws
 */
public class ExcelGeneraterBean {

	public static final int DEFAULT_PAGE_SIZE = 100;

	// 表格类型
	private ExcelTypeEnum excelType;

	// 待解析文件
	private String filePath;

	private int pageSize;// 一页数量

	// 表格名称
	private String sheetName;

	// 表格请求头
	private List<String> headers = null;

	// 表格请求数据
	private List<List<Object>> data = null;

	// excel 工作簿
	private Workbook workbook;

	public ExcelGeneraterBean() {
		super();
	}

	public ExcelGeneraterBean(ExcelTypeEnum excelType, String sheetName, List<String> headers,List<List<Object>> data) {
		super();
		this.excelType = excelType;
		this.sheetName = sheetName;
		this.headers = headers;
		this.data = data;
	}

	public ExcelGeneraterBean(ExcelTypeEnum excelType, int pageSize, String sheetName, List<String> headers,List<List<Object>> data) {
		super();
		this.excelType = excelType;
		this.pageSize = pageSize;
		this.sheetName = sheetName;
		this.headers = headers;
		this.data = data;
	}

	public ExcelGeneraterBean(String filePath, String sheetName, List<String> headers, List<List<Object>> data) {
		super();
		this.filePath = filePath;
		this.sheetName = sheetName;
		this.headers = headers;
		this.data = data;
	}

	public ExcelGeneraterBean(String filePath, int pageSize, String sheetName, List<String> headers,List<List<Object>> data) {
		super();
		this.filePath = filePath;
		this.pageSize = pageSize;
		this.sheetName = sheetName;
		this.headers = headers;
		this.data = data;
	}

	public ExcelTypeEnum getExcelType() {
		return excelType;
	}

	public void setExcelType(ExcelTypeEnum excelType) {
		this.excelType = excelType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public List<List<Object>> getData() {
		return data;
	}

	public void setData(List<List<Object>> data) {
		this.data = data;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

	@Override
	public String toString() {
		return "ExcelGeneraterBean [excelType=" + excelType + ", filePath=" + filePath + ", pageSize=" + pageSize
				+ ", sheetName=" + sheetName + ", headers=" + headers + ", data=" + data + ", workbook=" + workbook
				+ "]";
	}
}
