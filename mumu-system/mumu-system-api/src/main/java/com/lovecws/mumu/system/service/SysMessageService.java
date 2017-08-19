package com.lovecws.mumu.system.service;

import com.lovecws.mumu.common.core.page.PageBean;
import com.lovecws.mumu.system.entity.SysMessage;

import java.util.List;

public interface SysMessageService {

    /**
     * 消息列表
     * @param messageType 消息类型
     * @param messageStatus 消息状态
     * @param beginIndex 分页开始索引
     * @param pageSize 分页一页大小
     * @return
     */
    public PageBean<SysMessage> listPage(String messageType, String messageStatus, int beginIndex, int pageSize);

    /**
     * 查看消息详情
     * @param messageId 消息id
     * @return
     */
    public SysMessage getSysMessageById(int messageId);

    /**
     * 更新消息
     * @param message 消息实体
     */
    public void updateSysMessage(SysMessage message);

    /**
     * 删除消息
     * @param messageId 消息id
     */
    public void deleteSysMessageById(int messageId);

    /**
     * 添加消息
     * @param message
     */
    public void addSysMessage(SysMessage message);

    /**
     * 找到所有的广播消息
     * @param messageType 消息类型
     * @param messageStatus 消息状态
     * @return
     */
    public List<SysMessage> querySysMessageByCondition(String messageType, String messageStatus);
}
