package com.lovecws.mumu.common.core.enums;

/**
 * @desc 公共状态 0 分类禁用、1分类正常使用、2 分类删除
 * @author ganliang
 * @version 2016年8月11日 上午9:35:53
 */
public enum PublicEnum {

	/**
	 * 禁用 forbit
	 */
	FORBIDDEN("0"),

	/**
	 * 正常 normal
	 */
	NORMAL("1"),

	/**
	 * 删除 delete
	 */
	DELETE("2");

	/** 描述 */
	private String status;

	private PublicEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String value() {
		return status;
	}
}
