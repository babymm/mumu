package com.lovecws.mumu.common.excel.generater;

import com.lovecws.mumu.common.excel.beans.ExcelGeneraterBean;
import com.lovecws.mumu.common.excel.beans.ExcelTypeEnum;
import com.lovecws.mumu.common.excel.util.FileUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 生成excel
 * @author lovecws
 */
public class ExcelGenerater implements Generater{
	
	@Override
	public ExcelGeneraterBean create(String sheetName,List<String> headers,List<List<Object>> data){
		return create(sheetName, headers, data, ExcelGeneraterBean.DEFAULT_PAGE_SIZE);
	}
	
	@Override
	public ExcelGeneraterBean create(String sheetName,List<String> headers,List<List<Object>> data,int pageSize){
		return create(sheetName, headers, data, pageSize, null);
	}

	@Override
	public ExcelGeneraterBean create(String sheetName,List<String> headers,List<List<Object>> data,int pageSize,String filePath){
		ExcelGeneraterBean generaterBean=new ExcelGeneraterBean();
		generaterBean.setSheetName(sheetName);
		generaterBean.setHeaders(headers);
		generaterBean.setData(data);
		generaterBean.setPageSize(pageSize);
		generaterBean.setFilePath(filePath);
		return create(generaterBean);
	}

	@Override
	public ExcelGeneraterBean create(ExcelGeneraterBean generaterBean) {
		setDefaultExcelType(generaterBean);
		
		setDefaultPageSize(generaterBean);
		
		createWorkbook(generaterBean);
		createSheet(generaterBean);
		return generaterBean;
	}

	private void setDefaultExcelType(ExcelGeneraterBean generaterBean) {
		//获取文件后缀
		String filePath = generaterBean.getFilePath();
		String fileExtension = FileUtil.getFileExtension(filePath);
		
		ExcelTypeEnum excelType = generaterBean.getExcelType();
		//默认为xls格式excel
		if(excelType==null&&fileExtension==null){
			excelType=ExcelTypeEnum.XLS;
		}else if(excelType==null&&fileExtension!=null){
			if(fileExtension.equalsIgnoreCase(ExcelTypeEnum.XLS.name())){
				excelType=ExcelTypeEnum.XLS;
			}else if(fileExtension.equalsIgnoreCase(ExcelTypeEnum.XLSX.name())){
				excelType=ExcelTypeEnum.XLSX;
			}
		}
		generaterBean.setExcelType(excelType);		
	}
	
	private void setDefaultPageSize(ExcelGeneraterBean generaterBean) {
		//默认一页数量
		int pageSize = generaterBean.getPageSize();
		if(pageSize==0){
			generaterBean.setPageSize(ExcelGeneraterBean.DEFAULT_PAGE_SIZE);
		}			
	}
	
	private void createWorkbook(ExcelGeneraterBean generaterBean) {
		Workbook wb=null;
		ExcelTypeEnum excelType = generaterBean.getExcelType();
		if(ExcelTypeEnum.XLS.equals(excelType)){
			wb = new HSSFWorkbook();
		}else if(ExcelTypeEnum.XLSX.equals(excelType)){
			wb = new XSSFWorkbook();
		}else{
			wb = new HSSFWorkbook();
		}
		generaterBean.setWorkbook(wb);
	}
	
	/**
	 * 获取列的样式
	 * @param wb
	 * @return
	 */
	private CellStyle getCellStyle(Workbook wb) {
		CreationHelper creationHelper = wb.getCreationHelper();
		//设置excel的样式
		CellStyle cellStyle = wb.createCellStyle();
	    cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));
	    cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
	    cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    return cellStyle;
	}

	/**
	 * 设置head样式
	 */
	private CellStyle headFont(Workbook wb) {
		Font headFont = wb.createFont();
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 11);
		CellStyle headStyle = wb.createCellStyle();
		headStyle.setFont(headFont);
		headStyle.setBorderTop((short) 1);
		headStyle.setBorderRight((short) 1);
		headStyle.setBorderBottom((short) 1);
		headStyle.setBorderLeft((short) 1);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		//CreationHelper creationHelper = wb.getCreationHelper();
		//headStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));
		//headStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd hh:mm:ss"));
		DataFormat format = wb.createDataFormat();
		headStyle.setDataFormat(format.getFormat("yyyy-MM-dd hh:mm:ss"));
		return headStyle;
	}

	/**
	 * 创建表格
	 * @param generaterBean
	 */
	private void createSheet(ExcelGeneraterBean generaterBean){
		Workbook wb = generaterBean.getWorkbook();
		List<String> headers = generaterBean.getHeaders();
		List<List<Object>> data = generaterBean.getData();
		
		CellStyle cellStyle = headFont(wb);
		 //分页表格
	    int pageNum=1;
	    int begin=0;
	    int end=100;
	    int page_size=generaterBean.getPageSize();
	    if(data.size()%page_size==0){
	    	pageNum=data.size()/page_size;
	    }else{
	    	pageNum=data.size()/page_size+1;
	    }

	    for (int index = 0; index < pageNum; index++) {
	    	begin=index*page_size;
	    	//最后一个sheet
	    	if(pageNum-index==1){
	    		end=data.size();
	    	}else{
	    		end=(index+1)*page_size;
	    	}
	    	Sheet sheet = wb.createSheet(generaterBean.getSheetName()+index);
		    //设置标题头的样式
		    Header header = sheet.getHeader();
		    header.setCenter("Center Header");		    header.setLeft("Left Header");
		    header.setRight(HSSFHeader.font("Stencil-Normal", "Italic") + HSSFHeader.fontSize((short) 16) + "Right w/ Stencil-Normal Italic font and size 16");
		    int rowIndex=0;
		    //添加表头
		    if(headers!=null){
		    	Row headerRow = sheet.createRow(rowIndex++);
		    	for (int i = 0; i < headers.size(); i++) {
			    	Cell createCell = headerRow.createCell(i);
			    	createCell.setCellValue(headers.get(i));
			    	createCell.setCellStyle(cellStyle);
				}
		    }
	        //添加表格数据
	        for (int i = begin; i < end; i++) {
	        	//创建一行
	        	Row row = sheet.createRow(rowIndex++);
	        	List<Object> list=data.get(i);
	        	for (int j = 0; j < list.size(); j++) {
	        		//创建一列
	        		Cell cell = row.createCell(j);
					/*cell.setCellStyle(cellStyle);*/
	        		Object obj = list.get(j);
	        		if(obj instanceof Boolean){
	        			cell.setCellValue((Boolean)obj);
	        		}else if(obj instanceof Date){
	        			cell.setCellValue((Date)obj);
	        		}else if(obj instanceof Double){
	        			cell.setCellValue((Double)obj);
	        		}else{
	        			String cellValue="";
	        			if(obj!=null){
	        				cellValue=obj.toString();
	        			}
	        			//当列大小大于excel列的最大值
						int maxTextLength = SpreadsheetVersion.EXCEL2007.getMaxTextLength();
						if(cellValue.length()>maxTextLength){
	        				cellValue=cellValue.substring(0, maxTextLength);
	        			}
	        			cell.setCellValue(cellValue);
	        		}
				}
			}
	        sheet.setDefaultColumnWidth(25);
		}
		if (pageNum == 0) {
			Sheet sheet = wb.createSheet(generaterBean.getSheetName());
			//设置标题头的样式
			Header header = sheet.getHeader();
			header.setCenter("Center Header");
			header.setLeft("Left Header");
			header.setRight(HSSFHeader.font("Stencil-Normal", "Italic") + HSSFHeader.fontSize((short) 16) + "Right w/ Stencil-Normal Italic font and size 16");
			int rowIndex = 0;
			//添加表头
			if (headers != null) {
				Row headerRow = sheet.createRow(rowIndex++);
				for (int i = 0; i < headers.size(); i++) {
					Cell createCell = headerRow.createCell(i);
					createCell.setCellValue(headers.get(i));
					createCell.setCellStyle(cellStyle);
				}
			}
			sheet.setDefaultColumnWidth(25);
		}
	    saveFilePath(generaterBean);
	}

	private void saveFilePath(ExcelGeneraterBean generaterBean) {
		Workbook wb = generaterBean.getWorkbook();
		String filePath = generaterBean.getFilePath();
	    if(filePath!=null&&!"".equals(filePath)){
	    	try {
				wb.write(new FileOutputStream(new File(filePath)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					wb.close();
					generaterBean.setWorkbook(null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	    }
	}
}
