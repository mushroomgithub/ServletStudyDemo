package cn.lnu.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
//使用apache开源组织提供的fileupload组件解析前台表单包含上传文件的流
public class UploadServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List types=Arrays.asList("jpg","bmp","avi","rmvb","exe","txt","doc","docx");//限制文件上传类型只能是这几种类型
		try{
			DiskFileItemFactory factory=new DiskFileItemFactory();//首先创建一个FileItem解析工厂
			factory.setSizeThreshold(1024*1024);//设置解析器缓存大小为1MB，如果上传文件小于1M，对于上传文件是从缓存中读取，如果大于1M,则将上传文件先保存到缓存文件中，之后获取上传文件输入项的流会自动从缓存文件中读取上传文件的数据，而不是从缓存中读取
			factory.setRepository(new File(this.getServletContext().getRealPath("/temp")));//当缓存大于1M时，上传文件保存到缓存文件，这里还可以设置缓存文件的目录
			ServletFileUpload upload=new ServletFileUpload(factory);//将fileitem工厂传递给一个解析器
			
			//解析器如何监听上传进度，为前台提供解析器获得进度条数据，必须在解析器解析之前设置这个监听器
			upload.setProgressListener(new ProgressListener(){
				public void update(long pBytesRead, long pContentLength, int pItems) {
							System.out.println("当前已解析："+pBytesRead+"字节");
							System.out.println("解析文件总长度："+pContentLength+"字节");
							System.out.println("现在解析到第："+pItems+"项");
				}	
			});
			
			upload.setFileSizeMax(1024*1024*5);//通过解析器设置上传文件不能超过5M,否则会抛出异常
			if(!upload.isMultipartContent(request)){//如果前台封装到request中的数据不是上传表单数据
				//按传统方式获取表单数据
				String username=request.getParameter("username");
				//....
				return;
			}
			upload.setHeaderEncoding("UTF-8");//为了防止上传文件中文乱码问题，需要设置一下解析器的编码
			List<FileItem> list=upload.parseRequest(request);//使用解析器解析前台发过来的request，得到保存了所有上传数据的list集合，集合中每个元素都是一个FileItem，可能是代表的普通输入项，也可能代表的是上传文件输入项
			for(FileItem item:list){//迭代这个list集合获得封装了每个输入项的FileItem，判断这个item是普通数据输入字段，还是文件上传字段
				if(item.isFormField()){//表示当前迭代的item是一个普通输入项
					String inputName=item.getFieldName();//获得前台输入项的名称
					String inputValue=item.getString("UTF-8");//获得普通输入项的值,并指定获取一般输入项值使用的码表
					/*String inputValue=item.getString();//获得普通输入项的值
					inputValue=new String(inputValue.getBytes("iso8859-1"),"UTF-8");//手动解决一般输入项值的中文乱码问题*/
					System.out.println(inputName+"="+inputValue);
				}else{//代表当前处理的item里封装的是上传文件输入项，则调用流获得数据，写到本地硬盘或者服务器下某个目录
					String filename=item.getName().substring(item.getName().lastIndexOf("\\")+1);//获取上传文件名，如果是ie6，获得是完整的全路径的文件名C:\Users\MoGu\Desktop\1.txt，而IE7以上获得只是单纯的文件名1.txt
					if(filename==null || filename.trim().equals("")){//处理上传文件名是否为空，即没有选择文件上传的情形，结束本次循环，进入下次循环，继续处理其他文件上传
						continue;
					}
					
					String ext=filename.substring(filename.lastIndexOf(".")+1);//获取文件扩展名
					if(!types.contains(ext)){//如果上传文件扩展名不在list集合中，提示用户上传文件类型不支持
						request.setAttribute("message", "本网站不支持扩展名为"+ext+"的文件上传操作！");
						request.getRequestDispatcher("/message.jsp").forward(request, response);
						return;
					}
					
					InputStream in=item.getInputStream();//获得一个代表上传文件的流，这个刘很聪明，如果上传文件没超过缓存大小，则就按下面方法从缓存中读取上传文件数据，如果上传文件大于了缓存大小(1M),会自动去缓存目录下的缓存文件中读取上传文件数据，保存到本地，不会再去缓存中读取了，缓存中也没有数据
					int len=0;
					byte buffer[]=new byte[1024];
					//FileOutputStream out=new FileOutputStream("c:\\"+filename);//使用这个输出流将这个文件的数据保存到c盘下的名称为filename的文件中
					String saveFileName=generateFileName(filename);
					String savepath=generateSavePath(this.getServletContext().getRealPath("/WEB-INF/upload"),saveFileName);
					//使用上面两个语句获得唯一文件名和分散目录，可以实现上传文件被随机打撒到不同目录下保存
					FileOutputStream out=new FileOutputStream(savepath+File.separator+saveFileName);//实际开发中是将上传文件上传到服务器中，并且这个上传目录不能直接被外界访问到
					while((len=in.read(buffer))>0){
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();//删除由于上传文件保存到缓存目录下的临时文件，这句代码必须在流关闭之后
				}
			}
		}catch(FileUploadBase.FileSizeLimitExceededException e){//上传文件大小超出异常
			e.printStackTrace();
			request.setAttribute("message", "上传文件大小不能超过5M!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		request.setAttribute("message", "上传成功！");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public String generateSavePath(String path,String filename){//给出文件名使用hash算法生成子目录，然后将这些子目录保存在传递进来的savepath目录下
		int hashcode=filename.hashCode();//得到文件名这个字符串保存在内存中的地址32位系统下是一个四个字节的整数
		int dir1=hashcode&0xf;//用户文件名哈希值的后四位做一级文件目录
		int dir2=(hashcode>>4)&0xf;//用户文件名哈希值右移四位再去低四位做二级文件保存目录
		
		//最终保存目录为：
		String savepath=path+File.separator+dir1+File.separator+dir2;
		File file=new File(savepath);//由于第一次上传时，这个目录还没有创建在磁盘，所以必须先判断一下，没有创建的话，收到创建
		if(!file.exists()){
			file.mkdirs();//由于是多级目录，必须使用mkdirs()这个函数
		}
		return savepath;
	}
	
	//为上传文件生成一个唯一的文件名
	public String generateFileName(String filename){
		return UUID.randomUUID().toString()+"_"+filename;
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
