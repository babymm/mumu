package com.lovecws.mumu.common.core.utils;

import java.text.NumberFormat;

/**
 * 计算百分比
 * @author Administrator
 *
 */
public class DiliverPerUtil {

	public static String setDiliverPer(int diliverNum, int queryMailNum) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		String result = numberFormat.format((float) diliverNum / (float) queryMailNum * 100);
		return result;
	}
}
