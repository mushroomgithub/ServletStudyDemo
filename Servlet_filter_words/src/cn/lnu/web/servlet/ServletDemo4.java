package cn.lnu.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//缓存过滤器，如果商品分类信息经常不变，可以将数据缓存，不用每次都去访问数据库，大大提高网站性能
public class ServletDemo4 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查询数据库，获得商品分类信息
		String data="分类信息";
		//输出分类信息
		response.getWriter().write(data);
		
		System.out.println("ServletDemo4执行,,,");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
