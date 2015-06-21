package cn.lnu.response;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//控制浏览器定时刷新
public class ResponseDemo5 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//test1(response);
		//test2(response);
		test3(request,response);
	}
	//非常实用的定时跳转页面技术
	private void test3(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//假设实际开发中，这是一个用户处理登陆的servlet
		
		//假设程序到此，用户登陆成功
		String message = "<meta http-equiv='refresh' content='3;url=/TestServletHTTP_Response_Request/index.jsp'>恭喜你，登陆成功，程序将在3秒后跳转到首页，如果没有跳转，请点击<a href='#'>超链接</a>";
		this.getServletContext().setAttribute("message", message);
		
		this.getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	private void test2(HttpServletResponse response) throws IOException {
		//假设实际开发中，这是一个用户处理登陆的servlet
		
		//假设程序到此，用户登陆成功
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("refresh","3;url='/TestServletHTTP_Response_Request/index.jsp'");
		response.getWriter().write("恭喜你，登陆成功，程序将在3秒后跳转到首页，如果没有跳转，请点击<a href='#'>超链接</a>");
	}

	private void test1(HttpServletResponse response) throws IOException {
		//控制浏览器刷新时间为3秒
		response.setHeader("refresh", "3");
		
		String data=new Random().nextInt(10000)+"";
		PrintWriter out=response.getWriter();
		out.write(data);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
