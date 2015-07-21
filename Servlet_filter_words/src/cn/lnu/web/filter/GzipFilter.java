package cn.lnu.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
//实现全站压缩的过滤器,这就需要对response进行增强之后再放行
public class GzipFilter implements Filter {

	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		MyResponse myresponse=new MyResponse(response);
		
		chain.doFilter(request, myresponse);//response.getWriter，response.getOutputStream这两个方法需要增强，控制将数据都写到myresponse中的一个缓冲流中，返回一个自己的流
		//然后取出缓冲流中的数据进行压缩后输出浏览器
		byte out[]=myresponse.getBuffer();
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
