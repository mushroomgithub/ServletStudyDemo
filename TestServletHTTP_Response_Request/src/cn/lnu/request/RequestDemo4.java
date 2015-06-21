package cn.lnu.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//假设浏览器端采用的是UTF-8码表打开
public class RequestDemo4 extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//test1(request);
		test2(request);
	}
	//解决以Get方式提交产生的乱码问题
	private void test1(HttpServletRequest request)
			throws UnsupportedEncodingException {
		
		String username=request.getParameter("username");
		
		//如果是GET方式提交过来的数据，只能自己手动修改返回字符串的码表，首先获得request经iso8859-1编码之后的数，之后再设置以新的码表(必须与当前浏览器码表一致)转化当前数字形成一个字符串
		username=new String(username.getBytes("iso8859-1"),"UTF-8");
		
		System.out.println(username);
	}

	//解决以POST方式提交产生的乱码问题，只需要设置一下request的码表为浏览器使用的码表，不让它使用默认的iso8859-1码表
	private void test2(HttpServletRequest request)
			throws UnsupportedEncodingException {
		
		//设置request应该使用的码表，但是这种方法只能处理以Post方式提交的数据
		request.setCharacterEncoding("UTF-8");
		
		String username=request.getParameter("username");
		System.out.println(username);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
