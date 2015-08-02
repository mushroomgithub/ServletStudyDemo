package cn.lnu.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPRecvDemo {

	/**
	 * 创建UDP的接收端
	 * 思路：
	 * 1，创建socket服务，并明确一个服务器端端口，表示在该端口上监听数据
	 * 2，收数据
	 * 3，将其中所需要的数据取出来，ip，data,端口
	 * 4，关闭资源
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.print("UDP服务器端启动了...");
		//1创建socket服务
		DatagramSocket ds=new DatagramSocket(8000);
		//2，使用socket的接收方法，接收数据,需要将收到的数据存储到数据包中
		//可以通过数据包对象的方法对收到的数据进行解析
			//2.1创建一个数据包
		byte[] buf=new byte[1024];
		DatagramPacket dp=new DatagramPacket(buf,buf.length);
		ds.receive(dp);//阻塞式方法
		//3，通过数据包对象解析收到的数据，使用数据包的方法
		String from_ip=dp.getAddress().getHostAddress();
		int port=dp.getPort();
		//获得文本数据
		String data=new String(dp.getData(),0,dp.getLength());
		System.out.println(from_ip+":"+port+" has send "+data);
		//4，关闭资源
		ds.close();
	}

}
