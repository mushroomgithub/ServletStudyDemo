package cn.lnu.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RegisterUIAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//产生一个随机数存到request域中
		saveToken(request);//这个方法内部实现就是产生一个随机数保存到一个域中，struts提供了函数，不用再像之前不用框架时，我们自己定义个随机数生成器
		
		//产生随机数存到request域之后，跳转到jsp页面，将随机数带过去，
		//request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
		//上面这种方式是不使用框架时，页面跳转方式，这里为了使用struts框架跳转方式，还需要为RegisterUIAction这个action在struts-config.xml中为其配置一个forward，告诉其跳转地址
		//跳转到一个视图
		return mapping.findForward("registerUI");
	}
	
}
