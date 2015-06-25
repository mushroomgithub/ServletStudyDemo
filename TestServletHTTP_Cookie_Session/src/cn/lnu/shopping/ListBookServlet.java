package cn.lnu.shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//代表网站首页，列出所有书
public class ListBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解决中文乱码问题
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		request.getSession();
		
		out.print("本网站有如下商品:<br/>");
		Map<String,Book> map=Db.getAll();
		for(Map.Entry<String, Book> entry:map.entrySet()){
			Book book=entry.getValue();
			//所有url都重写，这样会在用户禁止cookie时，页面跳转时会带上sessionid
			String url=response.encodeURL("/TestServletHTTP_Cookie_Session/servlet/BuyServlet?id="+book.getId());
			out.print(book.getName()+"<a href='"+url+"' target='_blank'>  购买</a><br/>");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

//使用Db类模拟数据库
class Db{
	//使用LinkedHashMap是为了保证存入的数据顺序不发生变化
	private static Map<String,Book> map=new LinkedHashMap();
	static{
		map.put("1", new Book("1","javaWeb开发","mushroom","javaWebb必备宝典"));
		map.put("2", new Book("2","jdbc开发","mushroom","javaWebb必备宝典"));
		map.put("3", new Book("3","spring3开发","mushroom","javaWebb必备宝典"));
		map.put("4", new Book("4","structs2开发","mushroom","javaWebb必备宝典"));
		map.put("5", new Book("5","herbnate开发","mushroom","javaWebb必备宝典"));
	}
	
	public  static Map getAll(){
		return map;
	}
}

class Book implements Serializable{
	private String id;
	private String name;
	private String author;
	private String desription;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String id, String name, String author, String desription) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.desription = desription;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDesription() {
		return desription;
	}
	public void setDesription(String desription) {
		this.desription = desription;
	}
}