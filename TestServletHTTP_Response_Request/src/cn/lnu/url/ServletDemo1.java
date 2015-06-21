package cn.lnu.url;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//url该如何写，主要看这个url是提供给谁用，给浏览器或者服务器用 /的含义不同
public class ServletDemo1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1 给服务器用，/表示web应用即是代表那个WebRoot
		request.getRequestDispatcher("/form.html").forward(request, response);
		
		//2 给浏览器用，/表示网站地址
		response.sendRedirect("/TestServletHTTP_Response_Request/form.html");
		
		//3给服务器用，/表示web应用即是代表那个WebRoot
		this.getServletContext().getRealPath("/form.html");
		
		//4 给服务器用，/表示web应用即是代表那个WebRoot
		this.getServletContext().getResourceAsStream("/public/head.jsp");
		
		
		//5
		/*给浏览器用的话，/表示网站地址
		 * <a href="/TestServletHTTP_Response_Request/form.html">点我</a>
		 * 
		 * <form action="/TestServletHTTP_Response_Request/form.html">
		 * 
		 * </form>
		 * 
		 * 
		 * */
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
