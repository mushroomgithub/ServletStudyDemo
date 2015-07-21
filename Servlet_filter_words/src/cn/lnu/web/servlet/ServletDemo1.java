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
		//过滤器中在放行之前需要增强request的getParameter方法获得审核词高亮数据或者替换词替换之后的数据
		String data=request.getParameter("resume");
		//我有一把仿真手枪，你要电鸡和****吗？，增强之后的结果是，仿真手枪和电鸡等审核词高亮成红色，四大舰队替换词，替换词****
		response.getWriter().write(data);//写给浏览器看看效果,在servletDemo1这个页面看效果，获得数据我有一把仿真手枪，你要电鸡吗？,这个仿真手枪和电鸡就高亮成红色了，然后交给管理员去审核
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
