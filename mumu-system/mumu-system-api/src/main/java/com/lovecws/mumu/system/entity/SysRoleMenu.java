package com.lovecws.mumu.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 角色菜单实体
 * @author ganliang
 * @version 2016年9月10日 下午9:48:34
 */
public class SysRoleMenu implements Serializable {

	private static final long serialVersionUID = -9012707031072904356L;

	private Integer roleMenuId;// 主键ID.
	private String roleMenuStatus;// 状态 PublicStatusEnum
	private String creator;// 创建人.
	private Date createTime;// 创建时间.

	private Integer roleId;// 角色id
	private Integer menuId;// 菜单id

	public SysRoleMenu(String roleMenuStatus, String creator, Date createTime, Integer roleId,
			Integer menuId) {
		this.roleMenuStatus = roleMenuStatus;
		this.creator = creator;
		this.createTime = createTime;
		this.roleId = roleId;
		this.menuId = menuId;
	}

	public Integer getRoleMenuId() {
		return roleMenuId;
	}

	public void setRoleMenuId(Integer roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	public String getRoleMenuStatus() {
		return roleMenuStatus;
	}

	public void setRoleMenuStatus(String roleMenuStatus) {
		this.roleMenuStatus = roleMenuStatus;
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

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

}
