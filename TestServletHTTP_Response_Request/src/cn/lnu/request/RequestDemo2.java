package cn.lnu.request;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
//获得请求头信息和请求数据
public class RequestDemo2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//test1(request);
		test2(request);
		
	}
	//获取请求数据相关的方法
	private void test2(HttpServletRequest request) throws IOException {
		System.out.println("------获取数据方式1-------");
		//一般获得请求数据之后都要先检查后使用
		String nameValue=request.getParameter("username");
		if(nameValue!=null && !nameValue.trim().equals("")){
			System.out.println(nameValue);
		}
		String nameValue2=request.getParameter("password");
		System.out.println(nameValue2);
		
		System.out.println("------获取数据方式2-------");
		
		Enumeration e=request.getParameterNames();
		while(e.hasMoreElements()){
			String name=(String)e.nextElement();
			String value=request.getParameter(name);
			System.out.println(name+" = "+value);
		}
		
		System.out.println("------获取数据方式3-------");
		
		String values[]=request.getParameterValues("username");
		for(int i=0;values!=null && i<values.length;i++){
			System.out.println(values[i]);
		}

		System.out.println("------获取数据方式4-------");
		Map<String,String[]> map=request.getParameterMap();
		
		UserData userData=new UserData();
		try {
			BeanUtils.populate(userData,map);//用map集合中的数据填充bean
			//BeanUtils.copyProperties(userData, fromBean); bean的拷贝
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(userData);
		
		System.out.println("------获取数据方式5（适合与文件上传，一般获取数据不用这种方法）-------");
		
		InputStream in=request.getInputStream();
		int len=0;
		byte buffer[]=new byte[1024];
		while((len=in.read(buffer))>0){
			System.out.println(new String(buffer,0,len));
		}
	}
	
	//获取request请求头相关的方法
	private void test1(HttpServletRequest request) {
		String headValue=request.getHeader ("Accept-Encoding");
		System.out.println(headValue);
		
		System.out.println("-------------");
		Enumeration e=request.getHeaders("Accept-Encoding");
		while(e.hasMoreElements()){
			System.out.println((String)e.nextElement());
		}
		
		System.out.println("-------------");
		//打印所有请求头名
		e=request.getHeaderNames();
		while(e.hasMoreElements()){
			String name=(String)e.nextElement();
			String value=request.getHeader(name);
			System.out.println(name+" = "+value);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
