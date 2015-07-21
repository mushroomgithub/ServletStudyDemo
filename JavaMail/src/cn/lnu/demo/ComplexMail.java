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
import javax.mail.internet.MimeUtility;

public class ComplexMail {

	/**
	 * 世界上最复杂的邮件
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//创建邮件
		Session session = Session.getDefaultInstance(new Properties());
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("aaa@flx.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("bbb@flx.com"));
		message.setSubject("测试");
		
		//创建bodypart封装正文
		MimeBodyPart text=new MimeBodyPart();
		text.setContent("这是中文文件<br/><img src='cid:image1'><br/>fha;hfajh", "text/html;charset=UTF-8");
		//创建bodypart封装图片
		MimeBodyPart image=new MimeBodyPart();
		DataHandler dh=new DataHandler(new FileDataSource("src/1.jpg"));  //jaf技术，数据处理器，会自动感知文件类型，经感知的文件数据写到image中,所以就不用我们自己手动设置数据类型了
		image.setDataHandler(dh);
		image.setContentID("image1");
		//创建bodypart封装附件
		MimeBodyPart attach = new MimeBodyPart();
		DataHandler dh2 = new DataHandler(new FileDataSource("src/光辉岁月.mp3"));
		attach.setDataHandler(dh2);
		attach.setFileName(MimeUtility.encodeText(dh2.getName()));   //设置附件名,文件如果是附件，必须调用这个函数，这个函数setFileName函数内部会设置content-disposition头
		
		//描述数据关系
		MimeMultipart content=new MimeMultipart();
		content.addBodyPart(text);
		content.addBodyPart(image);
		content.setSubType("related");
		MimeBodyPart mbp=new MimeBodyPart();
		mbp.setContent(content);
		
		MimeMultipart mm=new MimeMultipart();
		mm.addBodyPart(mbp);
		mm.addBodyPart(attach);
		mm.setSubType("mixed");
		
		message.setContent(mm);
		message.saveChanges();
		message.writeTo(new FileOutputStream("c:\\1.eml"));
		
		
	}

}
