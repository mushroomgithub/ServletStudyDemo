package cn.lnu.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//处理文件下载页面的下载请求的servlet
public class DownloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");//这种方式只能解决post方式提交过来的数据的中文乱码问题，现在是通过超链接get方式提交的过来的，所以设置编码无效，必须手工转码
		String filename=request.getParameter("filename");
		//手动解决get方式获得的参数中文乱码问题，
		filename=new String(filename.getBytes("iso8859-1"),"UTF-8");
		
		//根据文件名，定位到服务器中这个文件所在目录(之前上传的文件的保存目录是用hash方法设置的文件保存在服务器端的目录)
		String path=this.getServletContext().getRealPath("/WEB-INF/upload")+File.separator+getFilePath(filename);
		
		//在读服务器中的这个文件之前，首先判断一下这个文件是否在服务器这个目录中
		File file=new File(path+File.separator+filename);
		if(!file.exists()){//如果文件不存在
			request.setAttribute("message", "对不起,下载文件不存在！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		//得到文件的原始文件名
		//String oldname=file.getName().substring(file.getName().lastIndexOf("_")+1);
		String oldname=filename.substring(filename.indexOf("_")+1);
		//文件存在，开始读文件到浏览器，并在此之前通知浏览器以下载方式打开下列发送的数据(即设置一个头,通知浏览器以下载方式打开)
		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(oldname,"UTF-8"));
		FileInputStream in=new FileInputStream(file);
		//FileOutputStream out=new FileOutputStream("c:\\"+filename);//写给硬盘
		OutputStream out=response.getOutputStream();//获取写给浏览器的输出流
		int len=0;
		byte buffer[]=new byte[1024];
		while((len=in.read(buffer))>0){
			out.write(buffer, 0, len);
		}
		
		in.close();
	}

	public String getFilePath(String filename){
		int hashcode=filename.hashCode();//得到文件名这个字符串保存在内存中的地址32位系统下是一个四个字节的整数
		int dir1=hashcode&0xf;//用户文件名哈希值的后四位做一级文件目录
		int dir2=(hashcode>>4)&0xf;//用户文件名哈希值右移四位再去低四位做二级文件保存目录
		return dir1+File.separator+dir2; //返回的是类似于 3/5这样的文件路径
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
