package com.lovecws.mumu.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 用户日志实体
 * @author ganliang
 * @version 2016年9月10日 下午10:09:39
 */
public class SysUserLog implements Serializable {

	private static final long serialVersionUID = 742891253537618199L;

	private Integer userLogId;// 主键ID.
	private String userLogStatus;// 状态 PublicStatusEnum
	private String creator;// 创建人.
	private Date createTime;// 创建时间.

	private String editor;// 修改人.
	private Date editTime;// 修改时间.
	private String remark;// 描述

	private Integer userId; // 用户ID
	private String userName; // 用户名
	private String operateType; // 操作类型（参与枚举:OperatorLogTypeEnum,1:增加,2:修改,3:删除,4:查询,5:登录）
	private String ip; // IP地址
	private String content; // 操作内容
	private String method;
	private String parameter;
	private String result;
	private String usetime;

	public SysUserLog() {
		super();
	}

	public SysUserLog(String userLogStatus, String creator, Date createTime, Integer userId, String userName, String ip,
			String method, String parameter, String result, String usetime) {
		super();
		this.userLogStatus = userLogStatus;
		this.creator = creator;
		this.createTime = createTime;
		this.userId = userId;
		this.userName = userName;
		this.ip = ip;
		this.method = method;
		this.parameter = parameter;
		this.result = result;
		this.usetime = usetime;
	}

	public Integer getUserLogId() {
		return userLogId;
	}

	public void setUserLogId(Integer userLogId) {
		this.userLogId = userLogId;
	}

	public String getUserLogStatus() {
		return userLogStatus;
	}

	public void setUserLogStatus(String userLogStatus) {
		this.userLogStatus = userLogStatus;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUsetime() {
		return usetime;
	}

	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}

	//日志统计
	private int logCount;//日志数量
	private Date logTime;//日志时间（毫秒计算）
	public int getLogCount() {
		return logCount;
	}
	public void setLogCount(int logCount) {
		this.logCount = logCount;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	private Object proceed;

	public Object getProceed() {
		return proceed;
	}

	public void setProceed(Object proceed) {
		this.proceed = proceed;
	}
}
