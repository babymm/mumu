package com.lovecws.mumu.common.core.utils;

import org.apache.commons.lang3.StringUtils;

public class IPAddressUtil {

	/**
	 * 将ip地址转换为二进制字符串
	 * @param ip 255.255.255.255
	 * @return
	 */
	public static final String toBinaryString(String ip) {
		if (ip == null || "".equals(ip)) {
			return null;
		}
		String[] ipAddress = StringUtils.split(ip, ".");
		StringBuilder builder = new StringBuilder();
		for (String ipAdd : ipAddress) {
			String binaryString = Integer.toBinaryString(Integer.parseInt(ipAdd));
			binaryString = StringUtils.leftPad(binaryString, 8, "0");
			builder.append(binaryString);
		}
		return builder.toString();
	}

	/**
	 * 将二进制字符串转化为int
	 * @param bi 11111111111111111111111111111111
	 * @return
	 */
	public static final long toLong(String bi) {
		int len = bi.length();
		long sum = 0;
		int tmp, max = len - 1;
		for (int i = 0; i < len; ++i) {
			tmp = bi.charAt(i) - '0';
			sum += tmp * Math.pow(2, max--);
		}
		return sum;
	}

	/**
	 * 将ip地址转换为int
	 * @param ip ip地址
	 * @return
	 */
	public static final long transformIpAddressToLong(String ip){
		String binaryString = toBinaryString(ip);
		if(binaryString==null){
			return 0;
		}
		return toLong(binaryString);
	}
	
	/**
	 * int数字转换为ip地址
	 * @param ip ip地址
	 * @return
	 */
	public static final String transformLongToIpAddress(int intIpAddress){
		String binaryString = Integer.toBinaryString(intIpAddress);
		binaryString = StringUtils.leftPad(binaryString, 32, "0");
		StringBuilder ipAddress=new StringBuilder();
		for (int i = 0; i < 4; i++) {
			String substring = binaryString.substring(i*8, (i+1)*8);
			ipAddress.append(toLong(substring));
			if(i<3){
				ipAddress.append(".");
			}
		}
		return ipAddress.toString();
	}
	
	public static void main(String[] args) {
		//long transformIpAddressToInt = transformIpAddressToInt("128.0.0.0");
		//10000000000000000000011111111111 2147483647

		long transformIpAddressToInt = transformIpAddressToLong("255.255.255.255");
		//10000000000000000000011111111111  2147483647
		System.out.println(transformIpAddressToInt);
		
		String ipAddress = transformLongToIpAddress(2147483647);
		System.out.println(ipAddress);
	}
}
