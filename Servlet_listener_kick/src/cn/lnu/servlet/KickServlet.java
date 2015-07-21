package cn.lnu.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//处理踢人的servlet
public class KickServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username=request.getParameter("username");
		username=new String(username.getBytes("iso8859-1"),"UTF-8");//解决乱码
		
		Map map=(Map) this.getServletContext().getAttribute("map");
		HttpSession session=(HttpSession) map.get(username);
		if(session!=null){
			session.invalidate();
			map.remove(username);
		}
		
		response.sendRedirect("/Servlet_listener_kick/listUser.jsp");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
