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
 * @desc 权限实体
 * @author ganliang
 * @version 2016年9月10日 下午9:46:09
 */
public class SysPermission implements Serializable {

	private static final long serialVersionUID = -2175597348886393330L;

	private Integer permissionId;// 主键ID.
	private String permissionStatus;// 状态 PublicStatusEnum
	private String creator;// 创建人.
	private Date createTime;// 创建时间.

	private String editor;// 修改人.
	private Date editTime;// 修改时间.
	private String remark;// 描述

	private String permissionCode; // 权限内码
	private String permissionName; // 权限名称
	private String permission; // 权限标识

	private Integer menuId;// 菜单id（权限是从菜单项中派发出来的）
	private String permissionPath;// 权限路径

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionStatus() {
		return permissionStatus;
	}

	public void setPermissionStatus(String permissionStatus) {
		this.permissionStatus = permissionStatus;
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

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getPermissionPath() {
		return permissionPath;
	}

	public void setPermissionPath(String permissionPath) {
		this.permissionPath = permissionPath;
	}

	@Override
	public String toString() {
		return "SysPermission [permissionId=" + permissionId + ", permissionStatus=" + permissionStatus + ", creator="
				+ creator + ", createTime=" + createTime + ", editor=" + editor + ", editTime=" + editTime + ", remark="
				+ remark + ", permissionCode=" + permissionCode + ", permissionName=" + permissionName + ", permission="
				+ permission + ", menuId=" + menuId + ", permissionPath=" + permissionPath + "]";
	}

}
