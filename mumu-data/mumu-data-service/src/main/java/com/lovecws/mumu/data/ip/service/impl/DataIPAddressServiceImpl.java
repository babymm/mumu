package com.lovecws.mumu.data.ip.service.impl;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.common.core.utils.IPAddressUtil;
import com.lovecws.mumu.data.ip.dao.DataIPAddressDao;
import com.lovecws.mumu.data.ip.entity.DataIPAddress;
import com.lovecws.mumu.data.ip.service.DataIPAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class DataIPAddressServiceImpl implements DataIPAddressService {

    @Autowired
    private DataIPAddressDao dataIPAddressDao;

    @Override
    /*@ModularCache(prefix="system:ipaddress")*/
    public List<DataIPAddress> getAllSystemIpAddress() {
        return dataIPAddressDao.listBy(new HashMap<String, Object>());
    }

    @Override
    public List<DataIPAddress> getSystemIpAddressByIp(String ip) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ip", IPAddressUtil.transformIpAddressToLong(ip));
        //paramMap.put("ip", ip);
        return dataIPAddressDao.listBy(paramMap);
    }

    @Override
    public List<DataIPAddress> getSystemIpAddressByAddress(String address) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("address", address);
        return dataIPAddressDao.listBy(paramMap);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addBatchSystemIPAddress(List<DataIPAddress> allSystemIpAddress) {
        dataIPAddressDao.insert(allSystemIpAddress);
    }

    @Override
    public PageBean<DataIPAddress> listPage(int currentPage, int pageSize) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        PageParam pageParam = new PageParam(currentPage, pageSize);
        return dataIPAddressDao.listPage(pageParam, paramMap);
    }
}
