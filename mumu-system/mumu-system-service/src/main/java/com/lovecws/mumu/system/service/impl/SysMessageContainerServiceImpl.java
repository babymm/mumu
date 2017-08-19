package com.lovecws.mumu.system.service.impl;


import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.common.core.page.PageParam;
import com.lovecws.mumu.system.dao.SysMessageContainerDao;
import com.lovecws.mumu.system.entity.SysMessageContainer;
import com.lovecws.mumu.system.service.SysMessageContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,readOnly=true)
public class SysMessageContainerServiceImpl implements SysMessageContainerService{

    @Autowired
    private SysMessageContainerDao messageContainerDao;

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public void addBatchSysMessageContainer(List<SysMessageContainer> messageContainerList) {
        if(messageContainerList!=null&&messageContainerList.size()>0){
            messageContainerDao.insert(messageContainerList);
        }
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public void addSysMessageContainer(SysMessageContainer memssageContainer) {
        memssageContainer.setCreateDate(new Date());
        memssageContainer.setMessageContainerStatus(SysMessageContainer.MESSAGECONTAINERSTATUS_UNREAD);
        messageContainerDao.insert(memssageContainer);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public void deleteSysMessageContainerByCondition(Integer userId, Integer messageId) {
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("userId",userId);
        paramMap.put("messageId",messageId);
        messageContainerDao.delete(paramMap);
    }

    @Override
    public PageBean<SysMessageContainer> listPage(String messageId, int beginIndex, int pageSize) {
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("messageId",messageId);
        PageParam pageParam=new PageParam(beginIndex/pageSize+1,pageSize);
        return messageContainerDao.listPage(pageParam,paramMap);
    }

    @Override
    public List<SysMessageContainer> querySysMessageContainerByCondition(Integer userId, Integer messageId, String messageContainerStatus) {
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("userId",userId);
        paramMap.put("messageId",messageId);
        paramMap.put("messageContainerStatus",messageContainerStatus);
        return messageContainerDao.selectList("querySysMessageContainerByCondition",paramMap);
    }

    @Override
    public List<SysMessageContainer> querySysMessageByCondition(Integer userId, Integer messageId, String messageContainerStatus) {
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("userId",userId);
        paramMap.put("messageId",messageId);
        paramMap.put("messageContainerStatus",messageContainerStatus);
        return messageContainerDao.selectList("querySysMessageByCondition",paramMap);
    }
}
