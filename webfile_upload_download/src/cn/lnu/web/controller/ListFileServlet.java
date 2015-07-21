package cn.lnu.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lnu.service.BusinessService;
import cn.lnu.service.impl.BusinessServiceImpl;
//列出所有供下载的文件
public class ListFileServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从数据库中获得所有上传在服务器供别人下载的文件的信息，每条记录是一个下载文件基本信息
		BusinessService service =new BusinessServiceImpl();
		List list=service.getAllUpfile();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/pages/listfiles.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doGet(request,response);
	}

}
