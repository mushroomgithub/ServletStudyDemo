package cn.lnu.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo3 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String data="bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
		String data="中国，念经";
		//将数据发给浏览器
		//response.getOutputStream().write(data.getBytes("UTF-8"));
		response.getWriter().write(data);//PrintWriter.Write("中国")；------》ByteArrayOutputStream时会将数据写到底层缓冲字节流，这是会默认查GB2312码表,就会出现乱码了
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
