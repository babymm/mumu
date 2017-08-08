package com.lovecws.mumu.common.core.utils;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * @desc 文件工具类
 * @author ganliang
 * @version 2016年8月16日 下午5:00:18
 */
public class FileUtil {

	/**
	 * 删除文件
	 * @param files
	 */
	public static void deleteQuietly(File... files) {
		if (files == null) {
			return;
		}
		for (File file : files) {
			FileUtils.deleteQuietly(file);
		}
	}
	
	/**
	 * 删除文件
	 * @param files
	 */
	public static void deleteQuietly(String... files) {
		if (files == null) {
			return;
		}
		for (String file : files) {
			FileUtils.deleteQuietly(new File(file));
		}
	}
	
	/**
	 * 删除文件
	 * @param files
	 */
	public static void deleteQuietly(List<String> files) {
		if (files == null) {
			return;
		}
		for (String file : files) {
			FileUtils.deleteQuietly(new File(file));
		}
	}
}
