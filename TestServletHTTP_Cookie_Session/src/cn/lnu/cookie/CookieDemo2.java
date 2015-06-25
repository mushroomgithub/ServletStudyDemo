package cn.lnu.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//删除cookie
public class CookieDemo2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//若想删除某一个cookie，则new出的cookie的名字必须保持一致
		Cookie cookie=new Cookie("lastAccessTime",System.currentTimeMillis()+"");
		cookie.setMaxAge(0);
		//删除cookie时如果被删除cookie之前设置了path，则删除cookie也要设置相同的path
		cookie.setPath("/TestServletHTTP_Cookie_Session");
		response.addCookie(cookie);
		//删除之后重新重定向到原来页面
		response.sendRedirect("/TestServletHTTP_Cookie_Session/servlet/CookieDemo1");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
