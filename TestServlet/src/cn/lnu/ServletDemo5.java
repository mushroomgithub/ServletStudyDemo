package cn.lnu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
  servletContext域：
  1，说明这是一个容器
  2，servletContext域这句话说明了这个容器作用范围，也就是应用程序作用范围
 */
//测试获得从servletDemo4中写入到TestServlet域这个web应用的共享资源，实现servletDemo4与servletDemo5的数据共享
public class ServletDemo5 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String value=(String)this.getServletContext().getAttribute("data");
		System.out.print(value);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
