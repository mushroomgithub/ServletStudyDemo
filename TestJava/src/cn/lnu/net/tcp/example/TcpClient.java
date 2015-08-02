package cn.lnu.net.tcp.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {

	/**实现客户端与服务器端数据交互
	 * 需求：客户端发送数据给服务器，并读取服务器端反馈的数据
	 * 1,创建socket客户端
	 * 2，获取socket输出流，写数据
	 * 3，获取socket的读取流，读取服务器端发回的数据
	 * 4关闭资源
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		System.out.println("tcp客户端启动...");
		//1，创建socket客户端
		Socket s=new Socket("127.0.0.1",10004);
		//2,获取socket输出流，写数据
		OutputStream out=s.getOutputStream();
		out.write("服务器,客户端数据要来了".getBytes());
		//3,获取socket的读取流，读取服务器端发回的数据
		InputStream in=s.getInputStream();
		byte[] buf=new byte[1024];
		int len=0;
		len=in.read(buf);
		String data=new String(buf,0,len);
		System.out.println(data);
		//4，关闭资源
		s.close();
	}

}
