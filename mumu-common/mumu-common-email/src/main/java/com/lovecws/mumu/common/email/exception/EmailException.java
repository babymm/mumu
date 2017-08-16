package com.lovecws.mumu.common.email.exception;

/**
 * @desc 邮件发送、接收异常
 * @author ganliang
 */
public class EmailException extends Exception {

	private static final long serialVersionUID = -57874834638140846L;

	public EmailException() {
		super();
	}

	public EmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailException(String message) {
		super(message);
	}

	public EmailException(Throwable cause) {
		super(cause);
	}

}
