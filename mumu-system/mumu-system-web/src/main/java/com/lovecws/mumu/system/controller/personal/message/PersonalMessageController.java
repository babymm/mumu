package com.lovecws.mumu.system.controller.personal.message;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.system.entity.SysMessage;
import com.lovecws.mumu.system.entity.SysMessageContainer;
import com.lovecws.mumu.system.entity.SysUser;
import com.lovecws.mumu.system.service.SysMessageContainerService;
import com.lovecws.mumu.system.service.SysMessageService;
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

/**
 * Created by Administrator on 2017/8/10/010.
 */
@Controller
@RequestMapping("/personal/message")
public class PersonalMessageController {

    @Autowired
    private SysMessageService messageService;
    @Autowired
    private SysMessageContainerService messageContainerService;

    /**
     * 进入个人消息页面
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String  message(){
        return "personal/message/index";
    }

    /**
     * 消息列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"/page"})
    public Map<String,Object> messagePage(int beginIndex, int pageSize){
        SysUser sessionUser=(SysUser)SecurityUtils.getSubject().getSession().getAttribute(SysUser.SYS_USER);
        PageBean<SysMessageContainer> pageBean = messageContainerService.listPage(sessionUser.getUserId(),null,beginIndex,pageSize);
        Map<String, Object> page = new HashMap<String, Object>();
        page.put("total", pageBean.getTotalCount());
        page.put("rows", pageBean.getRecordList());
        return page;
    }

    /**
     * 进入个人消息页面
     * @return
     */
    @RequestMapping(value = "/view/{messageId}",method = RequestMethod.GET)
    public String  view(@PathVariable int messageId, HttpServletRequest request){
        SysMessage message = messageService.getSysMessageById(messageId);
        request.setAttribute("message",message);

        //获取该消息容器中是否已读
        SysUser sessionUser=(SysUser)SecurityUtils.getSubject().getSession().getAttribute(SysUser.SYS_USER);
        List<SysMessageContainer> messageContainers = messageContainerService.querySysMessageContainerByCondition(sessionUser.getUserId(), messageId, SysMessageContainer.MESSAGECONTAINERSTATUS_UNREAD);
        if(messageContainers!=null&&messageContainers.size()>0){
            SysMessageContainer messageContainer=new SysMessageContainer();
            messageContainer.setMessageContainerId(messageContainers.get(0).getMessageContainerId());
            messageContainer.setMessageContainerStatus(SysMessageContainer.MESSAGECONTAINERSTATUS_READ);
            messageContainerService.updateSysMessageContainerById(messageContainer);
        }
        return "personal/message/view";
    }
}
