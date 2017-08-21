package com.lovecws.mumu.system.entity;

import com.lovecws.mumu.common.core.entity.PersistentEntity;

import java.util.Date;

/**
 * 站内消息容器 关联用户和消息
 */
public class SysMessageContainer extends PersistentEntity{

    private int messageContainerId;//消息id

    public static final String MESSAGECONTAINERSTATUS_READ="read";
    public static final String MESSAGECONTAINERSTATUS_UNREAD="unread";
    public static final String MESSAGECONTAINERSTATUS_DELETE="delete";
    private String messageContainerStatus;//消息状态 已读（read）、未读（unread）、删除（delete）

    private Date createDate;//创建日期
    private Date editDate;//创建日期

    private Integer userId;//用户id
    private Integer messageId;//消息id

    public int getMessageContainerId() {
        return messageContainerId;
    }

    public void setMessageContainerId(int messageContainerId) {
        this.messageContainerId = messageContainerId;
    }

    public String getMessageContainerStatus() {
        return messageContainerStatus;
    }

    public void setMessageContainerStatus(String messageContainerStatus) {
        this.messageContainerStatus = messageContainerStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    private String userName;
    private String avator;
    private String messageTitle;
    private String messageType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
