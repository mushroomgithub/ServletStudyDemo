package cn.lnu.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lnu.service.BusinessService;
import cn.lnu.service.impl.BusinessServiceImpl;

public class DelCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			String id=request.getParameter("id");
			System.out.println(id);
			BusinessService service=new BusinessServiceImpl();
			service.deleteCustomer(id);
			request.setAttribute("message", "删除用户成功！");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "删除用户失败！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
