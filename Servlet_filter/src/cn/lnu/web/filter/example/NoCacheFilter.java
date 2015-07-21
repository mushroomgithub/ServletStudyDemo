package cn.lnu.web.filter.example;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//控制浏览器不要缓存数据
public class NoCacheFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		
		//设置respons的头是控制浏览器不要缓存数据
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		chain.doFilter(request, response);//设置头之后放行给jsp,jsp拿到的resonse都是不要缓存的response，然后再用这个response输出数据的时候，就不会再浏览器缓存数据
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
