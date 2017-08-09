package com.lovecws.mumu.system.entity;

import com.lovecws.mumu.common.core.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 数据字典
 * @author ganliang
 * @version 2016年9月11日 上午11:56:20
 */
public class SysDDL extends BaseEntity {

	private String ddlCode;// 数据字典内码
	private String ddlValue;// 数据字典值
	private String parentCode;// 数据字典内码
	private String ddlNumber;// 数据字典排序

	public String getDdlCode() {
		return ddlCode;
	}

	public void setDdlCode(String ddlCode) {
		this.ddlCode = ddlCode;
	}

	public String getDdlValue() {
		return ddlValue;
	}

	public void setDdlValue(String ddlValue) {
		this.ddlValue = ddlValue;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getDdlNumber() {
		return ddlNumber;
	}

	public void setDdlNumber(String ddlNumber) {
		this.ddlNumber = ddlNumber;
	}
}
