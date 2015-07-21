package cn.lnu.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDemo1 implements Filter {

	/*拦截器filter在开发中常见应用
	 * 1，filter在目标资源在执行之前，进行一些权限检查，检查用户有无权限，如有权限则放行，如没有则拒绝访问
	 * 2，filter可以在放行资源执行之前，对request和response进行预处理，从而实现一些全局性德设置(如解决网站中文乱码问题)
	 * 3，filter在放行之后可以捕获到目标资源的输出（存在response中），从而对输出作出类似于压缩这样的设置
	 * */
	private FilterConfig config;//记住初始化参数信息
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//在过滤器中解决了中文乱码问题，如果该过滤器是拦截网站所有资源，那么这么设置的话就解决了全网站的中文乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//使用初始化参数
		Enumeration e=config.getInitParameterNames();
		while(e.hasMoreElements()){
			String name=(String) e.nextElement();
			System.out.println(name+" = "+config.getInitParameter(name));
		}
		
		
		System.out.println("FilterDemo1之前");
		
		//拦截之后，如果要放行这个资源
		chain.doFilter(request, response);
		
		//压缩处理
		
		System.out.println("FilterDemo1之后");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Filter1被创建！");
		this.config=filterConfig;
		Enumeration e=filterConfig.getInitParameterNames();
		while(e.hasMoreElements()){
			String name=(String) e.nextElement();
			System.out.println(name+" = "+filterConfig.getInitParameter(name));
		}
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Filter1被销毁！");
	}
}
