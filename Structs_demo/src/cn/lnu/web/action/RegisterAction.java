package cn.lnu.web.action;

import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import cn.lnu.web.formbean.RegisterBean;

public class RegisterAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		//处理表单重复提交
		if(!this.isTokenValid(request)){
			System.out.println("表单重复提交！！");
			return null;
		}
		
		//处理表单提交，处理前先删除表单号码随机数
		this.resetToken(request);
		
		//处理文件上传
		RegisterBean registerForm=(RegisterBean) form;
		FormFile file=registerForm.getFile();
		String filename=file.getFileName();//获得上传文件名
		InputStream in=file.getInputStream();//获得上传文件的流，接下来就可以读了
		int len=0;
		byte buffer[]=new byte[1024];
		
		FileOutputStream out=new FileOutputStream("c:\\"+filename);//将上传文件写到磁盘
		while((len=in.read(buffer))>0){
			out.write(buffer,0,len);
		}
		in.close();
		out.close();
		System.out.println("处理表单提交！！");
		return null;
		/*RegisterBean registerForm=(RegisterBean) form;
		String username=registerForm.getUsername();
		String password=registerForm.getPassword();
		String email=registerForm.getEmail();
		try{
			System.out.println("向数据库中注册 "+username+" 用户");
			request.setAttribute("message", "注册成功");
		}catch(Exception e){
			request.setAttribute("message", "注册失败");
		}
		//请求跳转
		//request.getRequestDispatcher("/message.jsp").forward(request, response);//非框架传统跳转页面方式
		//使用structs提供的跳转方式
		//ActionForward forward=new ActionForward("/message.jsp");
		//为了不写死，还可以在structs的配置文件中配置调用这个action时要跳转的页面路径
		return mapping.findForward("message");*/
	}
	
}
