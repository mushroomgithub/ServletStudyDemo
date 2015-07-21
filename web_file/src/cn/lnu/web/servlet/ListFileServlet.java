package cn.lnu.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//列出网站所有供下载文件，供别人下载
public class ListFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//首先先找到供下载文件所在目录
		String path=this.getServletContext().getRealPath("/WEB-INF/upload");
		//使用递归列出目录下所有文件
		Map map=new HashMap();//使用一个map集合保存所有该目录下的一般文件，带到前台jsp页面显示
		listfile(new File(path),map);
		
		request.setAttribute("map", map);
		request.getRequestDispatcher("/listfiles.jsp").forward(request, response);
	}
	//如果保存递归出来的所有文件，只是递归出所有文件，不会保存文件所在目录
	public void listfile(File file,Map map){
		if(!file.isFile()){//首先判断这个下载目录下的这个文件是不是文件，不是的话，说明是一个目录
			File children[]=file.listFiles();//得到这个目录下的所有文件
			for(File f:children){//迭代这个目录下的所有子文件，递归调用listfile函数，列出所有子目录以及子目录下的所有文件
				listfile(f,map);
			}
		}else{//说明是普通文件，不是目录
			String filename=file.getName().substring(file.getName().indexOf("_")+1);//根据服务器提供的uuid名，切割出文件原始文件名  28134-4194-4121-644_a_b.txt-->a_b.txt
			map.put(file.getName(),filename ); //jsp页面  <a href="/servlet/DownloadFileServlet?filename=文件在服务器中的转化为uuid的名称">文件的原始文件名</a>
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
