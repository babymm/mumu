package com.lovecws.mumu.common.core.utils;

public class ImageUploadConstants {
	
	public static String ZIMG_UPLOAD_URL="";
	static{
		PropertiesLoader loader=new PropertiesLoader("classpath:properties/zimg.properties");
		ZIMG_UPLOAD_URL=loader.getProperty("ZIMG_UPLOAD_URL");
	}

}
