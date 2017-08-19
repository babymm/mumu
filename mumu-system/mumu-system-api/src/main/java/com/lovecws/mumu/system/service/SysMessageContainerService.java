package com.lovecws.mumu.system.service;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.system.entity.SysMessageContainer;

import java.util.List;

public interface SysMessageContainerService {

    /**
     * 批量添加消息容器
     * @param messageContainerList 消息容器集合
     */
    public void addBatchSysMessageContainer(List<SysMessageContainer> messageContainerList);

    /**
     * 添加消息容器
     * @param memssageContainer 消息容器
     */
    public void addSysMessageContainer(SysMessageContainer memssageContainer);

    /**
     * 删除消息容器里面的消息
     * @param userId 用户id
     * @param messageId 消息id
     */
    public void deleteSysMessageContainerByCondition(Integer userId, Integer messageId);

    /**
     * 分页获取消息容器
     * @param messageId 消息id
     * @param beginIndex 开始索引
     * @param pageSize 一页大小
     * @return
     */
    public PageBean<SysMessageContainer> listPage(String messageId, int beginIndex, int pageSize);

    /**
     * 获取用户所有的广播消息
     * @param userId 用户id
     * @param messageId 消息id
     * @param messageContainerStatus 消息状态
     * @return
     */
    public List<SysMessageContainer> querySysMessageContainerByCondition(Integer userId, Integer messageId, String messageContainerStatus);

    /**
     * 获取广播消息（附带用户消息）
     * @param userId 用户id
     * @param messageId 消息id
     * @param messageContainerStatus 消息状态
     * @return
     */
    public List<SysMessageContainer> querySysMessageByCondition(Integer userId, Integer messageId, String messageContainerStatus);
}
