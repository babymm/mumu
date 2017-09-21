package com.lovecws.mumu.data.area.entity;

import com.lovecws.mumu.common.core.entity.PersistentEntity;

/**
 * 城市信息
 */
public class DataAreaCity extends PersistentEntity {

	private static final long serialVersionUID = -5838777657551072305L;

	private Integer cId;// 主键id（自增）
    private String cCode;// 市编码
    private String pCode;// 省编码

    private String cName;// 市全写
    private String cSname;// 市的简写
    private String cPinying;// 市的简拼

    private String cPy;// 市的英文缩写

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcSname() {
		return cSname;
	}

	public void setcSname(String cSname) {
		this.cSname = cSname;
	}

	public String getcPinying() {
		return cPinying;
	}

	public void setcPinying(String cPinying) {
		this.cPinying = cPinying;
	}

	public String getcPy() {
		return cPy;
	}

	public void setcPy(String cPy) {
		this.cPy = cPy;
	}
	
}
