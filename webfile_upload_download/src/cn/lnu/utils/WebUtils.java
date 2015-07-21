package cn.lnu.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.lnu.domain.Upfile;

public class WebUtils {//这个工具类，实现处理文件上传请求，并将上传文件保存到path目录下，然后将上传的文件返回给一个Upfile对象
	
	public static Upfile doUpload(HttpServletRequest request,String uppath) throws FileSizeLimitExceededException{
		Upfile bean=new Upfile();//创建用于封装上传文件信息的bean
		try{
			DiskFileItemFactory factory=new DiskFileItemFactory();//创建一个解析器工厂类对象
			factory.setRepository(new File(request.getSession().getServletContext().getRealPath("/WEB-INF/temp")));//如果上传文件过大，设置上传文件的缓存目录
			ServletFileUpload upload=new ServletFileUpload(factory);//使用工厂创建一个文件上传解析器
			upload.setHeaderEncoding("UTF-8");//解决一下上传文件的中文乱码问题
			upload.setFileSizeMax(1024*1024*500);//设置上传文件的最大为500M，文件过大或抛出FileUploadBase.FileSizeLimitExceededException异常，这个异常需要单独抓取
			List<FileItem> list=upload.parseRequest(request);
			
			for( FileItem item:list){
				if(item.isFormField()){//如果是普通字段
					String name=item.getFieldName();//username=mushroom         description=fhfhafahfahfa
					String value=item.getString("UTF-8");
					System.out.println(name+"="+value);
					BeanUtils.copyProperty(bean, name, value);//使用Beanutils将普通字段的名字和值封装到bean中
				}else{//上传输入项字段
					//得到上传文件名即对应数据库realname字段
					String filename=item.getName().substring(item.getName().lastIndexOf("\\")+1);
					//根据上传文件名如1.txt，生成一个唯一的uuid文件名，防止上传到服务器中的文件名名字冲突
					String uuidname=generateUidFileName(filename);
					//得到文件的保存路径
					String savepath=generateSavePath(uppath,uuidname);//uppath是上传目录，最后返回的才是真正的保存目录
					
					//然后开始将上传文件读到生成的保存在服务器上的保存目录上
					InputStream in=item.getInputStream();
					int len=0;
					byte buffer[]=new byte[1024];
					FileOutputStream out=new FileOutputStream(savepath+"\\"+uuidname);
					while((len=in.read(buffer))>0){
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();//删除当前上传文件在上传过程中在缓存目录中的缓存文件
					
					bean.setRealname(filename);
					bean.setId(UUID.randomUUID().toString());
					bean.setSavepath(savepath);
					bean.setUuidname(uuidname);
					bean.setUptime(new Date());
				
				}
			}
			return bean;
		}catch(FileUploadBase.FileSizeLimitExceededException e){
			throw e;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	private static String generateUidFileName(String filename){
		String ext=filename.substring(filename.lastIndexOf(".")+1);//得到上传文件的扩展名
		return UUID.randomUUID().toString()+"."+ext;
	}
	
	private static String generateSavePath(String path,String filename){//给出文件名使用hash算法生成子目录，然后将这些子目录保存在传递进来的savepath目录下
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
}
