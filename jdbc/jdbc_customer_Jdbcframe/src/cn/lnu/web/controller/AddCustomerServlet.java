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
//这个servlet既可以为用户提供视图界面，也可以处理视图界面的post请求
public class AddCustomerServlet extends HttpServlet {
	
	//给用户提供一个添加用户视图界面
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("genders", Globals.genders);
		request.setAttribute("preferences", Globals.preferences);
		request.setAttribute("types", Globals.types);
		request.getRequestDispatcher("/WEB-INF/jsp/addcustomer.jsp").forward(request, response);
	}
	
	//处理用户在addcustomer.jsp页面的添加客户的post请求，这里必须是post请求，否则会跳转到doGet方法执行相关代码
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			request.setCharacterEncoding("UTF-8");
			String preference=request.getParameter("preference");
			//System.out.print(preference);
			//获得请求提交的参数之后,需要对表单数据校验，提供一个formbean将数据封装，校验，这里暂时不校验
			
			//把数据封装到customer对象,可以在工具包中在创建一个WebUtils工具类一次取出表单数据，
			Customer c=WebUtils.requestToBean(request, Customer.class);
			//往数据库中添加用户，需要为用户创建一个id
			c.setId(WebUtils.generateID());
			
			//调用service接口为web层添加用户功能提供支持,将新用户添加到数据库中去
			BusinessService service=new BusinessServiceImpl();
			service.addCustomer(c);
			request.setAttribute("message", "添加用户成功！");	
		}catch(Exception e){
			e.printStackTrace();//如果整个过程抛异常了，后台记录这个异常，并跳转到消息显示页面，给用户友好提示
			request.setAttribute("message", "添加用户失败！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
}
