package cn.lnu.demo;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class AttachMail {

	/**
	 * 带附件的邮件
	 * @param args
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void main(String[] args) throws Exception {
		//创建邮件
		Session session = Session.getDefaultInstance(new Properties());
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("aaa@flx.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("bbb@flx.com"));
		message.setSubject("test");
		
		//创建封装正文数据的bodypart
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("aaaaaaaaaaaa", "text/html");
		
		//创建封装附件数据的bodypart
		MimeBodyPart attach = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource("src/1.mp3"));
		attach.setDataHandler(dh);
		attach.setFileName(dh.getName());   //设置附件名
		
		//mixed
		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(text);
		mm.addBodyPart(attach);
		mm.setSubType("mixed");
		
		message.setContent(mm);
		message.saveChanges();
		
		message.writeTo(new FileOutputStream("c:\\1.eml"));

	}

}
