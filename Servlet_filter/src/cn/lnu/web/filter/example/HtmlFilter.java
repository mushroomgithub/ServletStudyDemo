package cn.lnu.web.filter.example;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class HtmlFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		
		//将增强转义功能之后的request放行
		chain.doFilter(new MyRequest(request), response);
	}
	//实现自己的request增强转义功能
	class MyRequest extends HttpServletRequestWrapper{
		private HttpServletRequest request;
		public MyRequest(HttpServletRequest request){
			super(request);
			this.request=request;
		}
		//覆盖这个方法，实现从name属性获得的值，进行转义
		@Override
		public String getParameter(String name) {
			// TODO Auto-generated method stub
			String value=request.getParameter(name);
			if(value==null){
				return null;
			}
			//将数据转义之后返回
			value=filter(value);
			return value;
		}
		
		
	}
	//tomcat服务器中提供了对字符串转义的方法，路径是C:\apache-tomcat-6.0.20\webapps\examples\WEB-INF\classes\UTIL\HTMLFilter.java
	 public String filter(String message) {

	        if (message == null)
	            return (null);

	        char content[] = new char[message.length()];
	        message.getChars(0, message.length(), content, 0);
	        StringBuffer result = new StringBuffer(content.length + 50);
	        for (int i = 0; i < content.length; i++) {
	            switch (content[i]) {
	            case '<':
	                result.append("&lt;");
	                break;
	            case '>':
	                result.append("&gt;");
	                break;
	            case '&':
	                result.append("&amp;");
	                break;
	            case '"':
	                result.append("&quot;");
	                break;
	            default:
	                result.append(content[i]);
	            }
	        }
	        return (result.toString());

	    }
	 
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
