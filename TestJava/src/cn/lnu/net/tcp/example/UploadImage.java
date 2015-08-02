package cn.lnu.net.tcp.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//处理每个socket客户端请求的线程任务
public class UploadImage implements Runnable {
	private Socket s;
	public UploadImage(Socket s){
		this.s=s;
	}
	public void run() {
		// TODO Auto-generated method stub
		String client_ip=s.getInetAddress().getHostAddress();
		System.out.println(client_ip+".....connected");
		File file = getFile("e:\\server_image",client_ip);
		InputStream in;
		try {
			in = s.getInputStream();
			FileOutputStream fos=new FileOutputStream(file);	
			byte[] buf=new byte[1024];
			int len=0;
			while((len=in.read(buf))!=-1){
				fos.write(buf, 0, len);
			}
			//给客户端回馈数据
			OutputStream out=s.getOutputStream();
			out.write("上传图片成功".getBytes());	
			//关闭资源
			fos.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static File getFile(String dir,String client_ip) {
		File image_dir=new File(dir);//创建服务器端保存文件的文件夹
		if(!image_dir.exists()){
			image_dir.mkdir();
		}
		int count=1;
		File file=new File(image_dir,client_ip+"("+count+").jpg");
		while(file.exists()){
			count++;
			file=new File(image_dir,client_ip+"("+count+").jpg");
		}
		return file;
	}
}
