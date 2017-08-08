package com.lovecws.mumu.common.core.entity;

import java.util.Date;

/**
 * @desc 基类
 * @author ganliang
 * @version 2016年8月10日 上午9:40:41
 */
public class BaseEntity extends PersistentEntity {

	private static final long serialVersionUID = -782928088894116018L;
	private int id;// 主键ID.
	private String status;// 状态 PublicStatusEnum

	private String creator;//创建者
	private Date createTime;// 创建时间.
	private String editor;//修改者
	private Date editTime;// 修改时间.
	private String remark;// 描述

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
}
