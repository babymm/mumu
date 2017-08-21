package com.lovecws.mumu.system.controller.system.message;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.system.entity.SysGroup;
import com.lovecws.mumu.system.entity.SysMessage;
import com.lovecws.mumu.system.entity.SysMessageContainer;
import com.lovecws.mumu.system.entity.SysUser;
import com.lovecws.mumu.system.service.SysGroupService;
import com.lovecws.mumu.system.service.SysMessageContainerService;
import com.lovecws.mumu.system.service.SysMessageService;
import com.lovecws.mumu.system.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/message")
public class SysMessageController {

    @Autowired
    private SysMessageService messageService;
    @Autowired
    private SysMessageContainerService messageContainerService;
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysGroupService groupService;

    /**
     * 消息列表
     * @return
     */
    @RequestMapping(value={"/index"},method= RequestMethod.GET)
    public String messages(){
        return "system/message/index";
    }

    /**
     * 消息列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"/page"})
    public Map<String,Object> messagePage(String messageType, int beginIndex, int pageSize){
        PageBean<SysMessage> pageBean = messageService.listPage(messageType,PublicEnum.NORMAL.value(),beginIndex,pageSize);
        Map<String, Object> page = new HashMap<String, Object>();
        page.put("total", pageBean.getTotalCount());
        page.put("rows", pageBean.getRecordList());
        return page;
    }

    /**
     * 进入到消息添加页面
     * @return
     */
    @RequestMapping(value={"/add"},method= RequestMethod.GET)
    public String add(HttpServletRequest request){
        //获取所有的群组
        List<SysGroup> sysGroups = groupService.querySysGroupByCondition(null, null);
        request.setAttribute("groups",sysGroups);
        //获取所有的用户
        List<SysUser> sysUsers = userService.querySysUserByCondition(null, null, null, null, SysUser.USER_STATUS_ACTIVE);
        request.setAttribute("users",sysUsers);
        return "system/message/add";
    }

    /**
     * 进入到消息添加页面
     * @param message 消息实体
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"/add"},method= RequestMethod.POST)
    public ResponseEntity save(SysMessage message){
        SysUser sessionUser=(SysUser)SecurityUtils.getSubject().getSession().getAttribute(SysUser.SYS_USER);
        message.setCreateUserId(sessionUser.getUserId());
        messageService.addSysMessage(message);
        return new ResponseEntity(200,"消息添加成功","");
    }

    /**
     * 进入到消息详情页面
     * @param messageId 消息id
     * @param request
     * @return
     */
    @RequestMapping(value={"/view/{messageId}"},method= RequestMethod.GET)
    public String view(@PathVariable int messageId, HttpServletRequest request){
        SysMessage message=messageService.getSysMessageById(messageId);
        request.setAttribute("message",message);

        //获取所有的群组
        List<SysGroup> sysGroups = groupService.querySysGroupByCondition(null, null);
        request.setAttribute("groups",sysGroups);
        //获取所有的用户
        List<SysUser> sysUsers = userService.querySysUserByCondition(null, null, null, null, SysUser.USER_STATUS_ACTIVE);
        request.setAttribute("users",sysUsers);
        return "system/message/view";
    }

    /**
     * 进入到消息编辑页面
     * @param messageId 消息id
     * @param request
     * @return
     */
    @RequestMapping(value={"/edit/{messageId}"},method= RequestMethod.GET)
    public String edit(@PathVariable int messageId, HttpServletRequest request){
        SysMessage message=messageService.getSysMessageById(messageId);
        request.setAttribute("message",message);

        //获取所有的群组
        List<SysGroup> sysGroups = groupService.querySysGroupByCondition(null, null);
        request.setAttribute("groups",sysGroups);
        //获取所有的用户
        List<SysUser> sysUsers = userService.querySysUserByCondition(null, null, null, null, SysUser.USER_STATUS_ACTIVE);
        request.setAttribute("users",sysUsers);
        return "system/message/edit";
    }

    /**
     * 进入到消息添加页面
     * @param message 消息实体
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"/edit"},method= RequestMethod.PUT)
    public ResponseEntity update(SysMessage message){
        messageService.updateSysMessage(message);
        return new ResponseEntity(200,"消息更新成功","");
    }

    /**
     * 进入到消息添加页面
     * @param messageId 消息id
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"/delete/{messageId}"},method= RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable int messageId){
        messageService.deleteSysMessageById(messageId);
        return new ResponseEntity(200,"消息删除成功","");
    }

    /**
     * 进入到消息容器页面
     * @param messageId
     * @return
     */
    @RequestMapping(value={"/receiver/{messageId}"},method= RequestMethod.GET)
    public String receiver(@PathVariable int messageId){
        return "system/message/receiver";
    }

    /**
     * 消息列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"/receiverPage"})
    public Map<String,Object> receiverPage(int messageId,int beginIndex, int pageSize){
        PageBean<SysMessageContainer> pageBean = messageContainerService.listPage(null,messageId,beginIndex,pageSize);
        Map<String, Object> page = new HashMap<String, Object>();
        page.put("total", pageBean.getTotalCount());
        page.put("rows", pageBean.getRecordList());
        return page;
    }
}
