package com.lovecws.mumu.common.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

/**
 * 夺宝算法计算
 * @author ganliang
 */
public class DolphinUtil {

	public static final SimpleDateFormat SDF = new SimpleDateFormat("HHmmss");
	
	/**
	 * 简单算法 计算夺宝
	 * @param base 夺宝基数 10000001
	 * @param totalCount
	 * @return
	 */
	public static final String simleAlgorithm(int base,int totalCount){
		int dolphinCode = RandomUtils.nextInt(base, base+totalCount);
		return String.valueOf(dolphinCode);
	}
	
	/**
	 * 计算夺宝
	 * @param dolphinOrders 夺宝订单
	 * @param base 基数 10000001
	 * @param totalCount 总数量
	 * @return
	 */
	public static final String algorithm(List<Date> dates,int base,int totalCount){
		if(dates==null||dates.size()==0){
			return simleAlgorithm(base, totalCount);
		}
		dates.add(new Date());
		int sum=0;
		for (Date date : dates) {
			sum+=Integer.parseInt(SDF.format(date));
		}
		int dolphinCode=sum%totalCount+base;
		return String.valueOf(dolphinCode);
	}
}
