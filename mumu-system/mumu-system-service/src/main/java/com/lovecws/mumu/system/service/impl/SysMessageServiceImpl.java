package com.lovecws.mumu.system.service.impl;

import com.lovecws.mumu.common.core.enums.PublicEnum;
import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.system.dao.SysMessageDao;
import com.lovecws.mumu.system.entity.SysMessage;
import com.lovecws.mumu.system.entity.SysMessageContainer;
import com.lovecws.mumu.system.entity.SysUser;
import com.lovecws.mumu.system.service.SysMessageContainerService;
import com.lovecws.mumu.system.service.SysMessageService;
import com.lovecws.mumu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,readOnly=true)
public class SysMessageServiceImpl implements SysMessageService {

    @Autowired
    private SysMessageDao messageDao;
    @Autowired
    private SysMessageContainerService messageContainerService;
    @Autowired
    private SysUserService userService;

    @Override
    public PageBean<SysMessage> listPage(String messageType, String messageStatus, int beginIndex, int pageSize) {
        PageParam pageParam=new PageParam(beginIndex/pageSize+1,pageSize);
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("messageType",messageType);
        paramMap.put("messageStatus",messageStatus);
        return messageDao.listPage(pageParam,paramMap);
    }

    @Override
    public SysMessage getSysMessageById(int messageId) {
        return messageDao.getById(String.valueOf(messageId));
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public void updateSysMessage(SysMessage message) {
        messageDao.update(message);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public void deleteSysMessageById(int messageId) {
        //删除消息容器的关联
        messageContainerService.deleteSysMessageContainerByCondition(null,messageId);
        //删除消息
        messageDao.delete(String.valueOf(messageId));
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public void addSysMessage(SysMessage message) {
        if(message==null||message.getMessageType()==null){
            throw new IllegalArgumentException();
        }
        //保存消息信息
        message.setCreateDate(new Date());
        message.setMessageStatus(PublicEnum.NORMAL.value());
        message = messageDao.insert(message);

        //保存消息容器信息
        switch (message.getMessageType()) {
            //单点发送消息
            case SysMessage.MESSAGETYPE_USER:
                SysMessageContainer userMessageContainer=new SysMessageContainer();
                userMessageContainer.setCreateDate(new Date());
                userMessageContainer.setMessageContainerStatus(SysMessageContainer.MESSAGECONTAINERSTATUS_UNREAD);
                userMessageContainer.setMessageId(message.getMessageId());
                userMessageContainer.setUserId(Integer.parseInt(message.getUserId()));
                messageContainerService.addSysMessageContainer(userMessageContainer);
                break;
            //群组发送消息
            //TODO 当群组用户数量比较少的时候 使用该方法；但是当群组人数比较多的时候 不建议使用这种方法，而是和发送全体消息一样的方式。
            case SysMessage.MESSAGETYPE_GROUP:
                //获取该组的所有用户
                List<SysUser> sysUsers = userService.querySysUserByGroupId(message.getGroupId(), SysUser.USER_STATUS_ACTIVE);
                //对改组所有的用户发送一份消息到消息容器
                List<SysMessageContainer> messageContainerList=new ArrayList<SysMessageContainer>();
                for (SysUser user:sysUsers){
                    SysMessageContainer groupMessageContainer=new SysMessageContainer();
                    groupMessageContainer.setCreateDate(new Date());
                    groupMessageContainer.setMessageContainerStatus(SysMessageContainer.MESSAGECONTAINERSTATUS_UNREAD);
                    groupMessageContainer.setMessageId(message.getMessageId());
                    groupMessageContainer.setUserId(user.getUserId());
                    messageContainerList.add(groupMessageContainer);
                }
                messageContainerService.addBatchSysMessageContainer(messageContainerList);
                break;
            //所有的员工发送消息
            case SysMessage.MESSAGETYPE_ALL:
                break;
        }
    }

    @Override
    public List<SysMessage> querySysMessageByCondition(String messageType, String messageStatus) {
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("messageType",messageType);
        paramMap.put("messageStatus",messageStatus);
        return messageDao.selectList("listPage",paramMap);
    }
}
