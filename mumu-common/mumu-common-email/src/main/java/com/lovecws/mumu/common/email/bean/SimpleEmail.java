package com.lovecws.mumu.common.email.bean;

import java.io.File;
import java.util.Arrays;

/**
 * @desc 简单邮件
 * @author ganliang
 */
public class SimpleEmail {

	private String sender;//邮件发送者
	
	private String[] recipients;//发送

	private String[] copyTos;//抄送
	
	private String[] bccs;//暗送

	private File[] multiparts;

	private String subject;

	private Object content;

	public SimpleEmail() {
		super();
	}

	/**
	 * @param recipients 邮件收件人
	 * @param copyTos 邮件抄送人
	 * @param multiparts 邮件附件
	 * @param subject 邮件主题
	 * @param content 邮件文本内容
	 */
	public SimpleEmail(String[] recipients, String[] copyTos, File[] multiparts, String subject, Object content) {
		super();
		this.recipients = recipients;
		this.copyTos = copyTos;
		this.multiparts = multiparts;
		this.subject = subject;
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String[] getRecipients() {
		return recipients;
	}

	public void setRecipients(String[] recipients) {
		this.recipients = recipients;
	}

	public String[] getCopyTos() {
		return copyTos;
	}

	public void setCopyTos(String[] copyTos) {
		this.copyTos = copyTos;
	}

	public String[] getBccs() {
		return bccs;
	}

	public void setBccs(String[] bccs) {
		this.bccs = bccs;
	}

	public File[] getMultiparts() {
		return multiparts;
	}

	public void setMultiparts(File[] multiparts) {
		this.multiparts = multiparts;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "SimpleEmail [sender=" + sender + ", recipients=" + Arrays.toString(recipients) + ", copyTos="
				+ Arrays.toString(copyTos) + ", bccs=" + Arrays.toString(bccs) + ", multiparts="
				+ Arrays.toString(multiparts) + ", subject=" + subject + ", content=" + content + "]";
	}
}
