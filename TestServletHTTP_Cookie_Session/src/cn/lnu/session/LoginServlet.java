package cn.lnu.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//使用session处理登陆表单的状态
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		//得到数据提交过来的用户名和密码之后，首先检查数据库有没有对应的用户
		List<User> list=Db.getAll();
		
		for(User user:list){
			if(user.getUsername().equals(username)&& user.getPasswd().equals(password)){
				request.getSession().setAttribute("user", user);//登陆成功，向session中存入一个登陆成功标记
				response.sendRedirect("/TestServletHTTP_Cookie_Session/index.jsp");
				return;
			}
		}
		
		//如果数据库中不存在对应用户名和密码的登陆则输出”你输入的用户名或者密码不正确！“
		//解决中文乱码问题
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		out.print("你输入的用户名或者密码不正确！<br/>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request,response);
	}

}

class Db{
	public static List list=new ArrayList();
	static {
		list.add(new User("aaa","123"));
		list.add(new User("bbb","234"));
		list.add(new User("ccc","2543"));
	}
	
	public static List getAll(){
		return list;
	}
}
class User{
	private String username;
	private String passwd;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String passwd) {
		super();
		this.username = username;
		this.passwd = passwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}