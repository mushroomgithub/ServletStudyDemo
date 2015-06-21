package cn.lnu.response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//使用Response实现文件下载和带中文文件名的文件的下载
public class ResponseDemo3 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String path = this.getServletContext().getRealPath("/download/qe.jpg");
		String path = this.getServletContext().getRealPath("/download/不错.pdf");
		String filename = path.substring(path.lastIndexOf("\\") + 1);
		//如果下载文件是中文文件名，则文件名需要经过url编码
		//response.setHeader("content-disposition", "attachment;filename="+ filename);
		response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(filename,"UTF-8"));
		
		//写文件到文件流或者浏览器
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(path);
			int len = 0;
			byte buffer[] = new byte[1024];
			out = response.getOutputStream();//这么写是写给浏览器
			//out=new FileOutputStream("c:/2.jpg");//这样写是写到文件中去了
			while ((len = in.read(buffer)) > 0) {// 如果读到数据
				out.write(buffer, 0, len);// 每次都是从buffer的开始读len个字节
			}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
