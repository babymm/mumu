package com.lovecws.mumu.system.controller.dataset.ip;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.data.ip.entity.DataIPAddress;
import com.lovecws.mumu.data.ip.service.DataIPAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author babymm
 * @version 1.0-SNAPSHOT
 * @Description: ip地址管理
 * @date 2017-09-23 7:42
 */
@Controller
@RequestMapping("/dataset/ip")
public class DataSetIPAddressController {

    @Autowired
    private DataIPAddressService ipAddressService;

    /**
     * 进入IP地址管理主页面
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/index")
    public String index() {
        return "dataset/ip/index";
    }

    /**
     * 分页获取ip地址信息
     *
     * @param beginIndex 开始索引
     * @param pageSize   一页数量
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/page"})
    public Map<String, Object> ipPage(@RequestParam(defaultValue = "0", required = false) int beginIndex, @RequestParam(defaultValue = "10", required = false) int pageSize) {
        // 分页查询
        PageBean<DataIPAddress> pageBean = ipAddressService.listPage(beginIndex / pageSize + 1, pageSize);
        Map<String, Object> page = new HashMap<String, Object>();
        page.put("total", pageBean.getTotalCount());
        page.put("rows", pageBean.getRecordList());
        return page;
    }
}
