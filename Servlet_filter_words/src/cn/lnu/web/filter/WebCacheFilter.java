package cn.lnu.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
import javax.servlet.http.HttpServletResponseWrapper;

import cn.lnu.web.filter.GzipFilter.MyServletOutputStream;

public class WebCacheFilter implements Filter {
	private Map<String,byte[]> map=new HashMap();//记住缓存数据的map集合
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		//1，得到用户想请求访问的资源（uri），这里是uri=/Servlet_filter_words/servlet/ServletDemo4
		String uri=request.getRequestURI();
		System.out.println("uri="+uri);
		//2,看map集合中是否保存了该资源的数据
		byte b[]=map.get(uri);
		//3，如果保存了(表示不是第一次访问，直接拿到缓存)，则直接取出数据打给浏览器
		if(b!=null){
			response.getOutputStream().write(b);
			return;
		}
		//4,如果没有保存数据(说明是第一次访问)，则放行让目标资源执行，这时还需要写一个response的包装类，捕获目标资源的输出
		MyResponse myresponse=new MyResponse(response);
		chain.doFilter(request, myresponse);
		byte data[]=myresponse.getBuffer();
		//5,以资源uri为关键字，把资源的数据保存到map集合中，以备下次访问
		map.put(uri, data);
		//6，输出数据给浏览器
		response.getOutputStream().write(data);
	}
	
	class MyResponse extends HttpServletResponseWrapper{
		private ByteArrayOutputStream bout=new ByteArrayOutputStream();//维护一个缓冲流，将字节数组保存到这个流中
		private PrintWriter pw;
		private HttpServletResponse response;
		public MyResponse(HttpServletResponse response) {
			super(response);
			this.response=response;
		}
		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			
			
			return new MyServletOutputStream(bout);  //myresponse.getOutputStream.write("afhsaa");,数据最终进到了bout中
		}
		@Override
		public PrintWriter getWriter() throws IOException {
			//pw=new PrintWriter(bout); //PrintWriter.Write("中国")；内部会去查gb2312码表,改成下面方式,reponse采用的是什么码表就设置为什么码表
			pw=new PrintWriter(new OutputStreamWriter(bout,response.getCharacterEncoding()));
			return pw;//myresponse.getWriter().write("hdalhfahfa")；//这种方法如果写入数据量太小，不会将数据写到底层缓冲流中，而是直接写到printWriter的缓存中，getBuffer()时从底层流中就取不到数据
		}	
		
		//自定义函数，得到bout缓冲区中的数据
		public byte[] getBuffer(){
			if(pw!=null){
				pw.close();//确保printwriter方式由于数据量太小不会写到底层流，手动close之后将数据写到底层流bout中去
			}
			return bout.toByteArray();
		}
	}
	//定义一个流类，用作返回流
	class MyServletOutputStream extends ServletOutputStream{
		private ByteArrayOutputStream bout;
		public MyServletOutputStream(ByteArrayOutputStream bout){//定义一个缓冲流用户将数据写到这个流中缓存起来
			this.bout=bout;
		}
		
		@Override
		public void write(int b) throws IOException {
			// TODO Auto-generated method stub
			bout.write(b);
		}
		
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}
}
