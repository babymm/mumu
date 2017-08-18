package com.lovecws.mumu.data.car.entity;

import com.lovecws.mumu.common.core.entity.PersistentEntity;

public class DataCarSizeType extends PersistentEntity {

	private static final long serialVersionUID = 6732306260102774751L;

	private Integer sizetypeId;// 主键id
	private String sizetypeName;// 汽车型号名称
	private String sizetypeStatus;// 状态（1、可用；2、不可用）
	private Integer sizetypeRecomm;// 推荐条件（1、推荐；2、不推荐）
	
	public Integer getSizetypeId() {
		return sizetypeId;
	}
	public void setSizetypeId(Integer sizetypeId) {
		this.sizetypeId = sizetypeId;
	}
	public String getSizetypeName() {
		return sizetypeName;
	}
	public void setSizetypeName(String sizetypeName) {
		this.sizetypeName = sizetypeName;
	}
	public String getSizetypeStatus() {
		return sizetypeStatus;
	}
	public void setSizetypeStatus(String sizetypeStatus) {
		this.sizetypeStatus = sizetypeStatus;
	}
	public Integer getSizetypeRecomm() {
		return sizetypeRecomm;
	}
	public void setSizetypeRecomm(Integer sizetypeRecomm) {
		this.sizetypeRecomm = sizetypeRecomm;
	}
}
