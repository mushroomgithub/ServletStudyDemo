package cn.lnu.net.tcp.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadImageServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("上传图片服务器端启动...");
		//服务端对象
		ServerSocket ss=new ServerSocket(10006);
		while(true){
			Socket s=ss.accept();//每次accept接收一个客户端请求，然后下面创建一个线程处理这个客户端请求
			/*
			 * 服务器端必须能够处理并发上传请求，也就是没accept一个客户端对象，就创建一个线程任务处理客户端的请求，
			 * web服务器就是这么做的(因为tomcat服务器底层就是使用SocketServer写的),这样就可以实现由单独的线程处理客户端请求，从而实现并发访问
			 * */
			new Thread(new UploadImage(s)).start();
		}
		//ss.close();
	}
}
