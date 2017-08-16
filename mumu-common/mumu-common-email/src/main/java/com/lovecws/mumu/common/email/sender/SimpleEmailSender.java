package com.lovecws.mumu.common.email.sender;


import com.lovecws.mumu.common.email.bean.EmailAuthenticator;
import com.lovecws.mumu.common.email.bean.SimpleEmail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;


/**
 * 简单邮件发送器，可单发，群发。
 */
public class SimpleEmailSender {

    /**
     * 发送邮件的props文件
     */
    private final transient Properties props = System.getProperties();
    /**
     * 邮件服务器登录验证
     */
    private transient EmailAuthenticator authenticator;

    /**
     * 邮箱session
     */
    private transient Session session;
    
    /**
     * 初始化
     * @param username 发送邮件的用户名(地址)
     * @param password 密码
     * @param smtpHostName SMTP主机地址
     */
    private void init(String username, String password, String smtpHostName) {
        // 初始化props
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", smtpHostName);
        // 验证
        authenticator = new EmailAuthenticator(username, password);
        // 创建session
        session = Session.getInstance(props, authenticator);
    }
    
    /**
     * 初始化邮件发送器
     * @param smtpHostName SMTP邮件服务器地址
     * @param username 发送邮件的用户名(地址)
     * @param password 发送邮件的密码
     */
    public SimpleEmailSender(final String username,final String password,final String smtpHostName) {
        init(username, password, smtpHostName);
    }

    /**
     * 初始化邮件发送器
     * @param username 发送邮件的用户名(地址)，并以此解析SMTP服务器地址
     * @param password 发送邮件的密码
     */
    public SimpleEmailSender(final String username, final String password) {
        //通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
        final String smtpHostName = "smtp." + username.split("@")[1];
        init(username, password, smtpHostName);
    }

    /**
     * 发送邮件
     * @param recipient 收件人邮箱地址
     * @param copyTo 抄送人邮箱地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @throws AddressException
     * @throws MessagingException
     * @throws UnsupportedEncodingException 
     */
    public void send(String recipient,String copyTo, String subject, Object content)
            throws AddressException, MessagingException, UnsupportedEncodingException {
    	send(new SimpleEmail(recipient!=null?new String[]{recipient}:null,copyTo!=null?new String[]{copyTo}:null, null, subject, content));
    }

    /**
     * 群发邮件
     * @param recipients 收件人们
     * @param copyTos 抄送人
     * @param subject 主题
     * @param content 内容
     * @throws AddressException
     * @throws MessagingException
     * @throws UnsupportedEncodingException 
     */
    public void send(String[] recipients,String[] copyTos, String subject, Object content)
            throws AddressException, MessagingException, UnsupportedEncodingException {
        send(new SimpleEmail(recipients, copyTos, null, subject, content));
    }

    /**
     * 发送邮件
     * @param email
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public void send(SimpleEmail email) throws UnsupportedEncodingException, MessagingException{
   	 	// 创建mime类型邮件
        final MimeMessage message = new MimeMessage(session);
        // 设置发信人
        message.setFrom(new InternetAddress(authenticator.getUsername()));
        
        //设置收件人
        String[] recipients = email.getRecipients();
        if(recipients==null||recipients.length==0){
        	throw new IllegalArgumentException();
        }
        InternetAddress[] recipientsAddresses = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
        	recipientsAddresses[i] = new InternetAddress(recipients[i]);
		}
        message.setRecipients(Message.RecipientType.TO, recipientsAddresses);
        
        //设置抄件人
        String[] copyTos = email.getCopyTos();
        if(copyTos!=null&&copyTos.length>0){
        	InternetAddress[] copyTosAddresses = new InternetAddress[copyTos.length];
            for (int i = 0; i < copyTos.length; i++) {
            	copyTosAddresses[i] = new InternetAddress(copyTos[i]);
    		}
            message.setRecipients(Message.RecipientType.CC, copyTosAddresses);
        }
        
    	//设置附件
    	File[] multiparts = email.getMultiparts();
    	if(multiparts!=null&&multiparts.length>0){
    		MimeMultipart mp = new MimeMultipart();
        	for (File file : multiparts) {
        		BodyPart bp = new MimeBodyPart();
        		FileDataSource fileds = new FileDataSource(file);
        		bp.setDataHandler(new DataHandler(fileds));
        		bp.setFileName(MimeUtility.encodeText(fileds.getName()));
        		mp.addBodyPart(bp);
    		}
        	//添加附件
            message.setContent(mp);
    	}
        
        // 设置主题
        message.setSubject(email.getSubject());
        //设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件内容
        message.setContent(email.getContent(), "text/html;charset=utf-8");
        // 发送
        Transport.send(message);
    }

}