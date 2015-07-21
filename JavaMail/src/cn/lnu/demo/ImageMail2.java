package cn.lnu.demo;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ImageMail2 {

	/**
	 * @param args
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void main(String[] args) throws Exception {
		
		
		//´´½¨ÓÊ¼þ
		Session session = Session.getDefaultInstance(new Properties());
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("aaa@flx.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("bbb@flx.com"));
		message.setSubject("test");
		//servlet   1.jpg
		message.setContent("aaaaa<br/><img src='http://localhost:8080/mail/1.jpg'>", "text/html");
		
		message.saveChanges();
		
		message.writeTo(new FileOutputStream("c:\\1.eml"));
	}

}
