package com.lovecws.mumu.system.entity;

import com.lovecws.mumu.common.core.entity.PersistentEntity;

import java.util.Date;

/**
 * 站内消息
 */
public class SysMessage extends PersistentEntity{

    private  int messageId;//消息id
    private  int messageStatus;//消息状态 PublicEnum
    private Date createDate;//创建日期

    private Integer createUserId;//创建消息的用户id
    private String messageTitle;//标题
    private String messageContent;//内容

    private String messageImage;//消息图片
    private String messageLink;//连接

    public static final String MESSAGETYPE_USER="user";
    public static final String MESSAGETYPE_GROUP="group";
    public static final String MESSAGETYPE_ALL="all";
    private String messageType;// 个人（user）、群组（group）、全体（all）

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageImage() {
        return messageImage;
    }

    public void setMessageImage(String messageImage) {
        this.messageImage = messageImage;
    }

    public String getMessageLink() {
        return messageLink;
    }

    public void setMessageLink(String messageLink) {
        this.messageLink = messageLink;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
