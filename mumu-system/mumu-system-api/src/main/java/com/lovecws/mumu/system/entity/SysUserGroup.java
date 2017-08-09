package com.lovecws.mumu.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 用户组织中间表
 * @author ganliang
 * @version 2016年9月10日 下午9:47:55
 */
public class SysUserGroup implements Serializable {

	private static final long serialVersionUID = 174356028197753020L;

	private Integer userGroupId;// 主键ID.
	private String userGroupStatus;// 状态 PublicStatusEnum
	private String creator;// 创建人.
	private Date createTime;// 创建时间.
	private String editor;// 修改人.
	private Date editTime;// 修改时间.
	private String remark;// 修改人.
	private Integer groupId;// 角色ID
	private Integer userId;// 用户ID

	private String privilage;// 权限(基本用户1，副组长2；组长3）

	public Integer getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getUserGroupStatus() {
		return userGroupStatus;
	}

	public void setUserGroupStatus(String userGroupStatus) {
		this.userGroupStatus = userGroupStatus;
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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPrivilage() {
		return privilage;
	}

	public void setPrivilage(String privilage) {
		this.privilage = privilage;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	private String userName;//用户名称
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
