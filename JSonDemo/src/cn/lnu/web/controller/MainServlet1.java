package cn.lnu.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import cn.lnu.service.BusinessService;
import cn.lnu.service.impl.BusinessServiceImpl;
//处理主界面请求的servlet
public class MainServlet1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String id=request.getParameter("id");
		System.out.print(id);
		if(id==null||id.trim().equals("")){
			request.setAttribute("message", "请传递正确的操作码！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		BusinessService service=new BusinessServiceImpl();
		
		Object bean=null;
		JSONObject json=null;
		if (id.length() == 2 && id.trim().startsWith("0")) {
			bean = service.find(id);	
		} else if (id.length() == 2 && id.trim().startsWith("1")) {
			bean = service.find1(id);
		}
		
		if(bean!=null){
			json = JSONObject.fromObject(bean);
			String jstr = json.toString();
			System.out.print("Mainface "+jstr);
			HttpSession session=request.getSession();
			session.setAttribute("bean", bean);
			this.getServletContext().setAttribute("bean1", bean);
			//response.getOutputStream().write(jstr.getBytes("UTF-8"));
			response.sendRedirect("/JSonDemo/servlet/ScreenServlet?id=10000");
			//request.getRequestDispatcher("/servlet/ScreenServlet").forward(request,response);
		}
		
			
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
