package cn.lnu.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//请求转发，以及使用request域对象吧数据带给转发资源
//请求转发特点，客户端只发出一次请求，而服务器端有多个资源调用，客户机浏览器地址栏没有变化
public class RequestDemo5 extends HttpServlet {

	/*
	 * MVC (model(可以看做javaBean) view(可以看做jsp) controller（可以看做Servlet）)
	 * 也就是servlet这个控制器收到请求产生数据，产生数据之后使用javaBean这个Model来封装，
	 * 然而servlet控制器并不适合做输出，它是把javaBean存到request域中使用forward技术带给jsp这个view，由jsp这个view
	 * 取出数据做输出显示
	 * */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data="mushroom";
		//将将请求数据带给转发资源，但是实际开发中一般不采用此方法
		//this.getServletContext().setAttribute("data", data);
		
		//一般使用request域带数据给jsp显示，
		request.setAttribute("data", data);
		//request对象也可实现转发
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
