package cn.lnu.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//处理文件上传的servlet
public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//如果表单类型为enctype="multipart/form-data"的话，在servlet中注意就不能使用传统方式获得前台表单数据了
		/*String username=request.getParameter("username");
		System.out.println(username);*/
		//由于文件上传时的数据时经过mime描述过的流，此时要想在后台获得前台页面提交过来的数据，就必须使用获取的流的方式获得
		InputStream in=request.getInputStream();//将前台数据以流的方式获取，然后返回一个代表前台提交数据的流对象,后台只能采取下面这种流的方式读取前台数据
		OutputStream out=null;
		try{
			int len=0;
			byte buffer[]=new byte[1024];
			while((len=in.read(buffer))>0){
				System.out.println(new String(buffer,0,len));//解析出流中每部分的数据做处理
				//out.write(buffer, 0, len);
			}
		}finally{
			if(in!=null){
				in.close();
			}
			if(out!=null){
				out.close();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
