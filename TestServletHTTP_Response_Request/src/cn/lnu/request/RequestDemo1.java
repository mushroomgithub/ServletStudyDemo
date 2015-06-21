package cn.lnu.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//request常用方法
public class RequestDemo1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//打印当前请求资源的URI和URL,URI用的很多
		System.out.println(request.getRequestURI());// /TestServletHTTP_Response_Request/servlet/RequestDemo1
		System.out.println(request.getRequestURL());// http://localhost:8080/TestServletHTTP_Response_Request/servlet/RequestDemo1
		//浏览器地址输入http://localhost:8080/TestServletHTTP_Response_Request/servlet/RequestDemo1?name=mushroom&&passwd=123456 ?之后表示查询字串
		System.out.println(request.getQueryString());//返回查询字串
		
		//得到来访者(一般是客户端)的IP地址
		System.out.println(request.getRemoteAddr());
		//返回发出请求的客户机的完整主机名（但是如果这个客户机没有在DNS上注册IP，则还是打印IP地址）
		System.out.println(request.getRemoteHost());
		//返回发出请求的客户机所使用的网络端口号
		System.out.println(request.getRemotePort());
		
		//返回web服务器的IP地址
		System.out.println(request.getLocalAddr());
		
		//返回web服务器的主机名
		System.out.println(request.getLocalName());
		
		//返回发出请求的客户机的请求方式
		System.out.println(request.getMethod());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
