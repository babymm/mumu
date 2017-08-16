package com.lovecws.mumu.common.email.service;

import com.lovecws.mumu.common.email.bean.SimpleEmail;
import com.lovecws.mumu.common.email.exception.EmailException;

/**
 * @desc 邮件接收接口
 * @author ganliang
 */
public interface EmailReceiveService {

	/**
	 * 接收邮件信息
	 * @throws EmailException
	 */
	public SimpleEmail[] receiveAll() throws EmailException;
	
	/**
	 * 接收邮件
	 * @param msgnum
	 * @throws EmailException
	 */
	public SimpleEmail receive(int msgnum) throws EmailException;
}
