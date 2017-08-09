/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lovecws.mumu.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 角色实体
 * @author ganliang
 * @version 2016年9月10日 下午9:45:58
 */
public class SysRole implements Serializable {

	private static final long serialVersionUID = -1850274607153125161L;

	private Integer roleId;// 主键ID.
	private String roleStatus;// 状态 PublicStatusEnum
	private String creator;// 创建人.
	private Date createTime = new Date();// 创建时间.

	private String editor;// 修改人.
	private Date editTime;// 修改时间.
	private String remark;// 描述

	private String roleCode; // 角色编码：例如：admin
	private String roleName; // 角色名称
	private String roleType; // 角色类型任务分配：assignment、管理角色：security-role、普通角色：user

	
	public SysRole() {
		super();
	}

	public SysRole(Integer roleId, String roleStatus, String remark, String roleCode, String roleName,
			String roleType) {
		this.roleId = roleId;
		this.roleStatus = roleStatus;
		this.remark = remark;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.roleType = roleType;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		return "SysRole [roleId=" + roleId + ", roleStatus=" + roleStatus + ", creator=" + creator + ", createTime="
				+ createTime + ", editor=" + editor + ", editTime=" + editTime + ", remark=" + remark + ", roleCode="
				+ roleCode + ", roleName=" + roleName + ", roleType=" + roleType + "]";
	}

}
