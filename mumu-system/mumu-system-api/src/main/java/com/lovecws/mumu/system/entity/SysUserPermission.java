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
 * @desc 用户权限对象
 * @author ganliang
 * @version 2016年9月10日 下午9:47:23
 */
public class SysUserPermission implements Serializable {

	private static final long serialVersionUID = -9012707031072904356L;

	private Integer rolePermissionId;// 主键ID.
	private String rolePermissionStatus;// 状态 PublicStatusEnum
	private String creator;// 创建人.
	private Date createTime;// 创建时间.

	private Integer userId; // 用户ID
	private Integer permissionId;// 权限ID

	public SysUserPermission() {
		super();
	}

	public SysUserPermission(String rolePermissionStatus, String creator, Date createTime, Integer userId,
                             Integer permissionId) {
		super();
		this.rolePermissionStatus = rolePermissionStatus;
		this.creator = creator;
		this.createTime = createTime;
		this.userId = userId;
		this.permissionId = permissionId;
	}

	public SysUserPermission(Integer rolePermissionId, String rolePermissionStatus, String creator, Date createTime,
                             Integer userId, Integer permissionId) {
		super();
		this.rolePermissionId = rolePermissionId;
		this.rolePermissionStatus = rolePermissionStatus;
		this.creator = creator;
		this.createTime = createTime;
		this.userId = userId;
		this.permissionId = permissionId;
	}

	public Integer getRolePermissionId() {
		return rolePermissionId;
	}

	public void setRolePermissionId(Integer rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}

	public String getRolePermissionStatus() {
		return rolePermissionStatus;
	}

	public void setRolePermissionStatus(String rolePermissionStatus) {
		this.rolePermissionStatus = rolePermissionStatus;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

}
