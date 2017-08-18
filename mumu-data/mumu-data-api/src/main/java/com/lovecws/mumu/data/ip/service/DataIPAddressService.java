package com.lovecws.mumu.data.ip.service;

import com.lovecws.mumu.data.ip.entity.DataIPAddress;

import java.util.List;

public interface DataIPAddressService {

	/**
	 * 获取所有的ipaddress
	 * @return
	 */
	public List<DataIPAddress> getAllSystemIpAddress();
	
	/**
	 * 通过ip查询ip所属地
	 * @param ip ip地址
	 * @return
	 */
	public List<DataIPAddress> getSystemIpAddressByIp(String ip);
	
	/**
	 * 通过地理位置查询ip
	 * @param address 地理位置
	 * @return
	 */
	public List<DataIPAddress> getSystemIpAddressByAddress(String address);

	/**
	 * 重新构造ip地址库
	 * @param allSystemIpAddress
	 */
	public void addBatchSystemIPAddress(List<DataIPAddress> allSystemIpAddress);

}
