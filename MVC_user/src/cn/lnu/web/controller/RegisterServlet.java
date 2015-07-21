package cn.lnu.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lnu.domain.User;
import cn.lnu.exception.UserExistException;
import cn.lnu.service.impl.BusinessServiceImpl;
import cn.lnu.utils.WebUtils;
import cn.lnu.web.formbean.RegisterForm;
//处理注册请求
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//为了防止从表单获得验证码不是乱码，并且昵称也不会正确匹配
			request.setCharacterEncoding("UTF-8");
			
			//1.对表单提交字段进行合法性校验（把表单数据封装到formbean）
			RegisterForm bean=WebUtils.request2Bean(request,RegisterForm.class);
			boolean b;
			try {
				b = bean.validate(request);
				//2.校验失败，调回到表单页面，回显校验失败信息
				if(!b){
					//将错误消息也带回注册页面，用于给用户友好提示
					request.setAttribute("bean", bean);
					request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
					return;
				}
				
				//3.如果校验成功，则调用service层处理注册请求
				//将表单中的数据整合到user中
				User user=new User();
				WebUtils.copyBean(bean, user);
				//为用户创建一个全球唯一id
				user.setId(WebUtils.generateID());
				
				BusinessServiceImpl service=new BusinessServiceImpl();
				try{
				service.register(user);//将用户注册到数据库中
				//6. 如果service处理成功，则跳转到网站的全局消息显示页面，为用户显示注册成功的消息
				request.setAttribute("message", "恭喜你注册成功！");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
				}catch(UserExistException e){
					//4.如果service处理不成功，并且不成功的原因是注册用户已经存在，则跳回到注册页面，显示注册用户已存在
					bean.getErrors().put("username", "注册用户名已存在!");
					request.setAttribute("bean", bean);
					request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
					return;
				}catch(Exception e){
					//5,如果service处理不成功，并且不成功的原因是其他问题的话，则跳转到网站的全局消息显示页面，为用户显示友好错误消息
					e.printStackTrace();
					request.setAttribute("message","服务器出现未知错误！");
					request.getRequestDispatcher("/message.jsp").forward(request, response);
					return;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
