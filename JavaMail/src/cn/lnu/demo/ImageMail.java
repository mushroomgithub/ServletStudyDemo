package cn.lnu.demo;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ImageMail {

	/**
	 * @param args
	 * @throws MessagingException 
	 */
	public static void main(String[] args) throws Exception {
	//创建邮件
		Session session=Session.getDefaultInstance(new Properties());
		MimeMessage message=new MimeMessage(session);

		message.setFrom(new InternetAddress("ms15941699808@163.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("862998004@qq.com"));
		message.setSubject("test");
		
		//创建邮件中的数据
		//1创建邮件正文
		MimeBodyPart text=new MimeBodyPart();
		text.setContent("shfhafha;fha<br/><img src='cid:image1'><br/>fha;hfajh", "text/html");
		
		//2创建图片数据
		MimeBodyPart image=new MimeBodyPart();
		DataHandler dh=new DataHandler(new FileDataSource("src/1.jpg"));  //jaf技术，数据处理器，会自动感知文件类型，经感知的文件数据写到image中,所以就不用我们自己手动设置数据类型了
		image.setDataHandler(dh);
		image.setContentID("image1");
		
		//描述数据之间的关系
		MimeMultipart mm=new MimeMultipart();
		mm.addBodyPart(text);
		mm.addBodyPart(image);
		mm.setSubType("related");
		
		message.setContent(mm);
		message.saveChanges();
		
		message.writeTo(new FileOutputStream("c:\\1.eml"));
	}

}
