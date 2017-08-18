package com.lovecws.mumu.data.ip.entity;

import com.lovecws.mumu.common.core.entity.PersistentEntity;

/**
 * ip地址数据库
 * @author ganliang
 */
public class DataIPAddress extends PersistentEntity {

	private static final long serialVersionUID = -5003034644049906657L;

	private long sip;//开始ip
	private long eip;//结束ip
	private String startIp;// 起始ip地址
	private String endIp;// 结束ip地址
	private String cityName;// ip城市地名称
	private String companyName;// 运营商公司名称

	public String getStartIp() {
		return startIp;
	}

	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}

	public String getEndIp() {
		return endIp;
	}

	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public long getSip() {
		return sip;
	}

	public void setSip(long sip) {
		this.sip = sip;
	}

	public long getEip() {
		return eip;
	}

	public void setEip(long eip) {
		this.eip = eip;
	}
}
