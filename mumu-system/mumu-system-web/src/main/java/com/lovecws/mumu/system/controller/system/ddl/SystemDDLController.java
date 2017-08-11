package com.lovecws.mumu.system.controller.system.ddl;

import com.lovecws.mumu.common.core.log.MumuLog;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.system.entity.SysDDL;
import com.lovecws.mumu.system.service.SysDDLService;
import com.lovecws.mumu.system.util.NodeUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/7.
 * 系统数据字典管理
 */
@Controller("SystemDDLController")
@RequestMapping("/system/ddl")
public class SystemDDLController {
    @Autowired
    private SysDDLService systemDDLService;

    /**
     * 进入数据字典列表页面
     * @return
     */
    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String ddls(){
        return "system/ddl/index";
    }

    /**
     * 分页获取数据字典
     * @param ddlCode 字典内码
     * @param parentCode 字典内码
     * @param withDDLTables 包含
     * @param beginIndex 分页开始索引
     * @param pageSize 分页一页数量
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/page",method = RequestMethod.GET)
    public Map<String, Object> ddlPage(String ddlCode,String parentCode,String withDDLTables,@RequestParam(defaultValue = "0",required = false) int beginIndex, @RequestParam(defaultValue = "10",required = false) int pageSize){
        //分页获取数据
        PageBean<SysDDL> pageBean=systemDDLService.listPage(ddlCode,parentCode,withDDLTables,new PageParam(beginIndex / pageSize + 1, pageSize));
        Map<String, Object> page = new HashMap<String, Object>();
        page.put("total", pageBean.getTotalCount());
        page.put("rows", pageBean.getRecordList());
        return page;
    }

    /**
     * 进入到添加数据字典页面
     * @return
     */
    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String ddlAdd(HttpServletRequest request){
        //获取所有的数据字典
        List<SysDDL> systemDDLs = systemDDLService.getSystemDDLs();

        List<NodeUtil.NodeBean> nodeBeans = new ArrayList<NodeUtil.NodeBean>();
        for (SysDDL ddl : systemDDLs) {
            nodeBeans.add(new NodeUtil.NodeBean(String.valueOf(ddl.getDdlCode()), ddl.getParentCode(), ddl));
        }
        List<T> list = NodeUtil.iterator(nodeBeans, "﹎﹎", "ddlValue");
        request.setAttribute("systemDDLs", list);
        return "system/ddl/add";
    }

    /**
     * 添加数据字典
     * @param systemDDLEntity 数据字典实体
     * @return
     */
    @ResponseBody
    @MumuLog(name = "添加数据字典",operater = "PUT")
    @RequestMapping(value="/add",method = RequestMethod.PUT)
    public ResponseEntity saveSystemDDL(SysDDL systemDDLEntity){
        List<SysDDL> systemDDLs = systemDDLService.getSystemDDLByCondition(systemDDLEntity.getDdlCode());
        if(systemDDLs!=null&&systemDDLs.size()>0){
            return new ResponseEntity(200,"数据字典内码不可重复",null);
        }
        //保存数据字典
        systemDDLService.addSystemDDL(systemDDLEntity);
        return new ResponseEntity(200,"数据字典保存成功",null);
    }

    /**
     * 进入到数据字典详情页面
     * @param ddlId 数据字典id
     * @return
     */
    @RequestMapping(value="/view/{ddlId}",method = RequestMethod.GET)
    public String ddlView(@PathVariable String ddlId,HttpServletRequest request){
        //获取所有的数据字典
        List<SysDDL> systemDDLs = systemDDLService.getSystemDDLs();
        List<NodeUtil.NodeBean> nodeBeans = new ArrayList<NodeUtil.NodeBean>();
        for (SysDDL ddl : systemDDLs) {
            nodeBeans.add(new NodeUtil.NodeBean(String.valueOf(ddl.getDdlCode()), ddl.getParentCode(), ddl));
        }
        List<T> list = NodeUtil.iterator(nodeBeans, "﹎﹎", "ddlValue");
        request.setAttribute("systemDDLs", list);

        //获取数据字典详情
        SysDDL systemDDL = systemDDLService.getSystemDDLById(ddlId);
        request.setAttribute("systemDDL",systemDDL);
        return "system/ddl/view";
    }

    /**
     * 进入到编辑数据字典页面
     * @param ddlId 数据字典id
     * @return
     */
    @RequestMapping(value="/edit/{ddlId}",method = RequestMethod.GET)
    public String ddlEdit(@PathVariable String ddlId,HttpServletRequest request){
        //获取所有的数据字典
        List<SysDDL> systemDDLs = systemDDLService.getSystemDDLs();
        List<NodeUtil.NodeBean> nodeBeans = new ArrayList<NodeUtil.NodeBean>();
        for (SysDDL ddl : systemDDLs) {
            nodeBeans.add(new NodeUtil.NodeBean(String.valueOf(ddl.getDdlCode()), ddl.getParentCode(), ddl));
        }
        List<T> list = NodeUtil.iterator(nodeBeans, "﹎﹎", "ddlValue");
        request.setAttribute("systemDDLs", list);

        SysDDL systemDDL = systemDDLService.getSystemDDLById(ddlId);
        request.setAttribute("systemDDL",systemDDL);
        return "system/ddl/edit";
    }

    /**
     * 更新数据字典
     * @param systemDDLEntity 数据字典实体
     * @return
     */
    @ResponseBody
    @MumuLog(name = "更新数据字典",operater = "POST")
    @RequestMapping(value="/edit",method = RequestMethod.POST)
    public ResponseEntity updateSystemDDL(SysDDL systemDDLEntity){
        List<SysDDL> systemDDLs = systemDDLService.getSystemDDLByCondition(systemDDLEntity.getDdlCode());
        if(systemDDLs!=null&&systemDDLs.size()>1){
            return new ResponseEntity(200,"数据字典内码不可重复",null);
        }
        systemDDLService.updateSystemDDL(systemDDLEntity);
        return new ResponseEntity(200,"数据字典更新成功",null);
    }

    /**
     * 删除数据字典
     * @param ddlId 数据字典id
     * @param ddlCode 数据字典内码
     * @return
     */
    @ResponseBody
    @MumuLog(name = "删除数据字典",operater = "DELETE")
    @RequestMapping(value="/delete/{ddlId}/{ddlCode}",method = RequestMethod.DELETE)
    public ResponseEntity ddlDelete(@PathVariable int ddlId,@PathVariable String ddlCode){
        List<SysDDL> systemDDLs=systemDDLService.getSystemDDLByCondition(ddlCode);
        if(systemDDLs!=null&&systemDDLs.size()>0){
            return new ResponseEntity(400,"数据字典删除失败,该数据字典存在子数据字典，请先删除子数据字典!",null);
        }
        systemDDLService.deleteSystemDDL(String.valueOf(ddlId));
        return new ResponseEntity(200,"数据字典删除成功",null);
    }

    /**
     * 数据字典列表
     * @param ddlCode 数据字典内码
     * @return
     */
    @RequestMapping(value = "/tables/{ddlCode}",method = RequestMethod.GET)
    public String ddlTables(@PathVariable String ddlCode){
        return "system/ddl/tables";
    }
}
