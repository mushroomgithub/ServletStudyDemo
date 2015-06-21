package cn.lnu.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//forward需要注意的细节，以下代码会抛出异常
public class RequestDemo6 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message="I am a student.";
		
		request.setAttribute("message", message);
		
		if(true){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			//return;//不加return，下面就会抛出异常
		}
		//以下这句会抛出java.lang.IllegalStateException: Cannot forward after response has been committed异常，若想不让其产生
		//这个异常，则需要在每次跳转之后添加return语句
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
