package cn.lnu.net.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {

	/**
	 * tcp客户端的建立
	 * 思路：
	 * 1，建立tcp客户端服务
	 * 	 1.1因为是面向连接，必须有连接才可以进行通信
	 * 	 1.2在创建客户端时，就必须明确目的地址和端口
	 * 
	 * 2，一旦连接建立，就有了传输数据的通道，就可以在通道中进行数据传输了。
	 * 这个传输其实就是通过流实现的，这个流就是Socket IO流。
	 * 3，只要获取到Socket IO流中的写动作就可以把数据写到socket流中发给服务器
	 * 4，关闭资源
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		System.out.println("TCP客户端启动...");
		//1，创建客户端对象，明确目的地址和端口
		Socket s=new Socket("127.0.0.1",10001);
		//2，获得socket流中的输出流，将数据发送给服务器
		OutputStream out=s.getOutputStream();
		//3，通过输出流写数据
		out.write("注意了，tcp数据要来了".getBytes());
		
		//4，关闭资源
		s.close();
	}

}
