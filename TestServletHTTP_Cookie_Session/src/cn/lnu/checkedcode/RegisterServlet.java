package cn.lnu.checkedcode;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//注册servlet
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解决中文乱码问题，否则验证码比较不会正确
		request.setCharacterEncoding("UTF-8");
		
		//在注册之前校验用户输入的验证码是否有效
		//得到客户机带过来的随机验证码
		String c_checkedcode=request.getParameter("checkcode");
		//得到服务器session中保存的图片验证码
		String s_checkedcode=(String) request.getSession().getAttribute("checkcode");
		if(c_checkedcode!=null && s_checkedcode!=null && c_checkedcode.equals(s_checkedcode)){
			System.out.println("处理用户注册请求!!");
		}else{
			//验证码输入错误
			System.out.println("输入验证码错误!");
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
