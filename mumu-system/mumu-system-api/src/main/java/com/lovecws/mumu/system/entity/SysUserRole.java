package com.lovecws.mumu.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 用户角色中间表
 * @author ganliang
 * @version 2016年9月10日 下午9:47:55
 */
public class SysUserRole implements Serializable {

	private static final long serialVersionUID = 174356028197753020L;

	private Integer userRoleId;// 主键ID.
	private String userRoleStatus;// 状态 PublicStatusEnum
	private String creator;// 创建人.
	private Date createTime = new Date();// 创建时间.

	private Integer roleId;// 角色ID
	private Integer userId;// 用户ID

	public SysUserRole() {
		super();
	}

	public SysUserRole(String userRoleStatus, String creator, Date createTime, Integer roleId,Integer userId) {
		super();
		this.userRoleStatus = userRoleStatus;
		this.creator = creator;
		this.createTime = createTime;
		this.roleId = roleId;
		this.userId = userId;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserRoleStatus() {
		return userRoleStatus;
	}

	public void setUserRoleStatus(String userRoleStatus) {
		this.userRoleStatus = userRoleStatus;
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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
