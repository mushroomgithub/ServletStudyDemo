package cn.lnu.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//实现请求重定向
/*
 重定向的特点：
 * 1，浏览器会向服务器发送两次，意味着这就有2对request/response
 * 2.用重定向技术，浏览器地址栏会发生变化
 * 
 只有一些特定的场景下才用到重定向技术，一般是用户登录和显示购物车时
 * */
public class ResponseDemo7 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*response.setStatus(302);
		response.setHeader("location", "/TestServletHTTP_Response_Request/index.jsp");*/
		response.sendRedirect("/TestServletHTTP_Response_Request/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
