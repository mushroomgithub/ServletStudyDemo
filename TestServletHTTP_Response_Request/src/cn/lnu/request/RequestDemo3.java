package cn.lnu.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestDemo3 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username=request.getParameter("username");
		System.out.println(username);
		String password=request.getParameter("password");
		System.out.println(password);
		String gender=request.getParameter("gender");
		System.out.println(gender);
		String city=request.getParameter("city");
		System.out.println(city);
		String hobbies[]=request.getParameterValues("hobby");
		for(int i=0;hobbies!=null&&i<hobbies.length;i++){
			System.out.println(hobbies[i]);
		}
		
		String description=request.getParameter("description");
		System.out.println(description);
		String imageFilePath=request.getParameter("image");
		System.out.println(imageFilePath);
		String idValue=request.getParameter("id");
		System.out.println(idValue);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
