package cn.lnu;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//测试servletContext实现请求转发，所谓转发是指浏览器在访问这个servletDemo7web资源时，该资源没有浏览器请求的资源，但是它会将将请求转发有资源的地址，这里是jsp页面
public class ServletDemo7 extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data="aaa";
		//把数据带给1.jsp，这里采用将数据保存到servletContext域，但是一般我们都不这么做，因为这个数据会被所有web资源共享，因为存在线程安全问题
		//所以实际开发中将数据带给jsp不是通过context域，而是通过request域，这里暂时先用context域带数据给jsp
		this.getServletContext().setAttribute("data", data);
		
		
		//首先获得servlet转发对象
		RequestDispatcher rd=this.getServletContext().getRequestDispatcher("/1.jsp");
		//调用转发对象的forward方法将数据转发给1.jsp页面
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
