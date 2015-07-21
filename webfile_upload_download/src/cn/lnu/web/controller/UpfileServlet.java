package cn.lnu.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.lnu.domain.Upfile;
import cn.lnu.service.BusinessService;
import cn.lnu.service.impl.BusinessServiceImpl;
import cn.lnu.utils.WebUtils;

public class UpfileServlet extends HttpServlet {
	//跳转到jsp,显示上传页面
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		request.getRequestDispatcher("/WEB-INF/pages/addfiles.jsp").forward(request, response);
	}
	
	//处理post方式过来的上传文件请求
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)){//如果不是文件上传操作
			request.setAttribute("message", "不支持的文件操作");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		try{
			String path=this.getServletContext().getRealPath("/WEB-INF/upload");//文件的上传目录不能被外界可以直接访问到，所以这里设置到WEB-INF下
			Upfile upfile=WebUtils.doUpload(request,path);
			BusinessService service=new BusinessServiceImpl();
			service.addUpfile(upfile);
			//如果工具类整个处理上传过程没有抛出异常，说明处理成功
			request.setAttribute("message", "文件上传成功！");
		}catch(FileUploadBase.FileSizeLimitExceededException e){
			request.setAttribute("message", "上传文件大小不能超过500M.");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "文件上传失败！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

}
