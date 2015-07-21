package cn.lnu.web.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lnu.domain.Customer;
import cn.lnu.service.BusinessService;
import cn.lnu.service.impl.BusinessServiceImpl;
import cn.lnu.utils.Globals;
import cn.lnu.utils.WebUtils;


public class EditCustomerServlet extends HttpServlet {
	//根据id获取需要修改的客户信息
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		BusinessService service=new BusinessServiceImpl();
		Customer c=service.findCustomer(id);//根据id查找用户
		
		//将获得查找的要修改用户回显给修改当前用户界面
		request.setAttribute("c", c);
		request.setAttribute("genders", Globals.genders);
		request.setAttribute("preferences", Globals.preferences);
		request.setAttribute("types", Globals.types);
		request.getRequestDispatcher("/WEB-INF/jsp/editCustomer.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解决提交数据的中文乱码问题
		request.setCharacterEncoding("UTF-8");
		//把填写的表单的修改信息封装到customer对象中
		try{
			String id=request.getParameter("id");
			Customer c=WebUtils.requestToBean(request, Customer.class);
			BusinessService service=new BusinessServiceImpl();
			service.updateCustomer(c);
			request.setAttribute("message", "更新用户成功！");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "更新用户失败！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
}
