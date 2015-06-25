package cn.lnu.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//当用户禁用了cookie之后，可以采用在首页重写url的方式，这种方式在重写url之后会在之后追加一个sessionid
public class WelcomeSessionDemo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//解决中文乱码问题
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		request.getSession();
		
		String url1=response.encodeURL("/TestServletHTTP_Cookie_Session/servlet/SessionDemo1");
		String url2=response.encodeURL("/TestServletHTTP_Cookie_Session/servlet/SessionDemo2");
		out.print("<a href='"+url1+"'> 购买</a>");
		out.print("<a href='"+url2+"'> 结账</a>");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}
