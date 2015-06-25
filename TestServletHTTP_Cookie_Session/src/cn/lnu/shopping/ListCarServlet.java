package cn.lnu.shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//购物车显示用户购买所有商品
public class ListCarServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		//解决中文乱码问题
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		if(session==null){
			out.print("你没有购买任何商品:<br/>");
			return;
		}
		out.print("你购买了商品:<br/>");
		List<Book> list=(List)session.getAttribute("list");
		for(Book book:list){
			String url=response.encodeURL("/TestServletHTTP_Cookie_Session/servlet/BuyServlet?id="+book.getId());
			out.print("<a href='"+url+"' target='_blank'>"+book.getName()+"</a><br/>");
			//out.print("<a href='/TestServletHTTP_Cookie_Session/servlet/BuyServlet?id="+book.getId()+"' target='_blank'>"+book.getName()+"</a><br/>");
			//out.print(book.getName()+"<br/>");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
