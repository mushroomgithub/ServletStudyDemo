package cn.lnu.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDemo2 implements Filter {

	

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
			System.out.println("FilterDemo2之前");
			
			chain.doFilter(request, response);
			
			System.out.println("FilterDemo2之后");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Filter2被创建！");
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Filter2被销毁！");
	}

}
