package com.lovecws.mumu.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 用户组-用户中间表
 * @author ganliang
 * @version 2016年9月10日 下午9:47:55
 */
public class SysGroupRole implements Serializable {

	private static final long serialVersionUID = 174356028197753020L;

	private Integer groupRoleId;// 主键ID.
	private String groupRoleStatus;// 状态 PublicStatusEnum
	private String creator;// 创建人.
	private Date createTime;// 创建时间.

	private Integer groupId;// 用户组ID
	private Integer roleId;// 角色ID

	public SysGroupRole() {
		super();
	}

	public SysGroupRole(String groupRoleStatus, String creator, Date createTime, Integer groupId, Integer roleId) {
		super();
		this.groupRoleStatus = groupRoleStatus;
		this.creator = creator;
		this.createTime = createTime;
		this.groupId = groupId;
		this.roleId = roleId;
	}

	public Integer getGroupRoleId() {
		return groupRoleId;
	}

	public void setGroupRoleId(Integer groupRoleId) {
		this.groupRoleId = groupRoleId;
	}

	public String getGroupRoleStatus() {
		return groupRoleStatus;
	}

	public void setGroupRoleStatus(String groupRoleStatus) {
		this.groupRoleStatus = groupRoleStatus;
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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
