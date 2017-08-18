package com.lovecws.mumu.common.excel.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.lovecws.mumu.common.excel.beans.ExcelParserBean;
import com.lovecws.mumu.common.excel.beans.ExcelTypeEnum;
import com.lovecws.mumu.common.excel.util.FileUtil;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel 解析
 * @author lovecws
 */
public class ExcelParser implements Parser{

	@Override
	public ExcelParserBean parser(String filePath) throws IOException{
		ExcelParserBean parserBean=new ExcelParserBean();
		parserBean.setFilePath(filePath);
		File file = new File(filePath);
		if(!file.exists()){
			throw new IllegalArgumentException("excel文件路径不正确。");
		}
		parserBean.setInput(new FileInputStream(new File(filePath)));
		return parser(parserBean);
	}
	
	@Override
	public ExcelParserBean parser(InputStream input) throws IOException{
		ExcelParserBean parserBean=new ExcelParserBean();
		parserBean.setInput(input);
		return parser(parserBean);
	}
	
	@Override
	public ExcelParserBean parser(ExcelParserBean parserBean) {
		setDefaultExcelType(parserBean);
		Workbook workbook = createWorkbook(parserBean);
		List<List<Object>> parseSheet = parseSheet(workbook);
		parserBean.setData(parseSheet);
		if(parseSheet!=null&&parseSheet.size()>0){
			parserBean.setHeaders(parseSheet.get(0));
		}
		realeaseResources(parserBean,workbook);
		return parserBean;
	}

	private void setDefaultExcelType(ExcelParserBean parserBean) {
		//获取文件后缀
		String filePath = parserBean.getFilePath();
		String fileExtension = FileUtil.getFileExtension(filePath);
		
		ExcelTypeEnum excelType = parserBean.getExcelType();
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
		parserBean.setExcelType(excelType);		
	}
	
	private Workbook createWorkbook(ExcelParserBean parserBean) {
		Workbook wb=null;
		ExcelTypeEnum excelType = parserBean.getExcelType();
		try {
			if(ExcelTypeEnum.XLS.equals(excelType)){
				wb = new HSSFWorkbook(parserBean.getInput());
			}else if(ExcelTypeEnum.XLSX.equals(excelType)){
				wb = new XSSFWorkbook(parserBean.getInput());
			}else{
				wb = new HSSFWorkbook(parserBean.getInput());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}
	
	/**
	 * 解析表格
	 * @param workbook
	 * @return
	 */
	protected List<List<Object>> parseSheet(Workbook workbook) {
		List<List<Object>> data = new ArrayList<List<Object>>();
		Iterator<Sheet> sheetIterator = workbook.iterator();
		
		int sheetCount=0;
		List<Object> headerRowData=null;
		// 遍历excel多个sheet
		while (sheetIterator.hasNext()) {
			Sheet sheet = sheetIterator.next();
			Iterator<Row> rowIterator = sheet.iterator();
			int rowNumber=0;
			// 遍历行
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				List<Object> rowData = parseRow(row);

				//获取第一页 第一行数据
				if(sheetCount==0&&rowNumber==0){
					headerRowData=rowData;
				}
				//比較剩餘sheet第一行数据是否相等
				if(sheetCount>1&&rowNumber==0){
					if(compareRow(headerRowData,rowData)){
						continue;
					}
				}
				data.add(rowData);
				
				rowNumber++;
			}
			
			sheetCount++;
		}
		return data;
	}

	/**
	 * 比较两行数据是否相等
	 * @param headerRowData
	 * @param rowData
	 * @return
	 */
	private boolean compareRow(List<Object> headerRowData, List<Object> rowData) {
		if(headerRowData==null||headerRowData.size()==0||rowData==null||rowData.size()==0||headerRowData.size()!=rowData.size()){
			return false;
		}
		for (int i=0;i<headerRowData.size();i++){
			if(!String.valueOf(headerRowData.get(i)).equals(String.valueOf(rowData.get(i)))){
				return false;
			}
		}
		return true;
	}

	/**
	 * 解析行
	 * @param row
	 * @return
	 */
	protected List<Object> parseRow(Row row) {
		List<Object> rowData = new ArrayList<Object>();

		for (int i = 0; i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			Object cellObj=null;
			if(cell!=null){
				cellObj = parseCell(cell);
			}
			rowData.add(cellObj);
		}
		/*// 迭代 一行的各个单元格
		Iterator<Cell> cellIterator = row.iterator();
		// 遍历一行多列
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			Object cellObj = parseCell(cell);
			rowData.add(cellObj);
		}*/
		return rowData;
	}

	/**
	 * 解析单元格
	 * @param cell
	 * @return
	 */
	protected Object parseCell(Cell cell) {
		Object obj = null;
		int cellType = cell.getCellType();
		switch (cellType) {
		case Cell.CELL_TYPE_BOOLEAN:
			obj = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			// 处理日期格式、时间格式
            if (HSSFDateUtil.isCellDateFormatted(cell)) {  
            	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                Date date = cell.getDateCellValue();  
                obj = sdf.format(date);  
            } else if (cell.getCellStyle().getDataFormat() == 58) {  
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                double value = cell.getNumericCellValue();  
                Date date = org.apache.poi.ss.usermodel.DateUtil  
                        .getJavaDate(value);  
                obj = sdf.format(date);  
            } else {  
                double value = cell.getNumericCellValue();  
                CellStyle style = cell.getCellStyle();  
                DecimalFormat format = new DecimalFormat();  
                String temp = style.getDataFormatString();  
                // 单元格设置成常规  
                if (temp.equals("General")) {  
                    format.applyPattern("#");  
                }  
                obj = format.format(value);  
            }  
			break;
		default:
			obj = cell.getStringCellValue();
			break;
		}
		return obj;
	}
	
	private void realeaseResources(ExcelParserBean parserBean,Workbook workbook) {
		try {
			InputStream inputStream = parserBean.getInput();
			if(inputStream!=null){
				inputStream.close();
			}
			if(workbook!=null){
				workbook.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
