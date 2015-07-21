package cn.lnu.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lnu.domain.Upfile;
import cn.lnu.service.BusinessService;
import cn.lnu.service.impl.BusinessServiceImpl;
//处理文件下载请求
public class DownloadFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//通过前台超链接带过来的下载文件的id，在服务器找到要下载文件信息
		String id=request.getParameter("id");
		BusinessService service=new BusinessServiceImpl();
		Upfile upfile=service.finUpfile(id);
		File file=new File(upfile.getSavepath()+"\\"+upfile.getUuidname());
		if(!file.exists()){
			request.setAttribute("messsage", "对不起，你下载的文件不存在，可能已被删除！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		//从服务器下载目录下，下载文件
		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(upfile.getRealname(),"UTF-8"));//告诉浏览器以下载的方式打开
		//FileInputStream in=new FileInputStream(upfile.getSavepath()+"\\"+upfile.getUuidname());//不要上来就读文件，首先判断文件在服务器中是否存在
		FileInputStream in=new FileInputStream(file);
		int len=0;
		byte buffer[]=new byte[1024];
		OutputStream out=response.getOutputStream();//写给浏览器
		while((len=in.read(buffer))>0){
			out.write(buffer, 0, len);
		}
		in.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
