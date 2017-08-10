package com.lovecws.mumu.system.controller.system.organize;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.common.core.tree.ZTreeBean;
import com.lovecws.mumu.system.entity.*;
import com.lovecws.mumu.system.service.SysGroupService;
import com.lovecws.mumu.system.service.SysOrganizeService;
import com.lovecws.mumu.system.service.SysUserService;
import com.lovecws.mumu.system.util.NodeUtil;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/8/008.
 */
@Controller
@RequestMapping("/system/organize")
public class SysOrganizeController {

    private static final Logger log= Logger.getLogger(SysOrganizeController.class);
    @Autowired
    private SysOrganizeService organizeService;
    @Autowired
    private SysGroupService groupService;
    @Autowired
    private SysUserService userService;

    /**
     * 跳转到组织结构页面
     * @return
     */
    @RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
    public String index(){
        return "system/organize/index";
    }

    /**
     * 分页获取组织机构列表
     * @param orgName 组织名称
     * @param beginIndex 开始索引
     * @param pageSize 一页数量
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"/page"})
    public Map<String,Object> page(String orgName,int beginIndex,int pageSize){
        // 分页查询
        PageBean<SysOrganize> pageBean = organizeService.listPage(orgName, beginIndex / pageSize + 1, pageSize);
        Map<String, Object> page = new HashMap<String, Object>();
        page.put("total", pageBean.getTotalCount());
        page.put("rows", pageBean.getRecordList());
        return page;
    }

    /**
     * 组织机构列表
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public String add(HttpServletRequest request){
        List<SysOrganize> organizes=organizeService.querySysOrganizeByCondition(null);
        List<NodeUtil.NodeBean> nodeBeans = new ArrayList<NodeUtil.NodeBean>();
        for (SysOrganize organize : organizes) {
            nodeBeans.add(new NodeUtil.NodeBean(String.valueOf(organize.getOrgId()), String.valueOf(organize.getParentOrgId()), organize));
        }
        List<T> list = NodeUtil.iterator(nodeBeans, "﹎﹎", "orgName");
        request.setAttribute("organizes", list);
        return "system/organize/add";
    }

    /**
     * 保存组织机构
     * @param orgName 组织名称
     * @param parentOrgId 父组织id
     * @param remark 组织描述
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public ResponseEntity saveOrganize(String orgName, String parentOrgId, String remark){
        List<SysOrganize> organizes=organizeService.querySysOrganizeByCondition(orgName);
        if(organizes!=null&&organizes.size()>0){
            return new ResponseEntity(500, "组织机构名称不可重复", null);
        }
        try {
            SysOrganize organize=new SysOrganize();
            organize.setOrgName(orgName);
            organize.setParentOrgId(Integer.parseInt(parentOrgId));
            organize.setRemark(remark);
            organize.setCreator(SecurityUtils.getSubject().getPrincipal().toString());
            organizeService.addSysOrganize(organize);
            return new ResponseEntity(200, "组织机构添加成功", null);
        } catch (NumberFormatException e) {
            log.error(e);
            return new ResponseEntity(500, "组织机构添加失败", null);
        }
    }

    /**
     * 组织机构详情
     * @param orgId 组织机构id
     * @param request
     * @return
     */
    @RequestMapping(value="/view/{orgId}",method=RequestMethod.GET)
    public String view(@PathVariable String orgId,HttpServletRequest request){
        SysOrganize organize=organizeService.getSysOrganizeById(orgId);
        request.setAttribute("organize", organize);
        return "system/organize/view";
    }


    /**
     * 编辑组织机构
     * @param orgId 组织机构id
     * @param request
     * @return
     */
    @RequestMapping(value="/edit/{orgId}",method=RequestMethod.GET)
    public String edit(@PathVariable String orgId,HttpServletRequest request){
        SysOrganize organize=organizeService.getSysOrganizeById(orgId);
        request.setAttribute("organize", organize);

        List<SysOrganize> organizes=organizeService.querySysOrganizeByCondition(null);
        List<NodeUtil.NodeBean> nodeBeans = new ArrayList<NodeUtil.NodeBean>();
        for (SysOrganize org : organizes) {
            nodeBeans.add(new NodeUtil.NodeBean(String.valueOf(org.getOrgId()), String.valueOf(org.getParentOrgId()), org));
        }
        List<T> list = NodeUtil.iterator(nodeBeans, "﹎﹎", "orgName");
        request.setAttribute("organizes", list);
        return "system/organize/edit";
    }

    /**
     * 编辑组织机构
     * @param orgName 组织名称
     * @param parentOrgId 父组织id
     * @param remark 组织描述
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/edit",method=RequestMethod.PUT)
    public ResponseEntity updateOrganize(int orgId,String orgName,String parentOrgId,String remark){
        List<SysOrganize> organizes=organizeService.querySysOrganizeByCondition(orgName);
        if(organizes!=null&&organizes.size()>0&&organizes.get(0).getOrgId()!=orgId){
            return new ResponseEntity(500, "组织机构名称不可重复", null);
        }
        try {
            SysOrganize organize=new SysOrganize();
            organize.setOrgId(orgId);
            organize.setOrgName(orgName);
            organize.setParentOrgId(Integer.parseInt(parentOrgId));
            organize.setRemark(remark);
            organize.setEditor(SecurityUtils.getSubject().getPrincipal().toString());
            organizeService.updateSysOrganize(organize);
            return new ResponseEntity(200, "更新组织机构成功", null);
        } catch (NumberFormatException e) {
            log.error(e);
            return new ResponseEntity(500, "更新组织机构出现异常", null);
        }
    }

    /**
     * 保存组织机构
     * @param orgId 组织机构id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/delete/{orgId}",method=RequestMethod.DELETE)
    public ResponseEntity deleteOrganize(@PathVariable String orgId){
        try {
            organizeService.deleteSysOrganizeById(orgId);
            return new ResponseEntity(200, "删除组织机构成功", null);
        } catch (Exception e) {
            log.error(e);
            return new ResponseEntity(500, "删除组织机构出现异常", null);
        }
    }

    /**
     * ECOTree组织机构图
     * @return
     */
    @RequestMapping(value="/ECOTree",method=RequestMethod.GET)
    public String ECOTree(){
        return "system/organize/ECOTree";
    }

    @ResponseBody
    @RequestMapping(value="/graph",method=RequestMethod.GET)
    public List<SysOrganize> organizeGraph(){
        return organizeService.querySysOrganizeByCondition(null);
    }

    /**
     * 组织机构下的成员列表
     * @param orgId 组织机构id
     * @return
     */
    @RequestMapping(value = {"/members"}, method = RequestMethod.GET)
    public String allowRole(String orgId, HttpServletRequest request) {
        request.setAttribute("orgId", orgId);
        return "system/organize/members";
    }

    /**
     * 组织机构下的成员列表
     * @param orgId 组织机构id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/members/{orgId}"}, method = RequestMethod.GET)
    public List<ZTreeBean> members(@PathVariable String orgId) {
        SysOrganize organize = organizeService.getSysOrganizeById(orgId);
        if(organize==null){
            throw new IllegalArgumentException("组织机构不存在!");
        }
        //获取该组织机构下的群组
        List<SysGroup> sysGroups = groupService.querySysGroupByCondition(orgId, null);
        List<ZTreeBean> ztree = new ArrayList<ZTreeBean>();
        for (SysGroup sysGroup : sysGroups) {
            //获取群组下的用户成员
            List<SysUser> sysUsers = userService.querySysUserByGroupId(String.valueOf(sysGroup.getGroupId()), PublicEnum.NORMAL.value());
            ztree.add(new ZTreeBean("g"+sysGroup.getGroupId(), "o"+organize.getOrgId(), sysGroup.getGroupName(), true, "", true));
            for (SysUser sysUser:sysUsers){
                ztree.add(new ZTreeBean("u"+String.valueOf(sysUser.getUserId()), "g"+sysGroup.getGroupId(), sysUser.getUserName(), true, "", true));
            }
        }
        ztree.add(new ZTreeBean("o"+organize.getOrgId(), "0", organize.getOrgName(),true, "", "", true));
        return ztree;
    }

}
