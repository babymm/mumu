package com.lovecws.mumu.data.area.entity;

import com.lovecws.mumu.common.core.entity.PersistentEntity;

/**
 * 省份信息
 */
public class DataAreaProvince extends PersistentEntity {

	private static final long serialVersionUID = -339382258430591813L;

	private Integer pId;// 主键id（自增）
	private String pCode;// 省编码
	private String pName;// 省全写

	private String pSname;// 省的简写
	private String pPinying;// 省的简拼
	private String pPy;// 省的英文缩写

	private String pAbb;// 省的简称

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpSname() {
		return pSname;
	}

	public void setpSname(String pSname) {
		this.pSname = pSname;
	}

	public String getpPinying() {
		return pPinying;
	}

	public void setpPinying(String pPinying) {
		this.pPinying = pPinying;
	}

	public String getpPy() {
		return pPy;
	}

	public void setpPy(String pPy) {
		this.pPy = pPy;
	}

	public String getpAbb() {
		return pAbb;
	}

	public void setpAbb(String pAbb) {
		this.pAbb = pAbb;
	}
	
}
