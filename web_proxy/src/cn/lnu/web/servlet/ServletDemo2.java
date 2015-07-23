package cn.lnu.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.getWriter().write("中国");//这个时候在servlet获得response对象实际是是拦截器放行的responseProxy，调用getWriter方法写的数据并没有直接写给浏览器，而是写到底层缓冲流中了，然后经过压缩再写给浏览器
		response.getOutputStream().write("hahfalkhf蘑菇".getBytes());//hahfalkhf蘑菇
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doGet(request,response);
	}

}
