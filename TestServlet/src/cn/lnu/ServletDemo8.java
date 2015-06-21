package cn.lnu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




//通过servletContext读取资源文件
public class ServletDemo8 extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//测试配置文件在不同位置如何获取配置文件流
		//test1();
		//test2();
		//test3();
		test4();
	}

	//这个函数是测试db.properties在src目录下时如何获得文件流
	public void test1() throws IOException {
		//获得资源文件流,
		InputStream in=this.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");
		//读取Properties配置文件的模板方式，java里有一个Properties对象
		Properties pros=new Properties();//内部是map集合方式保存
		pros.load(in);//这个就是将输入流中的数据整合到pros对象中去了
		
		String user=pros.getProperty("username");
		String passwd=pros.getProperty("password");
		String url=pros.getProperty("url");
		System.out.print(user+"  "+passwd+"  "+url);
	}
	//这个函数是测试db.properties在src目录下cn.lnu包下时如何获得文件流
	public void test2() throws IOException {
		//获得资源文件流
		InputStream in=this.getServletContext().getResourceAsStream("/WEB-INF/classes/cn/lnu/db.properties");
		//读取Properties配置文件的模板方式，java里有一个Properties对象
		Properties pros=new Properties();//内部是map集合方式保存
		pros.load(in);//这个就是将输入流中的数据整合到pros对象中去了
		
		String user=pros.getProperty("username");
		String passwd=pros.getProperty("password");
		String url=pros.getProperty("url");
		System.out.print(user+"  "+passwd+"  "+url);
	}
	
	//这个函数是测试db.properties在WebRoot下时如何获得文件流
	public void test3() throws IOException {
		//获得资源文件流
		InputStream in=this.getServletContext().getResourceAsStream("/db.properties");
		//读取Properties配置文件的模板方式，java里有一个Properties对象
		Properties pros=new Properties();//内部是map集合方式保存
		pros.load(in);//这个就是将输入流中的数据整合到pros对象中去了
		
		String user=pros.getProperty("username");
		String passwd=pros.getProperty("password");
		String url=pros.getProperty("url");
		System.out.print(user+"  "+passwd+"  "+url);
	}
	
	//这个函数是测试db.properties在src下时如何通过servletContext的getRealPath方法获得配置文件的绝对路径再通过传统流去获得文件流的方式
	public void test4() throws IOException {
		//返回配置文件的绝对路径
		String path=this.getServletContext().getRealPath("/WEB-INF/classes/db.properties");
		String filename=path.substring(path.lastIndexOf("\\")+1);//获得资源名称
		System.out.println("当前配置文件名是： "+filename);
		//获得资源文件流
		FileInputStream in=new FileInputStream(path);
		
		//读取Properties配置文件的模板方式，java里有一个Properties对象
		Properties pros=new Properties();//内部是map集合方式保存
		pros.load(in);//这个就是将输入流中的数据整合到pros对象中去了
		System.out.println("当前配置文件配置信息是： ");
		String user=pros.getProperty("username");
		String passwd=pros.getProperty("password");
		String url=pros.getProperty("url");
		System.out.print(user+"  "+passwd+"  "+url);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
