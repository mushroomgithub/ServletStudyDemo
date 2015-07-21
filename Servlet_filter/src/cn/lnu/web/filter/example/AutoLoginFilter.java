package cn.lnu.web.filter.example;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

import cn.lnu.domain.User;
import cn.lnu.service.BusinessService;
//过滤用户的所有请求，检测用户请求是否带cookie过来，如果带来了，就根据cookie信息给用户自动登录，如果没有带cookie直接放行
public class AutoLoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		
		//1，检测用户有没有已经登录(检查session中有没有存登录标记)，没登录才自动登录
		User user=(User) request.getSession().getAttribute("user");
		if(user!=null){//如果已经登录，直接放行
			chain.doFilter(request, response);
			return;
		}
		//2,没登陆，再执行自动登录逻辑
		
		//看用户有没有带自动登录的cookie
		Cookie autoLoginCookie=null;//用户保存找到的自动登录的cookie
		Cookie cookies[]=request.getCookies();
		for(int i=0;cookies!=null&&i<cookies.length;i++){
			if(cookies[i].getName().equals("autologin")){
				autoLoginCookie=cookies[i];
				break;
			}
		}
		
		if(autoLoginCookie==null){//表示是用户第一次登录,说明没带自动登录cookie，直接放行
			chain.doFilter(request, response);
			return;
		}
		
		//用户带了自动登录cookie，则先检查cookie有效期的有效期
		String values[]=autoLoginCookie.getValue().split("\\:");
		if(values.length!=3){
			chain.doFilter(request, response);
			return;
		}
		long expirestime=Long.parseLong(values[1]);
		if(System.currentTimeMillis()>expirestime){
			chain.doFilter(request, response);
			return;
		}
		//,代表cookie的有效期是有效的，再检查cookie密码的有效性
		String username=values[0];
		String client_md5=values[2];
		
		BusinessService service=new BusinessService();
		user=service.findUser(username);
		if(user==null){
			chain.doFilter(request, response);
			return;
		}
		
		String password=user.getPassword();
		//将用户名，密码,cookie有效期组合成md5(password:expirestime:username)格式
		String server_md5=md5(username,password,expirestime);
		//比如服务器端和客户端的带密码信息的md5码
		if(!server_md5.equalsIgnoreCase(client_md5)){//如果客户端与服务器端带密码信息的md5码不匹配就放行
			chain.doFilter(request, response);
			return;
		}
		//最后执行登录，想session中存放一个登录标记，然后放行
		request.getSession().setAttribute("user", user);
		chain.doFilter(request, response);
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
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
