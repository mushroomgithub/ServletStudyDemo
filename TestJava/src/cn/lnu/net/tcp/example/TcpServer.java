package cn.lnu.net.tcp.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

	/**实现客户端与服务器端数据交互
	 * 服务器端收到客户端的数据，并反馈数据给客户端，应答
	 * 1，创建服务器端socket对象
	 * 2，获取客户端对象socket
	 * 3，获取客户端对象socket的读取流
	 * 4，获取客户端对象socket的写入流
	 * 
	 * 5，关闭资源
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Tcp服务器端启动...");
		//1,创建服务器端socket对象
		ServerSocket ss=new ServerSocket(10004);
		//2,获取客户端socket对象
		Socket s=ss.accept();
		String client_ip=s.getInetAddress().getHostAddress();
		System.out.println(client_ip+".....connected!");
		//3，获取客户端对象socket的读取流、
		InputStream in=s.getInputStream();
		byte buf[]=new byte[1024];
		int len=0;
		len=in.read(buf);
		String data=new String(buf,0,len);
		System.out.println(data);
		
		//4，获取客户端对象socket的写入流
		OutputStream out=s.getOutputStream();
		out.write("好的,你发的数据我收到了".getBytes());
		
		//5，关闭资源
		s.close();
		ss.close();
	}

}
