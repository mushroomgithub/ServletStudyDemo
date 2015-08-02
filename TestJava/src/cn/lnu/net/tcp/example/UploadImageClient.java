package cn.lnu.net.tcp.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class UploadImageClient {

	/**
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		System.out.println("上传图片客户端启动...");
		//1，创建Socket客户端
		Socket s=new Socket("127.0.0.1",10006);
		//2,确定源
		File file=new File("Lighthouse.jpg");
		FileInputStream fis=new FileInputStream(file);
		
		//确定目的，Socket的输出流
		OutputStream out=s.getOutputStream();
		byte[] buf=new byte[1024];
		int len=0;
		while((len=fis.read(buf))!=-1){
			out.write(buf, 0, len);
		}
		//告诉服务器写完了
		s.shutdownOutput();
		//读取服务器端数据
		InputStream in=s.getInputStream();
		byte[] bufIn=new byte[1024];
		int lenIn=0;
		lenIn=in.read(buf);
		String data=new String(bufIn,0,lenIn);
		System.out.println(data);
		
		//关闭资源
		fis.close();
		s.close();
	}

}
