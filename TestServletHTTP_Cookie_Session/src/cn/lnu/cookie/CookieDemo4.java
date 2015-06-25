package cn.lnu.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//显示商品详细信息的servlet
public class CookieDemo4 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		//1 根据用户点击超链接带过来id，显示相应商品的详细信息
		String id=request.getParameter("id");
		Book book=(Book)Db.getAll().get(id);
		out.write(book.getId()+"<br/>");
		out.write(book.getName()+"<br/>");
		out.write(book.getAuthor()+"<br/>");
		out.write(book.getDesription()+"<br/>");
		
		//2 构建cookie，回写给浏览器
		String cookieValue=buildCookie(id,request);
		Cookie cookie=new Cookie("bookHistory",cookieValue);
		cookie.setMaxAge(1*30*24*3600);
		cookie.setPath("/TestServletHTTP_Cookie_Session");
		response.addCookie(cookie);
	}

	//如何构建cookie值
	private String buildCookie(String id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		//假设cookie最多保存三个
		//第一次时，用户没有带任何cookie，此时bookHistory=null  则返回id值
		
		//用户带cookie来时，假设bookHistory 为2 ，5,1,  1 返回1,2,5
		
		//用户带cookie来时，假设bookHistory 为2 ，5,1,  不包含当前值3 返回3,2,5
		
		//用户带cookie来时，假设bookHistory 为2 ，5    1	返回1,2,5
		String bookHistory=null;
		Cookie cookies[]=request.getCookies();
		//如果用户带cookie过来，则将bookHistory设置为该cookie值
		for(int i=0;cookies!=null&&i<cookies.length;i++){
			if(cookies[i].getName().equals("bookHistory")){
				bookHistory=cookies[i].getValue();
			}
		}
		//表示用户没有带cookie过来
		if(bookHistory==null){
			return id;
		}
		
		LinkedList<String> list=new LinkedList(Arrays.asList(bookHistory.split("\\,")));
		
		if(list.contains(id)){//如果集合类中包含id 如用户带cookie来时，假设bookHistory 为2 ，5,1,  1 返回1,2,5
			list.remove(id);
			list.addFirst(id);
		}else if(list.size()>=3){//如果不包含id，并且容量大于等于3 如用户带cookie来时，假设bookHistory 为2 ，5,1,  不包含当前值3 返回3,2,5
			list.removeLast();
			list.addFirst(id);
		}else {//如果不包含id，但是容量小于3  如用户带cookie来时，假设bookHistory 为2 ，5    1	返回1,2,5
			list.addFirst(id);
			
		}
		
		//最后将bookHistory转换为1,2,3由逗号分隔的字符串返回
		StringBuffer sb=new StringBuffer();
		for(String bid : list){
			sb.append(bid+",");
		}
		bookHistory=sb.deleteCharAt(sb.length()-1).toString();
		return bookHistory;
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
