package cn.lnu;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//test servletConfig信息
//servletConfig对象：用于封住servlet的配置信息
//在实际开发中，有一些东西不适合在servlet程序中写死，这类数据可以通过配置方法配给servlet，例如：
//servlet采用哪个字符编码表，servlet连接哪个数据库，servlet使用哪个配置文件
public class ServletDemo2 extends HttpServlet {
	//private ServletConfig config;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*ServletConfig config=this.getServletConfig();
		//获得指定参数的值
		String value=config.getInitParameter("data1");//要获得参数值，需要将配置文件中的参数名传递进去
		System.out.println(value);*/
		//获得所有参数信息
		Enumeration e = this.getServletConfig().getInitParameterNames();
		while(e.hasMoreElements()){
			String name=(String)e.nextElement();
			String value=this.getServletConfig().getInitParameter(name);
			System.out.println(name+" "+value);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	//要想获得web.xml中为servlet写的配置信息，只需要覆盖init方法，服务器自动将这些配置信息封装成一个servletConfig对象中去，
	//并且在调用servlet对象init方法时会自动将这个servletConfig对象传递这个函数中，我们可以通过这个对象获得相关的配置信息
	/*@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		this.config=config;//获得config对象信息
	}*/
	
}
