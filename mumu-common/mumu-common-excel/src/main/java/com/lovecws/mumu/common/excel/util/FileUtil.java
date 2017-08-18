package com.lovecws.mumu.common.excel.util;

public class FileUtil {

	/**
	 * 获取文件路径后缀
	 * @return
	 */
	public static String getFileExtension(String filePath){
		if(filePath==null||"".equals(filePath)){
			return null;
		}
		int lastIndexOf = filePath.lastIndexOf(".");
		if(lastIndexOf!=-1){
			String extension = filePath.substring(lastIndexOf+1);
			return extension;
		}
		return null;
	}
}
