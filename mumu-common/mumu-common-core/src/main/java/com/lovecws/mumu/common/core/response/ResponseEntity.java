package com.lovecws.mumu.common.core.response;

import com.lovecws.mumu.common.core.entity.PersistentEntity;

import java.util.HashMap;

/**
 * @desc 服务器向客户端返回的数据
 * @author ganliang
 * @version 2016年8月8日 下午1:52:14
 */
public class ResponseEntity extends PersistentEntity {

	private static final long serialVersionUID = 1402067508931778446L;
	private int code;
	private String msg;
	private Object data;// 可以是对象、集合

	public ResponseEntity() {
		HttpCode code=HttpCode.OK;
		this.code = code.code();
		this.msg = code.msg();
		this.data=new HashMap<String,Object>();
	}

	public ResponseEntity(Object data) {
		this(HttpCode.OK,data);
	}

	public ResponseEntity(HttpCode code, Object data) {
		this.code = code.code();
		this.msg = code.msg();
		this.data = data;
	}

	public ResponseEntity(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
