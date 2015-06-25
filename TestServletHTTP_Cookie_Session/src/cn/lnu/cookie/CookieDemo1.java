package cn.lnu.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//假设代表网站首页，模仿使用cookie技术显示用户上次访问时间
public class CookieDemo1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out=response.getWriter();
		out.print("<a href='/TestServletHTTP_Cookie_Session/servlet/CookieDemo2'>删除上次访问时间:</a><br/>");
		out.print("尊敬的用户，你的上次访问时间是：");
		
		//获得用户的时间cookie
		Cookie cookies[]=request.getCookies();
		for(int i=0;cookies!=null&&i<cookies.length;i++){
			if(cookies[i].getName().equals("lastAccessTime")){
				long cookieValue=Long.parseLong(cookies[i].getValue());//得到用户上次访问的时间cookie值(默认毫秒值),并将其转换为时间值
				Date date=new Date(cookieValue); 
				out.print(date.toLocaleString());
			}
		}
		//给用户浏览器返回新的访问时间
		Cookie cookie=new Cookie("lastAccessTime",System.currentTimeMillis()+"");
		//设置cookie有效期
		cookie.setMaxAge(1*30*24*3600);
		//设置cookie的有效路径，表示访问什么路径下的web资源，带这个cookie过来
		cookie.setPath("/TestServletHTTP_Cookie_Session");//这里设置的路径表示我希望当用户访问网站下TestServletHTTP_Cookie_Session这个目录下的资源时都带这个cookie过来
		
		//将cookie写给浏览器
		response.addCookie(cookie);
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
