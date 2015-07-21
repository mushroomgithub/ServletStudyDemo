package cn.lnu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lnu.domain.User;
//³öÀ´µÇÂ½µÄservlet
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		
		request.getSession().setAttribute("user", user);
		response.sendRedirect("/Servlet_listener_kick/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
