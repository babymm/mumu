package com.lovecws.mumu.data.controller.ip;

import com.lovecws.mumu.common.core.response.HttpCode;
import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.common.core.utils.IPAddressUtil;
import com.lovecws.mumu.common.core.utils.ValidateUtils;
import com.lovecws.mumu.data.ip.entity.DataIPAddress;
import com.lovecws.mumu.data.ip.service.DataIPAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ip地址查找控制器
 */
@Controller
@RequestMapping("/data/ipaddress")
public class DataIPAddressController {

    @Autowired
    private DataIPAddressService ipAddressService;

    /**
     * 获取所有的ipaddress
     *
     * @return
     */
    @ResponseBody
    //@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllSystemIpAddress() {
        List<DataIPAddress> allSystemIpAddress = ipAddressService.getAllSystemIpAddress();
        return new ResponseEntity(HttpCode.OK, allSystemIpAddress);
    }

    /**
     * 根据ip获取到该ip下的所有地址
     *
     * @param ip
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public ResponseEntity getSystemIpAddressByIp(String ip) {
        if(ip==null||"".equalsIgnoreCase(ip)){
            return new ResponseEntity(400,"ip地址不能为空","");
        }
        else if(!ValidateUtils.isIP(ip)){
            return new ResponseEntity(400,"ip地址无效","");
        }
        List<DataIPAddress> allSystemIpAddress = ipAddressService.getSystemIpAddressByIp(ip);
        return new ResponseEntity(HttpCode.OK, allSystemIpAddress);
    }

    /**
     * 根据地址查询改地址下的所有ip
     *
     * @param address
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public ResponseEntity getSystemIpAddressByAddress(String address) {
        if(address==null||"".equalsIgnoreCase(address)){
            return new ResponseEntity(400,"地址不能为空","");
        }
        List<DataIPAddress> allSystemIpAddress = ipAddressService.getSystemIpAddressByAddress(address);
        return new ResponseEntity(HttpCode.OK, allSystemIpAddress);
    }

    private static boolean has_init = false;

    /**
     * 获取所有的ipaddress
     *
     * @return
     */
    @ResponseBody
    //@RequestMapping(value = "/init", method = RequestMethod.POST)
    public ResponseEntity initIPAddress() {
        if (has_init) {
            return new ResponseEntity(HttpCode.PARAMETER_ERROR, null);
        }
        has_init = true;
        List<DataIPAddress> allSystemIpAddress = ipAddressService.getAllSystemIpAddress();
        for (DataIPAddress systemIPAddressEntity : allSystemIpAddress) {
            systemIPAddressEntity.setSip(IPAddressUtil.transformIpAddressToLong(systemIPAddressEntity.getStartIp()));
            systemIPAddressEntity.setEip(IPAddressUtil.transformIpAddressToLong(systemIPAddressEntity.getEndIp()));
            System.out.println(systemIPAddressEntity);
        }
        ipAddressService.addBatchSystemIPAddress(allSystemIpAddress);
        return new ResponseEntity(HttpCode.OK, null);
    }

}
