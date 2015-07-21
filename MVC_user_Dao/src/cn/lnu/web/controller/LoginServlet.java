package cn.lnu.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lnu.domain.User;
import cn.lnu.service.impl.BusinessServiceImpl;
//处理登陆页面请求
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//验证用户提交的用户名和密码
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		BusinessServiceImpl service=new BusinessServiceImpl();
		User user=service.login(username, password);
		if(user!=null){
			//如果用户登陆成功，则向session存放一个登陆标记，标记成功登陆
			request.getSession().setAttribute("user", user);
			//让用户登陆成功后，跳转到首页
			
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}
		//否则登陆失败，让其跳转到全局消息显示页面，给用户友好提示
		request.setAttribute("message","用户名或密码错误！！");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
