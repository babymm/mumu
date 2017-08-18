package com.lovecws.mumu.common.excel.generater;

import com.lovecws.mumu.common.excel.beans.ExcelGeneraterBean;

import java.util.List;


public interface Generater {

	/**
	 * 默认生成一个xls格式，每个表格100条数据的excel，并且将weokbook返回给用户自己处理
	 * @param sheetName 表格名称
	 * @param headers excel头元素
	 * @param data excel数据
	 * @return
	 */
	public ExcelGeneraterBean create(String sheetName, List<String> headers, List<List<Object>> data);

	/**
	 * 默认生成一个xls格式的excel，并且将weokbook返回给用户自己处理
	 * @param sheetName 表格名称
	 * @param headers excel头元素
	 * @param data excel数据
	 * @param pageSize 每个表格的数据量
	 * @return
	 */
	public ExcelGeneraterBean create(String sheetName, List<String> headers, List<List<Object>> data, int pageSize);

	/**
	 * 并且将weokbook返回给用户自己处理
	 * @param sheetName 表格名称
	 * @param headers excel头元素
	 * @param data excel数据
	 * @param pageSize 每个表格的数据量
	 * @param filePath 保存excel路径
	 * @return
	 */
	public ExcelGeneraterBean create(String sheetName, List<String> headers, List<List<Object>> data, int pageSize, String filePath);

	/**
	 * 生成excel
	 * @param generaterBean
	 * @return
	 */
	public ExcelGeneraterBean create(ExcelGeneraterBean generaterBean);

}
