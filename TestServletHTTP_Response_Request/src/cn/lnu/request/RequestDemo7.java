package cn.lnu.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//forward细节2:forward时，会清空response中的数据，但是不会已经写入到HttpServletResponse对象的响应头字段信息将保持，所以你只能看到index.jsp页面的原始数据，但是看不到刚写入的aaaa
public class RequestDemo7 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data="aaaa";
		response.getWriter().write(data);
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
