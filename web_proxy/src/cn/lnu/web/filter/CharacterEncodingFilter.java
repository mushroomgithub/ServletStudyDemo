package cn.lnu.web.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//在拦截器中，使用动态代理技术解决全站乱码问题
public class CharacterEncodingFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		final HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		
		request.setCharacterEncoding("UTF-8");//这种方法只能解决post方式提交过来的请求数据的中文乱码问题，不能解决get方式的中文乱码问题，即get方式提交的数据使用request.getParameter(name)函数获得数据还是会中文乱码，
		//即request这个对象的getParameter(name)函数的功能不够强,可以使用代理对request对象的getParameter函数进行增强，并且只有是get方式才增强这个函数功能，
		
		//将request的代理放行，实现解决全站中文乱码问题，外面的servlet拿到的都是requestProxy,调用的函数是通过代理对象调用的。比如说requestProxy.getCookie,requestProxy.getParameter
		chain.doFilter((ServletRequest) Proxy.newProxyInstance(CharacterEncodingFilter.class.getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler(){

			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				if(!method.getName().equals("getParameter")){//如果外面servlet调用的不是getParameter函数，则不进行增强，还是使用原来的request对象处理
					return method.invoke(request, args);//	InvocationHandler是内部实现类，所以request要设置为final类型
				}
				
				//如果外界调用的是getParameter方法，判断是不是get方式提交过来的数据
				if(!request.getMethod().equalsIgnoreCase("get")){//如果不是get方式提交的数据，不做增强，还是使用原来的request对象处理，注意getMethod方法可以可以获得前台提交数据的方式是get还是post
					return method.invoke(request, args);
				}
				
				//外界servlet调用的是getParameter函数，并且是处理的一get方式提交过来的数据，此时需要对getParameter函数进行增强
				String value=(String) method.invoke(request, args);//首先使用原始的getParameter的函数获得前台提交过来的可能有乱码的数据值
				//然后将这个值的乱码转正常
				if(value==null){
					return null;
				}
				
				return new String(value.getBytes("iso8859-1"),"UTF-8");
			}
			
		}), response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
