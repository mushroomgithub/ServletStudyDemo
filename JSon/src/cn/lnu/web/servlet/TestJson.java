package cn.lnu.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import cn.lnu.domain.Control;

public class TestJson extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");
		//response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");
		String username=request.getParameter("username");
		if(username.equals("abc")){
			Control bean=new Control();
			bean.setMcount("2");
			bean.setScount("2");
			bean.setTcount("2");
			bean.setVcount("1");
			JSONObject json = JSONObject.fromObject(bean);
			String jstr=json.toString();
			//request.setAttribute("jstr", jstr);
			//request.getRequestDispatcher("/message.jsp").forward(request, response);
			//response.getWriter().write(jstr);
			response.getOutputStream().write(jstr.getBytes());
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
