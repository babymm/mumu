package com.lovecws.mumu.common.core.response;

/**
 * @desc huasi 后台服务器状态吗
 * @author ganliang
 * @version 2016年8月9日 上午11:39:52
 */
public enum HttpCode {
	/**
	 * 请求成功
	 */
	OK(200, "success","请求成功"),
	
	/**
	 * 参数错误
	 */
	PARAMETER_ERROR(400, "parameter error","参数错误"),
	
	/**
	 * 服务出现异常
	 */
	SERVER_ERROR(500, "server error","服务出现异常"),
	
	/**
	 * 远程发发调用异常
	 */
	REMOTE_METHOD_CALL_ERROR(555, "remote method call error","远程发发调用异常");
	
	private final Integer code;
	private final String msg;
	private final String content;

	private HttpCode(Integer code, String msg,String content) {
		this.code = code;
		this.msg = msg;
		this.content=content;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public Integer code() {
		return this.code;
	}

	public String msg() {
		return this.msg;
	}
	
	public String content() {
		return this.content;
	}

	public String toString() {
		return this.code.toString();
	}
	
	
}
