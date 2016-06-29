package com.kingbase.bookSearch.core.email;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;

/**
 * 发送邮件
 * @author ganliang
 */
public class SimpleMailService {
	
	 private static final Logger log=Logger.getLogger(SimpleMailSender.class);
	/**
	 * 发送邮件
	 * @param recipient 发送目的地邮箱
	 * @param subject 邮件的标题
	 * @param mail发送邮箱的内容
	 */
	public boolean sendSimpleMail(String mail, String subject, String content) {
		SimpleMailSender sms = new SimpleMailSender(userName, password);
		boolean sendSuccess=false;
		try {
			sms.send(mail, subject, content);
			log.info("邮件【"+mail+"】发送成功");
			sendSuccess=true;
		} catch (AddressException e) {
			log.error(e);
		} catch (MessagingException e) {
			log.error(e);
		}
		return sendSuccess;
	}

	//setter 注入
	private String userName;// 用户名
	private String password;// 密码
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
