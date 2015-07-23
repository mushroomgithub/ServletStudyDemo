package cn.lnu.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//在拦截器中使用动态代理技术，解决将返回浏览器的数据进行全站压缩。主要是增强response对象的getOutputStream和getWriter函数
public class GzipFilter implements Filter {
	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		final HttpServletResponse response=(HttpServletResponse) resp;
		
		//使用动态代理技术拦截对response对象方法，增强response的getOutputStream和getWriter函数，返回自己的流，用户通过这两个方法写数据是写到缓冲中区，然后从缓冲中取出数据进行压缩，返回给浏览器，从而实现全站压缩，通过chain放行的是response的代理
		ResponseProxy proxy=new ResponseProxy(response);//先创建一个response代理对象，用它来创建代理
		chain.doFilter(request, proxy.createProxy());//使用responseProxy.get...方法就是使用创建的response代理的get方法
		
		byte[] out=proxy.getBuffer();//放行之后，目标资源数据都进了缓冲流，从缓冲流中取出数据，进行压缩
		
		//然后取出缓冲流中的数据进行压缩后输出浏览器
		System.out.println(new String(out,"UTF-8"));
		System.out.println("压缩之前："+out.length);
		byte gzipout[]=gzip(out);
		System.out.println("压缩之后："+gzipout.length);
		response.setHeader("content-encoding", "gzip");
		response.setHeader("content-length", gzipout.length+"");
		response.getOutputStream().write(gzipout);
	}
	
	public byte[] gzip(byte b[]) throws IOException{//接受一个byte数组，返回一个gzip压缩之后的byte数组
		ByteArrayOutputStream bout=new ByteArrayOutputStream();
		GZIPOutputStream gout=new GZIPOutputStream(bout);
		gout.write(b);//将数组写到bout中去
		gout.close();
		
		return bout.toByteArray();
	}
	
	class ResponseProxy{
		private ByteArrayOutputStream bout=new ByteArrayOutputStream();//在这个代理类中维护一个缓冲，当用户在sevlet中掉getOutputStream或者getWriter方法时，先将数据写到bout缓冲中
		private PrintWriter pw=null;
		public byte[] getBuffer(){
			if(pw!=null){
				pw.close();//确保如果如果数据量太小，数据没有进入缓冲bout中，还在pw中
			}
			return bout.toByteArray();
		}
		
		
		private HttpServletResponse response;//用于记住产生谁的代理
		public ResponseProxy(HttpServletResponse response){//通过构造函数接收要创建代理的对象
			this.response=response;
		}
		public HttpServletResponse createProxy(){
			return (HttpServletResponse) Proxy.newProxyInstance(GzipFilter.class.getClassLoader(), response.getClass().getInterfaces(), new InvocationHandler(){

				public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
					
					if(!method.getName().equals("getOutputStream") && !method.getName().equals("getWriter")){//如果外面servlet调用的不是这两个方法，就不对response进行增强，调用原来的response对象处理请求
						return method.invoke(response, args);
					}
					
					if(method.getName().equals("getOutputStream")){//servlet调用getOutputStream想要返回一个ServletOutputStream流，这里需要增强，返回自己的outputStream流，将数据写到缓冲中去
						return new ServletOutputStream(){
							@Override
							public void write(int b) throws IOException {
								bout.write(b);//控制数据写到底层bout缓冲流中去	
							}	
						};
					}
					
					if(method.getName().equals("getWriter")){//外面serlet调用getWriter想要返回一个PrintWriter流，这里需要增强，返回自己的Writer流，将数据写到缓冲中去
						pw=new PrintWriter(new OutputStreamWriter(bout,"UTF-8"));//代理对象拦截对真实response对象的访问，查看是否调用的是这个方法，如果是，返回一个自己的一个流，这个流是控制数据写到底层缓冲流bout中的流,为了防止中文乱码问题，需要使用一个转化流OutputStreamWriter，指定采用UTF-8码表
						return pw;//返回将数据写到缓冲中的流，便于servlet中可以从缓冲中取出数据压缩之后再写回浏览器，从而实现全站压缩
					}
					
					return null;
				}
				
			});
		}
		
	}
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
