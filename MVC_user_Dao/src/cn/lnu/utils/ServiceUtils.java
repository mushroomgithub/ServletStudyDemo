package cn.lnu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

//用于处理用户密码，使其先经过MD5码加密后再保存到数据库
public class ServiceUtils {
	//传递过来一个信息，返回一个该信息的摘要
	public static String md5(String message){
		//要处理md5码，找这个类的方法获得一个可以处理md5算法的digest
		try {
			MessageDigest md=MessageDigest.getInstance("md5");
			byte md5[]=md.digest(message.getBytes());//获得这个消息的摘要，然后返回一个摘要数组
			//不能直接将这个密文的摘要返回，必须先使用BASE64将其转换为明文之后再返回
			BASE64Encoder encoder=new BASE64Encoder();//先获得一个可以执行BASE64算法的对象，之后调用encode方法对密文编码成明文
			return encoder.encode(md5);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}
}
