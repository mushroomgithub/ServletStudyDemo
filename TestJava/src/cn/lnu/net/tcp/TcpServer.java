package cn.lnu.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

	/**
	 *创建TCP服务器端
	 *思路：
	 *1，创建socket服务器端服务，服务器端为了让客户端可以连接上，必须提供端口，并监听这个端口
	 *2，获取客户端对象，通过客户端的socket流和对应的客户端进行通信
	 *3，获取客户端的socket流的读取流
	 *4，读取数据，并显示在服务器端
	 *5，关闭资源
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("TCP服务器端启动了...");
		//1，创建服务器端对象
		ServerSocket ss=new ServerSocket(10001);
		//2，获取客户端对象
		Socket s=ss.accept();
		String fromip=s.getInetAddress().getHostAddress();
		System.out.println(fromip+"----connected!");
		
		//3,通过客户端对象获取socket流的读取流
		InputStream in=s.getInputStream();
		//4，读取客户端socket流的读取流读取数据，并显示
		byte[] buf=new byte[1024];
		
		int len=in.read(buf);
		String str=new String(buf,0,len);
		System.out.print(str);
		//5，关闭资源
		s.close();//关闭连接过来的客户端
		ss.close();//关闭服务器端
	}

}
