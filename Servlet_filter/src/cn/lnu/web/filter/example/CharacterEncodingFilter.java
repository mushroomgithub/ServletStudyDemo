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
//解决全站乱码
public class CharacterEncodingFilter implements Filter {
	
	private FilterConfig config;
	private String defaultCharset = "UTF-8";

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// 获取要设置的字符集
		String charset=config.getInitParameter("charset");
		if(charset==null){//为避免忘记设置字符集，设置默认字符集
			charset=defaultCharset;
		}
		
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset="+charset);
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config=filterConfig;//服务器启动时获得在配置文件中对过滤器的配置信息，并用config对象记住改配置对象信息
		System.out.println("被创建");
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("被销毁");
	}

}
