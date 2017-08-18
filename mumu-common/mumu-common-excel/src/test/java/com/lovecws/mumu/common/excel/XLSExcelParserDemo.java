package com.lovecws.mumu.common.excel;

import com.lovecws.mumu.common.excel.beans.ExcelParserBean;
import com.lovecws.mumu.common.excel.parser.ExcelParser;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class XLSExcelParserDemo {

	@Test
	public void parser(){
		ExcelParser parser=new ExcelParser();
		try {
			ExcelParserBean excelParserBean = parser.parser("D:\\crawler4j\\carnews\\20170321092615.xls");
			//System.out.println(excelParserBean);
			for (List<Object> list : excelParserBean.getData()) {
				for (Object object : list) {
					System.out.print(object);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void test(){

	}
}
