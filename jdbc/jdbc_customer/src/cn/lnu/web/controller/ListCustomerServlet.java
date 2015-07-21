package cn.lnu.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lnu.domain.PageBean;
import cn.lnu.domain.QueryInfo;
import cn.lnu.service.BusinessService;
import cn.lnu.service.impl.BusinessServiceImpl;
import cn.lnu.utils.WebUtils;
//处理用户分页请求
public class ListCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			QueryInfo queryinfo=WebUtils.requestToBean(request, QueryInfo.class);
			BusinessService service=new BusinessServiceImpl();
			PageBean pagebean=service.pageQuery(queryinfo);
			
			request.setAttribute("pagebean", pagebean);
			request.getRequestDispatcher("/WEB-INF/jsp/listCustomer.jsp").forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "查看用户失败！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
