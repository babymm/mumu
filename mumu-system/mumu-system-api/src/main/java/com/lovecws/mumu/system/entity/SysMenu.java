package com.lovecws.mumu.system.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @desc 系统菜单
 * @author ganliang
 * @version 2016年9月10日 下午10:16:53
 */
public class SysMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer menuId;// 主键ID.
	private String menuStatus;// 状态 PublicStatusEnum
	private String creator;// 创建人.
	private Date createTime;// 创建时间.

	private String editor;// 修改人.
	private Date editTime;// 修改时间.
	private String remark;// 描述

	private String menuCode;// 菜单内码
	private String menuName;// 菜单名称
	private String menuUrl;// 菜单地址

	private Integer menuNum;// 菜单编号（用于显示时排序）
	private boolean isLeaf;// 是否为叶子节点
	private String menuIcon;// 菜单图标

	private String menuVisible;// 菜单可见 show,hidden
	private Integer level;// 菜单层级
	private Integer parentMenuId;// 父节点:一级菜单为0

	private List<SysMenu> childrens;
	public SysMenu() {
	}

	public SysMenu(int menuId,String menuCode, String menuName, String menuUrl,int parentMenuId) {
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
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

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getMenuNum() {
		return menuNum;
	}

	public void setMenuNum(Integer menuNum) {
		this.menuNum = menuNum;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuVisible() {
		return menuVisible;
	}

	public void setMenuVisible(String menuVisible) {
		this.menuVisible = menuVisible;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(Integer parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public List<SysMenu> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<SysMenu> childrens) {
		this.childrens = childrens;
	}

	@Override
	public String toString() {
		return "SysMenu [menuId=" + menuId + ", menuStatus=" + menuStatus + ", creator=" + creator + ", createTime="
				+ createTime + ", editor=" + editor + ", editTime=" + editTime + ", remark=" + remark + ", menuName="
				+ menuName + ", menuCode=" + menuCode + ", menuUrl=" + menuUrl + ", menuNum=" + menuNum + ", isLeaf="
				+ isLeaf + ", menuIcon=" + menuIcon + ", menuVisible=" + menuVisible + ", level=" + level
				+ ", parentMenuId=" + parentMenuId + "]";
	}

	private String parentMenuName;//父菜单名称
	public String getParentMenuName() {
		return parentMenuName;
	}
	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}
}
