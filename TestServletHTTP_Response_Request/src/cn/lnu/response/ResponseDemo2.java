package cn.lnu.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//通过response的PrintWriter流(只能写字符串和字符)向客户端输出字符流数据
public class ResponseDemo2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//test1(response);
		test2(response);
	}

	private void test1(HttpServletResponse response) throws IOException {
		//设置Response对象使用的码表，以控制response对象以什么码表向浏览器写出数据
		response.setCharacterEncoding("UTF-8");
		//既然你设置了Response的码表是UTF-8,那么要想客户端浏览器正常输出显示，还要通过设置响应头设置浏览器采用的码表也为UTF-8来显示服务器发送的数据,即要保持一致
		response.setHeader("content-type", "text/html;charset=UTF-8");
		String data="中国";
		PrintWriter out=response.getWriter();
		out.write(data);//直接这么写浏览器访问时，会返回两个??，因为它是写给resonse的，而response一般采用的是ISO8859码表，因此需要设置response的码表
	}

	private void test2(HttpServletResponse response) throws IOException {
		//设置Response对象使用的码表，以控制response对象以什么码表向浏览器写出数据
		//response.setCharacterEncoding("UTF-8");
		//要指定浏览器以什么码表打开服务器发送的数据也可以使用下面这种方法设置浏览器使用什么码表打开,而且更强大的是它还会设置response采用的码表也为UTF-8
		//response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String data="中国";
		PrintWriter out=response.getWriter();
		out.write(data);//直接这么写浏览器访问时，会返回两个??，因为它是写给resonse的，而response一般采用的是ISO8859码表，因此需要设置response的码表
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
