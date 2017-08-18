package com.lovecws.mumu.common.excel.parser;

import com.lovecws.mumu.common.excel.beans.ExcelParserBean;

import java.io.IOException;
import java.io.InputStream;


public interface Parser {

	/**
	 * 解析excel
	 * @param filePath excel路径
	 * @return
	 * @throws IOException
	 */
	public ExcelParserBean parser(String filePath) throws IOException;

	/**
	 * 解析excel
	 * @param input excel流
	 * @return
	 * @throws IOException
	 */
	public ExcelParserBean parser(InputStream input) throws IOException;

	/**
	 * 解析excel
	 * @param parserBean
	 * @return
	 */
	public ExcelParserBean parser(ExcelParserBean parserBean);

}
