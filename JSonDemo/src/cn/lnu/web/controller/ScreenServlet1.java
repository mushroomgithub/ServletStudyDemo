package cn.lnu.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public class ScreenServlet1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id=request.getParameter("id");
		
		if(id.trim().equals("10000")){
			HttpSession session=request.getSession();
			Object bean=session.getAttribute("bean");
			Object bean1=this.getServletContext().getAttribute("bean1");
			if(bean1==null){
				response.getOutputStream().write("0000".getBytes("UTF-8"));
			}
			
			JSONObject json = JSONObject.fromObject(bean1);
			String jstr = json.toString();
			System.out.println("Screenface "+jstr);
			response.getOutputStream().write(jstr.getBytes("UTF-8"));
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
