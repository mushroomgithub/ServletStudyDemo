package cn.lnu.web.filter.example;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
//真正解决全站乱码
public class CharacterEncodingFilter2 implements Filter {
	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		
		//下面这三行只是解决了post方法请求乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//通过写包装类实现对request的增强，这就解决了get请求方式的中文乱码问题，这样，get，post请求方式的中文乱码问题就都解决了
		chain.doFilter(new MyRequest(request), response);//这种方式对于request.getParameter(“password”)，看这个参数password是请求方式带过来给服务器的，方法获得数据会得到乱码
	}

	/*包装设计模式五部曲，这里我们需要增强request对象
	 *1， 写一个类，实现与被增强对象相同的接口
	 *2，定义一个变量，记住被增强对象
	 *3，定义一个构造方法，接受被增强对象
	 *4，覆盖想增强的方法
	 *5，对于不想增强的方法，直接调用被增强对象（目标对象）的方法
	 */
	
	//使用sun公司默认的一个HttpServletRequestWrapper包装类，我们只需要覆盖需要覆盖的方法即可，这个默认的包装类内部是对HttpServletRequest的增强
	class MyRequest extends HttpServletRequestWrapper{
		private HttpServletRequest request;
		public MyRequest(HttpServletRequest request){
			super(request);
			this.request=request;
		}
		@Override
		public String getParameter(String name) {
			// TODO Auto-generated method stub
			
			String value=request.getParameter(name);
			if(!request.getMethod().equalsIgnoreCase("get")){
				return value;
			}
			
			if(value==null){
				return null;
			}
			
			try {
				return value=new String(value.getBytes("iso8859-1"),request.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
			
		}
		
		
	}
	
	/*class MyRequest implements HttpServletRequest{
		private HttpServletRequest request;
		public MyRequest(HttpServletRequest request){
			this.request=request;
		}
		//为了防止客户端采用get方法获取数据的乱码问题，我们需要增强这个方法
		public String getParameter(String name) {
			// TODO Auto-generated method stub
			String value=request.getParameter(name);
			
			//判断客户端的请求方式是不是get,如果不是get方法，就没必要进行增强
			if(!request.getMethod().equalsIgnoreCase("get")){
				return value;
			}
			//如果是get，则value可能是乱码格式
			if(value==null){
				return null;
			}
			
			try {//将可能乱码的value转码为请求时设置的编码方式返回
				return value=new String(value.getBytes("iso8859-1"),request.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		
		public String getAuthType() {
			// TODO Auto-generated method stub
			return this.request.getAuthType();
		}
		
		public String getContextPath() {
			// TODO Auto-generated method stub
			return this.request.getContextPath();
		}
		public Cookie[] getCookies() {
			// TODO Auto-generated method stub
			return this.request.getCookies();
		}
		public long getDateHeader(String name) {
			// TODO Auto-generated method stub
			return this.request.getDateHeader(name);
		}
		public String getHeader(String name) {
			// TODO Auto-generated method stub
			return this.request.getHeader(name);
		}
		public Enumeration getHeaderNames() {
			// TODO Auto-generated method stub
			return this.request.getHeaderNames();
		}
		public Enumeration getHeaders(String name) {
			// TODO Auto-generated method stub
			return this.request.getHeaders(name);
		}
		public int getIntHeader(String name) {
			// TODO Auto-generated method stub
			return this.request.getIntHeader(name);
		}
		public String getMethod() {
			// TODO Auto-generated method stub
			return this.request.getMethod();
		}
		public String getPathInfo() {
			// TODO Auto-generated method stub
			return this.request.getPathInfo();
		}
		public String getPathTranslated() {
			// TODO Auto-generated method stub
			return this.request.getPathTranslated();
		}
		public String getQueryString() {
			// TODO Auto-generated method stub
			return this.request.getQueryString();
		}
		public String getRemoteUser() {
			// TODO Auto-generated method stub
			return this.request.getRemoteUser();
		}
		public String getRequestURI() {
			// TODO Auto-generated method stub
			return this.request.getRequestURI();
		}
		public StringBuffer getRequestURL() {
			// TODO Auto-generated method stub
			return this.request.getRequestURL();
		}
		public String getRequestedSessionId() {
			// TODO Auto-generated method stub
			return this.request.getRequestedSessionId();
		}
		public String getServletPath() {
			// TODO Auto-generated method stub
			return this.request.getServletPath();
		}
		public HttpSession getSession() {
			// TODO Auto-generated method stub
			return this.request.getSession();
		}
		public HttpSession getSession(boolean create) {
			// TODO Auto-generated method stub
			return this.request.getSession(create);
		}
		public Principal getUserPrincipal() {
			// TODO Auto-generated method stub
			return this.request.getUserPrincipal();
		}
		public boolean isRequestedSessionIdFromCookie() {
			// TODO Auto-generated method stub
			return this.request.isRequestedSessionIdFromCookie();
		}
		public boolean isRequestedSessionIdFromURL() {
			// TODO Auto-generated method stub
			return this.request.isRequestedSessionIdFromURL();
		}
		public boolean isRequestedSessionIdFromUrl() {
			// TODO Auto-generated method stub
			return this.request.isRequestedSessionIdFromUrl();
		}
		public boolean isRequestedSessionIdValid() {
			// TODO Auto-generated method stub
			return this.request.isRequestedSessionIdValid();
		}
		public boolean isUserInRole(String role) {
			// TODO Auto-generated method stub
			return this.request.isUserInRole(role);
		}
		public Object getAttribute(String name) {
			// TODO Auto-generated method stub
			return this.getAttribute(name);
		}
		public Enumeration getAttributeNames() {
			// TODO Auto-generated method stub
			return this.request.getAttributeNames();
		}
		public String getCharacterEncoding() {
			// TODO Auto-generated method stub
			return this.request.getCharacterEncoding();
		}
		public int getContentLength() {
			// TODO Auto-generated method stub
			return this.request.getContentLength();
		}
		public String getContentType() {
			// TODO Auto-generated method stub
			return this.request.getContentType();
		}
		public ServletInputStream getInputStream() throws IOException {
			// TODO Auto-generated method stub
			return this.request.getInputStream();
		}
		public String getLocalAddr() {
			// TODO Auto-generated method stub
			return this.request.getLocalAddr();
		}
		public String getLocalName() {
			// TODO Auto-generated method stub
			return this.request.getLocalName();
		}
		public int getLocalPort() {
			// TODO Auto-generated method stub
			return this.request.getLocalPort();
		}
		public Locale getLocale() {
			// TODO Auto-generated method stub
			return this.request.getLocale();
		}
		public Enumeration getLocales() {
			// TODO Auto-generated method stub
			return this.request.getLocales();
		}
		
		public Map getParameterMap() {
			// TODO Auto-generated method stub
			return this.request.getParameterMap();
		}
		public Enumeration getParameterNames() {
			// TODO Auto-generated method stub
			return this.request.getParameterNames();
		}
		public String[] getParameterValues(String name) {
			// TODO Auto-generated method stub
			return this.request.getParameterValues(name);
		}
		public String getProtocol() {
			// TODO Auto-generated method stub
			return this.request.getProtocol();
		}
		public BufferedReader getReader() throws IOException {
			// TODO Auto-generated method stub
			return this.request.getReader();
		}
		public String getRealPath(String path) {
			// TODO Auto-generated method stub
			return this.request.getRealPath(path);
		}
		public String getRemoteAddr() {
			// TODO Auto-generated method stub
			return this.request.getRemoteAddr();
		}
		public String getRemoteHost() {
			// TODO Auto-generated method stub
			return this.getRemoteHost();
		}
		public int getRemotePort() {
			// TODO Auto-generated method stub
			return this.getRemotePort();
		}
		public RequestDispatcher getRequestDispatcher(String path) {
			// TODO Auto-generated method stub
			return this.request.getRequestDispatcher(path);
		}
		public String getScheme() {
			// TODO Auto-generated method stub
			return this.request.getScheme();
		}
		public String getServerName() {
			// TODO Auto-generated method stub
			return this.request.getServerName();
		}
		public int getServerPort() {
			// TODO Auto-generated method stub
			return this.request.getServerPort();
		}
		public boolean isSecure() {
			// TODO Auto-generated method stub
			return this.request.isSecure();
		}
		public void removeAttribute(String name) {
			// TODO Auto-generated method stub
			
		}
		public void setAttribute(String name, Object o) {
			// TODO Auto-generated method stub
			
		}
		public void setCharacterEncoding(String env)
				throws UnsupportedEncodingException {
			// TODO Auto-generated method stub
			
		}
		
	}*/
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
