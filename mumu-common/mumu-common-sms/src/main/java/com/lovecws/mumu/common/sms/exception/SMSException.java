package com.lovecws.mumu.common.sms.exception;

/**
 * 发送短信出现异常
 * @author ganliang
 */
public class SMSException extends Exception{

	private static final long serialVersionUID = 87383339211212903L;

	public SMSException() {
		super();
	}

	public SMSException(String msg, Throwable e) {
		super(msg, e);
	}

	public SMSException(Throwable e) {
		super(e);
	}
	
	public SMSException(String msg) {
		super(msg);
	}
}
