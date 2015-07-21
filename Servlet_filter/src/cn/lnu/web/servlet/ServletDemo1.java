package cn.lnu.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo1 extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//测试真正解决全站乱码，这里就不想以前一样还要一上来就先解决中文乱码问题
		String username=request.getParameter("username");
		response.getWriter().write("中国");
		//如果前台这个属性中存放的是js代码，需要对其转义才能存到数据库，而getParameter获得数据的不会对内容转义，这样以后去数据库中取出数据时，这段代码会被执行，
		String resume=request.getParameter("resume");
		response.getWriter().write(resume);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
