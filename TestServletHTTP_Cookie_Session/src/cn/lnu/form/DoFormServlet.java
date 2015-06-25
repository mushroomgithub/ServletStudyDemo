package cn.lnu.form;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//处理表单提交请求
public class DoFormServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*String username=request.getParameter("username");
		
		//让其睡眠3秒模拟服务器端有延迟，这样在重复点击提交按钮的时候就模拟出了重复提交表单的情形
		try {
			Thread.sleep(1000*3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("向数据库中注册用户---");*/
		
		boolean b=isTokenValid(request);
		if(!b){//如果为false，表示之前已经提交过了
			System.out.println("请不要重复提交！");
			return;
		}
		
		//为true表示还没有提交，则处理提交，并删除令牌
		request.getSession().removeAttribute("token");
		System.out.println("向数据库中注册用户~~~");
		
	}
	//判断令牌表单号是否有效
	private boolean isTokenValid(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//获得来自客户机带过来的token
		String Client_token=request.getParameter("token");
		if(Client_token==null){//判断客户机是否带token过来，一般都会带过来，但是这么做是为了防止hacker恶意提交
			return false;
		}
		//获得来自服务器端带过来的token
		String Server_token=(String) request.getSession().getAttribute("token");
		if(Server_token==null){//用户已经提交过一次。服务器端已经删除token
			return false;
		}
		//如果客户机带过来的token和服务器端的token不一致，也认为是重复提交
		if(!Client_token.equals(Server_token)){
			return false;
		}
		
		return true;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
