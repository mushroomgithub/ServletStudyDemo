package cn.lnu.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public class ScreenServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id=request.getParameter("id");
		
		if(id.trim().equals("10000")){
			Object bean=this.getServletContext().getAttribute("bean");
			/*if(bean==null){
				response.getOutputStream().write("0000".getBytes("UTF-8"));
			}*/
			
			JSONObject json = JSONObject.fromObject(bean);
			String jstr = json.toString();
			System.out.println("Screenface "+jstr);
			response.getOutputStream().write(jstr.getBytes("UTF-8"));
			//this.getServletContext().removeAttribute("bean");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
