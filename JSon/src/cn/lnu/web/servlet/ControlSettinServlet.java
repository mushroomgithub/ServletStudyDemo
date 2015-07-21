package cn.lnu.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.lnu.utils.WebUtils;
import cn.lnu.web.formbean.CotrolForm;
//处理控制机设置校验servlet
public class ControlSettinServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解决中文乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//将客户端带过来的参数转存到一个bean对象中
		CotrolForm control =WebUtils.request2Bean(request, CotrolForm.class);
    	
    	String tcount=request.getParameter("tcount");
    	System.out.println("request="+tcount);
    	
		boolean isCheck;
		try{
			isCheck=control.validate(request);
			if(!isCheck){//系统设置失败调回设置页面，重写设置，给出友好提示
				request.setAttribute("control", control);
				//校验失败，将失败的提示信息，返回到设置界面，提示用户
				Map map=control.getErrors();
				JSONObject jsonErrors=JSONObject.fromObject(map);
				String jstrErrors=jsonErrors.toString();
				request.setAttribute("jstrErrors", jstrErrors);
				
				request.getRequestDispatcher("/WEB-INF/jsp/controlSyscfg.jsp").forward(request, response);
				return;
			}
			//校验成功
			try{
				//将用户设置信息保存到数据库中，暂时先不考虑，待以后扩展，这里模拟把对象转化为json格式的字符串返回前台页面显示
				JSONObject json = JSONObject.fromObject(control);
				String jstr=json.toString();
				request.setAttribute("jstr", jstr);
			
				//跳转到网站的全局消息显示页面，为用户显示设置成功的消息
				request.setAttribute("message", "恭喜你,控制管理机设置成功！");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}catch(Exception e){
				e.printStackTrace();
				request.setAttribute("message","服务器出现未知错误！");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
