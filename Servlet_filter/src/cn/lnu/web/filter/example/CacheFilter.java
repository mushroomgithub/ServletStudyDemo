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
//控制浏览器缓存的过滤器
public class CacheFilter implements Filter {
	
	private FilterConfig config;
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		
		String css=config.getInitParameter("css");
		String js=config.getInitParameter("js");
		//1.获取到用户想访问的资源
		String uri=request.getRequestURI();//用户想访问的资源可以在request域中获得
		//2，获取该资源在配置文件设置的缓存时间，配置文件中设置的值都是分钟为单位的
		int expires=0;
		if(uri.endsWith(".bmp")){
			expires=Integer.parseInt(config.getInitParameter("bmp"));
		}else if(uri.endsWith(".css")){
			expires=Integer.parseInt(config.getInitParameter("css"));
		}else{
			expires=Integer.parseInt(config.getInitParameter("js"));
		}
		
		//3,使用response设置缓存头，第二个参数值是long型的毫秒值,这个时间值需要的是1970年开始的时间，所以需要加一个当前的时间值
		response.setDateHeader("Expires", System.currentTimeMillis()+expires*60*1000);
		//4，放行,这么一来目标资源拿到的都是设置好response头的response，使用这个respose输出时的数据都会按指定配置文件时间值缓存
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		//记住web.xml中为该过滤器配置的参数
		this.config=filterConfig;
	}

}
