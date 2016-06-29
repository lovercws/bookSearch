package com.kingbase.bookSearch.core.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.log4j.Logger;

public class SimpleMailReceiver {
	/**
	 * 发送邮件的props文件
	 */
	private final transient Properties props = System.getProperties();
	private static final Logger log = Logger.getLogger(SimpleMailSender.class);
	/**
	 * 邮件服务器登录验证
	 */
	private transient MailAuthenticator authenticator;

	private Store store;
	/**
	 * 邮箱session
	 */
	private transient Session session;

	/**
	 * 初始化
	 * 
	 * @param username发送邮件的用户名(地址)
	 * @param password
	 *            密码
	 * @param smtpHostNameSMTP主机地址
	 * @throws MessagingException
	 */
	private void init(String username, String password, String smtpHostName) throws MessagingException {
		// 初始化props
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpHostName);
		// 验证
		authenticator = new MailAuthenticator(username, password);
		// 创建session
		session = Session.getInstance(props, authenticator);
		store = session.getStore("pop3");
		store.connect(smtpHostName, username, password);
	}

	/**
	 * 初始化邮件发送器
	 * @param smtpHostNameSMTP邮件服务器地址
	 * @param username发送邮件的用户名(地址)
	 * @param password发送邮件的密码
	 * @throws MessagingException
	 */
	public SimpleMailReceiver(final String smtpHostName, final String username, final String password)
			throws MessagingException {
		init(username, password, smtpHostName);
	}

	public void receive() throws MessagingException, IOException {
		// 获取inbox文件
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY); // 打开，打开后才能读取邮件信息

		// 获取邮件消息
		Message message[] = folder.getMessages();

		for (int i = 0, n = message.length; i < n; i++) {
			log.info(i + ": " + message[i].getFrom()[0] + "\t" + message[i].getSubject());
			message[i].writeTo(System.out);
		}
		// 关闭资源
		folder.close(false);
		store.close();
	}

	public static void main(String[] args) throws MessagingException, IOException {

		SimpleMailReceiver receiver = new SimpleMailReceiver("127.0.0.1", "admin", "lovecws");
		receiver.receive();
	}
}
