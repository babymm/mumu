package com.lovecws.mumu.data.area.entity;

import com.lovecws.mumu.common.core.entity.PersistentEntity;

/**
 * 地区信息
 */
public class DataAreaRegion extends PersistentEntity {

	private static final long serialVersionUID = -1964686834328151697L;

	private Integer aId;// 主键id（自增）
	private String aCode;// 区编码
	private String cCode;// 市编码

	private String aName;// 区全写
	private String aSname;// 区的简写
	private String aPinying;// 区的简拼

	private String aPy;// 区的英文缩写

	public Integer getaId() {
		return aId;
	}

	public void setaId(Integer aId) {
		this.aId = aId;
	}

	public String getaCode() {
		return aCode;
	}

	public void setaCode(String aCode) {
		this.aCode = aCode;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getaSname() {
		return aSname;
	}

	public void setaSname(String aSname) {
		this.aSname = aSname;
	}

	public String getaPinying() {
		return aPinying;
	}

	public void setaPinying(String aPinying) {
		this.aPinying = aPinying;
	}

	public String getaPy() {
		return aPy;
	}

	public void setaPy(String aPy) {
		this.aPy = aPy;
	}
	
}
