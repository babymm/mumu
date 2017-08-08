package com.lovecws.mumu.common.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * @desc 本地ip工具类
 * @author ganliang
 * @version 2016年8月15日 上午9:27:05
 */
public final class WebUtil {
	private static Logger logger = Logger.getLogger(WebUtil.class);

	private WebUtil() {
	}

	/** 获取机器名 */
	public static final String getHostName() {
		String hostName = "";
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			logger.error(e.getMessage(), e);
		}
		return hostName;
	}

	/** 获取网卡序列号 */
	public static final String getDUID() {
		String address = "";
		String command = "cmd.exe /c ipconfig /all";
		try {
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf("DUID") > 0) {
					int index = line.indexOf(":");
					index += 2;
					address = line.substring(index);
					break;
				}
			}
			br.close();
		} catch (IOException e) {
		}
		return address;
	}

	/**
	 * @Title: getRemoteIP @Description: 获取到远程客户端的ip @param @param
	 * request @param @return @return String 返回类型 @throws
	 */
	public static String getRemoteIP(HttpServletRequest request) {
		String ip = request.getRemoteHost();
		return ip;
	}

	/**
	 * 获取链接地址
	 * @param request
	 * @return
	 */
	public static String getReferenceUrl(HttpServletRequest request) {
		return request.getHeader("refer");
	}

	/**
	 * 获取计算机的ip地址
	 * @return
	 */
	public static String getIpAddr() {
		String ipAddress = null;
		try {
			InetAddress address = InetAddress.getLocalHost();
			ipAddress = address.getHostAddress();
		} catch (UnknownHostException e) {
			logger.error(e.getMessage(), e);
		}
		return ipAddress;
	}
}
