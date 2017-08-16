package com.lovecws.mumu.common.email.receiver;

import com.lovecws.mumu.common.email.bean.EmailAuthenticator;
import com.lovecws.mumu.common.email.bean.SimpleEmail;
import com.lovecws.mumu.common.email.listeners.EmailMessageCountListener;
import com.lovecws.mumu.common.email.utils.AddressUtil;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import java.io.IOException;
import java.util.Properties;


public class SimpleEmailReceiver {
	/**
	 * 发送邮件的props文件
	 */
	private final transient Properties props = System.getProperties();
	/**
	 * 邮件服务器登录验证
	 */
	private transient EmailAuthenticator authenticator;

	private Store store;
	/**
	 * 邮箱session
	 */
	private transient Session session;

	/**
	 * 初始化
	 * @param username发送邮件的用户名(地址)
	 * @param password密码
	 * @param smtpHostNameSMTP主机地址
	 * @throws MessagingException
	 */
	private void init(String username, String password, String pop3HostName) throws MessagingException {
		// 初始化props
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", pop3HostName);
		// 验证
		authenticator = new EmailAuthenticator(username, password);
		// 创建session
		session = Session.getInstance(props, authenticator);
		store = session.getStore("pop3");
		store.connect(pop3HostName, username, password);
	}

	/**
	 * 初始化邮件发送器
	 * @param pop3HostName接收邮件服务器地址
	 * @param username发送邮件的用户名(地址)
	 * @param password发送邮件的密码
	 * @throws MessagingException
	 */
	public SimpleEmailReceiver(final String pop3HostName, final String username, final String password)
			throws MessagingException {
		init(username, password, pop3HostName);
	}

	/**
	 * 获取邮件
	 * @param msgnum 邮件数量
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public SimpleEmail receive(int msgnum) throws MessagingException, IOException {
		// 获取inbox文件
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY); // 只读方式访问邮件
		Message message = folder.getMessage(msgnum);
		// 获取邮件消息
		SimpleEmail simpleEmail = handleMessage(message);
		return simpleEmail;
	}

	/**
	 * 获取所有的邮件
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public SimpleEmail[] receiveAll() throws MessagingException, IOException {
		// 获取inbox文件
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY); // 只读方式答问邮件

		folder.addMessageCountListener(new EmailMessageCountListener());
		// 获取邮件消息
		Message message[] = folder.getMessages();

		SimpleEmail[] emails = new SimpleEmail[message.length];
		for (int i = 0, n = message.length; i < n; i++) {
			emails[i] = handleMessage(message[i]);
		}
		// 关闭资源
		folder.close(false);
		store.close();
		return emails;
	}

	/**
	 * 获取邮件消息
	 * @param msg
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	private SimpleEmail handleMessage(Message msg) throws MessagingException, IOException {
		SimpleEmail email = new SimpleEmail();

		Address[] addresses = msg.getFrom();
		if (addresses != null && addresses.length > 0) {
			email.setSender(addresses[0].toString());
		}
		// 接收者
		Address[] recipients = msg.getRecipients(RecipientType.TO);
		email.setRecipients(AddressUtil.addressToString(recipients));

		// 抄送
		Address[] copyTos = msg.getRecipients(RecipientType.CC);
		email.setCopyTos(AddressUtil.addressToString(copyTos));

		// 暗送
		Address[] bccs = msg.getRecipients(RecipientType.BCC);
		email.setBccs(AddressUtil.addressToString(bccs));

		// 主题
		String subject = msg.getSubject();
		email.setSubject(subject);

		// 内容
		Object content = msg.getContent();
		// 普通邮件
		if (content instanceof String) {
			email.setContent(content);
		}

		// 附件
		else if (content instanceof Multipart) {
			Multipart mul = (Multipart) content;
			int bodyCount = mul.getCount();
			BodyPart[] bodyParts = new BodyPart[bodyCount];
			for (int j = 0; j < bodyCount; j++) {
				bodyParts[j] = mul.getBodyPart(j);
			}
		} else {
			email.setContent(content);
		}
		return email;
	}

	public static void main(String[] args) throws MessagingException, IOException {
		SimpleEmailReceiver receiver = new SimpleEmailReceiver("pop3.163.com", "gl515xxx@163.com", "915827225");
		SimpleEmail receive = receiver.receive(3);
		System.out.println(receive.getSender());
	}
}
