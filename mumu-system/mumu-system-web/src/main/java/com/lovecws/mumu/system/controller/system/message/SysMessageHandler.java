package com.lovecws.mumu.system.controller.system.message;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.system.entity.SysMessage;
import com.lovecws.mumu.system.entity.SysMessageContainer;
import com.lovecws.mumu.system.entity.SysUser;
import com.lovecws.mumu.system.service.SysMessageContainerService;
import com.lovecws.mumu.system.service.SysMessageService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 站内信处理器
 */
@Component
public class SysMessageHandler {

    @Autowired
    private SysMessageService messageService;

    @Autowired
    private SysMessageContainerService messageContainerService;

    /**
     * 定义一个线程池 处理用户消息的业务逻辑
     */
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public void handler(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                handlerMessage();
            }
        });
    }

    /**
     * 处理广播消息
     * 首选找到所有的广播消息，然后找到用户已经生成的广播消息，再找到未生成的广播消息，最后将未生成的广播消息添加到消息容器中
     * 该方式处理业务逻辑比较简单 但是当广播消息比较多的时候 程序需要花费很多时间来判断哪些广播消息没有被注册到消息容器中。
     */
    private void handlerMessage() {
        SysUser sessionUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(SysUser.SYS_USER);
        //获取所有的 发送广播消息
        List<SysMessage> messages=messageService.querySysMessageByCondition(SysMessage.MESSAGETYPE_ALL, PublicEnum.NORMAL.value());
        //1、获取用户已经生成的广播消息
        List<SysMessageContainer> messageContainers=messageContainerService.querySysMessageContainerByCondition(sessionUser.getUserId(),null,null);
        //找到用户未生成的广播消息
        List<SysMessageContainer> unCreateMessageContainers=new ArrayList<SysMessageContainer>();
        for (SysMessage message:messages){
            boolean isCreate=false;
            for (SysMessageContainer messageContainer:messageContainers){
                if(messageContainer.getMessageId().intValue()==message.getMessageId()){
                    isCreate=true;
                    break;
                }
            }
            if(!isCreate){
                SysMessageContainer messageContainer=new SysMessageContainer();
                messageContainer.setUserId(sessionUser.getUserId());
                messageContainer.setMessageId(message.getMessageId());
                messageContainer.setCreateDate(new Date());
                messageContainer.setMessageContainerStatus(SysMessageContainer.MESSAGECONTAINERSTATUS_UNREAD);
                unCreateMessageContainers.add(messageContainer);
            }
        }
        //将消息添加到消息容器中
        messageContainerService.addBatchSysMessageContainer(unCreateMessageContainers);
    }
}
