package cn.lnu.response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseDemo1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//test1(response);
		//test2(response);
		//test3(response);
		test4(response);
	}
	//在servlet中用OutputStream(字节流)向客户端输出中文乱码的问题
	private void test1(HttpServletResponse response) throws IOException,
			UnsupportedEncodingException {
		//设置浏览器以什么码表显示服务器返回的数据,也即servlet程序以什么码表输出了，程序就一定要控制浏览器以什么码表打开显示
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		//向浏览器输出中国两个中文
		String data="中国";
		OutputStream out=response.getOutputStream();
		out.write(data.getBytes("UTF-8"));//以字节流并且使用UTF-8码表的方式返回给浏览器
	}
	
	private void test2(HttpServletResponse response) throws IOException,
			UnsupportedEncodingException {
		// 向浏览器输出中国两个中文
		String data = "中国";
		OutputStream out = response.getOutputStream();
		//html： <meta>标签的http-equiv可以模拟一个HTTP响应头(具体模拟哪个头由http-equiv指定)来控制浏览器显示数据采用什么码表
		out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>".getBytes());
		out.write(data.getBytes("UTF-8"));
	}
	
	//在servlet中用OutputStream输出中文乱码的问题
	private void test3(HttpServletResponse response) throws IOException,
			UnsupportedEncodingException {
		//注意，下面写错了，浏览器会提示下载
		response.setHeader("content-type", "text/html,charset=UTF-8");
		
		//向浏览器输出中国两个中文
		String data="中国";
		OutputStream out=response.getOutputStream();
		out.write(data.getBytes("UTF-8"));//以字节流并且使用UTF-8码表的方式返回给浏览器
	}
	
	//在servlet中用OutputStream输出数字显示却不是数字的问题
	private void test4(HttpServletResponse response) throws IOException,
			UnsupportedEncodingException {
		OutputStream out=response.getOutputStream();
		//out.write(1);
		//需要将数字1转化为字符串，再转化为字节流写回
		out.write((1+"").getBytes());
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
