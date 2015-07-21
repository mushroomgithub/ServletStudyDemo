package cn.lnu.web.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;
import cn.lnu.domain.User;
import cn.lnu.service.BusinessService;
//处理登录界面的servlet
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		//拿到一个用户请求之后，创建一个service真正去处理请求
		BusinessService service=new BusinessService();
		User user=service.login(username, password);//如果数据库中存在这个用户名和密码则返回这个用户
		if(user==null){//登录失败
			request.setAttribute("message", "用户名或密码不正确！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		//如果存在这个用户，登录成功向session中存放一个登录标记，若想实现用户自动登录，在登录成功之后还要给客户端发cookie，
		//保存用户的登录的信息，下次用户再次登录时，会带着登录信息的cookie过来，如果cookie不为空，带着cookie来给他自动登录
		request.getSession().setAttribute("user", user);
		//获得前台页面设置的有效期
		int expirestime=Integer.parseInt(request.getParameter("time"));
		//给客户机发送自动登录的cookie,cokie的名为autologin
		//autologin=username:expirestime:md5(password:expirestime:username),这是cookie的值，md5防止密码被暴力破解
		Cookie cookie=makeCookie(user,expirestime);
		response.addCookie(cookie);//将cookie返回给客户机浏览器，这样浏览器下次访问站点下的资源时就会带着这个cookie过来
		//登录成功之后，重定向到首页，告诉用户登录成功
		response.sendRedirect("/Servlet_filter/index.jsp");
	}

	//自定义函数，创建一个cookie
	public Cookie makeCookie(User user,int expirestime){
		long currenttime=System.currentTimeMillis();
		//构建出cookie的值
		String cookieValue=user.getUsername()+":"+(currenttime+expirestime*1000)+":"+md5(user.getUsername(),user.getPassword(),(currenttime+expirestime*1000));
		Cookie cookie=new Cookie("autologin",cookieValue);
		cookie.setMaxAge(expirestime);//设置cookie最大有效期
		cookie.setPath("/Servlet_filter");//浏览器带cookie来，设置浏览器访问/Servlet_filter这个站点下的所有资源时都带着个cookie过来
		
		return cookie;
	}
	//将用户名密码和cookie无效期联合md5下，防止密码被人暴力破解
	private String md5(String username,String password,long expirestime){
		String value=password+":"+expirestime+":"+username;
		//使用MessageDigest将一个字符串md5
		try {
			MessageDigest md=MessageDigest.getInstance("md5");// 返回实现指定摘要算法的 MessageDigest 对象
			byte md5[]=md.digest(value.getBytes());
			//使用BASE64对md5编码
			BASE64Encoder encoder=new BASE64Encoder();
			return encoder.encode(md5);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
