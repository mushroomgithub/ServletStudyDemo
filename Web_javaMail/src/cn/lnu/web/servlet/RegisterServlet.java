package cn.lnu.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lnu.domain.User;
import cn.lnu.utils.SendMail2;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		
		try{
			//向数据库注册用户
			
			//启动一个线程向用户发一封邮件
			/*SendMail send=new SendMail(user);
			send.start();//启动线程，这个函数会自动调用这个类的run方法，执行向user发送邮件操作*/
			
			Thread t=new Thread(new SendMail2(user));
			t.start();
			
			request.setAttribute("message", "注册成功,我们已经给你发送一封邮件，请及时查收！");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "注册失败！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
