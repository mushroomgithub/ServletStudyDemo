package cn.lnu;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//获得web应用的初始化参数
public class ServletDemo6 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//根据web应用的参数名获得web应用的参数值
		/*String value=this.getServletContext().getInitParameter("contextparm");
		System.out.println(value);*/
		
		//获得所有web应用初始化参数
		Enumeration e=this.getServletContext().getInitParameterNames();
		while(e.hasMoreElements()){
			String name=(String)e.nextElement();
			String value=this.getServletContext().getInitParameter(name);
			System.out.println(name+"  "+value);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
