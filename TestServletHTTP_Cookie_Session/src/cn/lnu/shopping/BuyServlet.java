package cn.lnu.shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//购买
public class BuyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		//通过i图书Id获得对应图书
		Book book=(Book)Db.getAll().get(id);
		//将用户买的书的信息，加到session，当用户结账时，获得session就可以获得这本书
		HttpSession session=request.getSession(false);
		//手工以cookie形式发sessionid。以解决关闭浏览器之后，上次购买的东西还在
		//.....
		//为了解决购买的多本书，需要将用户购买的书组织到集合中然后再加到session中，这样用户在结账时，只需从session中获得集合就能得到用户购买的图书信息
		//从session中得到用户用于保存所有书的集合(购物车)
		List list=(List)session.getAttribute("list");
		if(list==null){
			list=new ArrayList();
			session.setAttribute("list", list);//将这个集合加到session中
		}
		list.add(book);
		
		//跳转到购物车页面,注意此处不能采用转发的方式跳转页面，如果使用转发的方式跳转到购物车页面，那么用户在购物车页面只要一刷新，
		//就会再次将上次买的书再买一遍，这当然不是我们想看到的，所以要采用重定向的方式跳转页面
		//request.getRequestDispatcher("/servlet/ListCarServlet").forward(request, response);
		//所有url都重写，这样会在用户禁止cookie时，页面跳转时会带上sessionid
		String context=request.getContextPath();///TestServletHTTP_Cookie_Session得到web应用目录
		String url=response.encodeRedirectURL("/servlet/ListCarServlet");
		response.sendRedirect(context+url);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
