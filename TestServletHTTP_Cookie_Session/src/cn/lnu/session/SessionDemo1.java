package cn.lnu.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//购买session
public class SessionDemo1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		//在得到session对象的同时将服务器为session对象创建的session id号以cookie的方式写回给浏览器，并且设置一下cookie的有效期为30天，
		//这样的话，就不会造成之前创建的session在浏览器突然被中途关闭，再次打开找不到原来session对象的状况，而重新创建session
		String sessionid=session.getId();
		Cookie cookie=new Cookie("JSESSIONID",sessionid);
		cookie.setPath("/TestServletHTTP_Cookie_Session");
		cookie.setMaxAge(1*30*24*3600);
		response.addCookie(cookie);
		
		session.setAttribute("name","笔记本");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
