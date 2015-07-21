package cn.lnu.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//压缩输出
public class ServletDemo2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		ByteArrayOutputStream bout=new ByteArrayOutputStream();//这是一个缓冲流
		//实验把数据压缩之后返回给浏览器
		GZIPOutputStream gout=new GZIPOutputStream(bout);//参数是指定压缩之后写到什么地方去,一般给一个带缓冲的目标，这里是写到缓冲中去
		gout.write(data.getBytes());//底层就是将数据压缩之后写到一个ByteArrayOutputStream缓冲流中去
		gout.close();//确保数据全部写到缓冲流中，即便GZIPOutputStream这个帮助流没写满缓存也写到缓冲流中去
		
		byte gzip[]=bout.toByteArray();//拿到压缩后的数据数组，然后打给浏览器
		
		//压缩数据打给浏览器之前先通知浏览器，服务器端使用的压缩编码格式和压缩数据长度，通过发头的方式通知浏览器
		response.setHeader("content-encoding", "gzip");//告诉浏览器服务器端采用的压缩格式
		response.setHeader("content-length", gzip.length+"");//告诉浏览器返回的压缩之后的数据的长度
		//将数据发给浏览器
		response.getOutputStream().write(gzip);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
