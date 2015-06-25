package cn.lnu.form;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.misc.BASE64Encoder;
//利用程序产生表单，并且为表单附带一个随机数
public class FormServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//产生一个随机数(表单号)
		TokenProcessor tp=TokenProcessor.getInstance();
		String token=tp.generateToken();
		
		//跳转到jsp中给用户输出一个表单
		//如何将表单号带给jsp，使用session带过去
		HttpSession session=request.getSession();
		session.setAttribute("token", token);
		
		request.getRequestDispatcher("/form.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

class TokenProcessor{//随机数发生器，Token令牌，为了保证产生的令牌为单一的，所以将这个令牌发生器设计成单例的
	/*
	 * 	1,把构造函数私有
	 * 2，自己创建一个
	 * 3，对外暴露一个方法，允许获取上面创建的对象
	 * */
	private TokenProcessor(){
		
	}
	
	private static final TokenProcessor instance=new TokenProcessor();
	public static TokenProcessor getInstance(){
		return instance;
	}
	
	public String generateToken(){//根据当前时间毫秒值以及一个随机数，得到一个唯一的随机数
		String token=System.currentTimeMillis()+new Random().nextInt()+"";
		//设置随机数长度一致，需要获得随机数指纹/摘要，固定128位
		try {//产生随机128位长度摘要的一般步骤
			MessageDigest md=MessageDigest.getInstance("md5");
			byte[] md5=md.digest(token.getBytes());
			//为了防止直接构建string返回的随机数是随机乱码串，需要采用base64编码，它能md5数组中的每三个字节转化成四个字节，这样这三个字节保存到四个字节中
			//那么四个字节的每个字节保存原来三个字节的6位。将每三个字节转为四个字节之后，这四个字节的数据的特点是，每个字节的有效位只是后六位，前两位补零，那么每个字节的
			//范围是00000000--00111111（0~63）,这么一转码，那么四个字节的每个字节对应的字符都是人眼熟悉的可见的字符
			BASE64Encoder encoder=new BASE64Encoder();
			
			return encoder.encode(md5);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
