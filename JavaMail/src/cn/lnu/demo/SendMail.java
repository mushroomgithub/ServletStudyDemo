package cn.lnu.demo;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SendMail {

	/**
	 * @param 使用javamailAPI发送邮件
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
		
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.host", "localhost");
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		
		javax.mail.Session session = javax.mail.Session.getInstance(prop);
		session.setDebug(true);
		
		Message message = createMessage(session);
		Transport ts = session.getTransport();
		ts.connect("aaa", "123");
		ts.sendMessage(message,message.getAllRecipients());
		ts.close();
		
	}

	private static Message createMessage(Session session) throws AddressException, MessagingException, UnsupportedEncodingException {
		//创建邮件
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("aaa@flx.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("bbb@flx.com"));
		message.setSubject("test");
		
		
		message.setContent("aaaaaaaaaaaa", "text/html");
		message.saveChanges();
		
		return message;
	}

}
