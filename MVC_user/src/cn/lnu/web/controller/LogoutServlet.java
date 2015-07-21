package cn.lnu.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//处理注销请求
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		if(session!=null){
			session.removeAttribute("user");	
		}
		//注销成功，就跳到全局消息显示页面，并控制消息显示页面过3秒后跳转到首页
		request.setAttribute("message", "恭喜你,注销成功,浏览器将在3秒之后跳转到首页！，如果没有跳转，请点击链接<a href='"+request.getContextPath()+"/index.jsp'>跳转到首页</a><meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/index.jsp'");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
