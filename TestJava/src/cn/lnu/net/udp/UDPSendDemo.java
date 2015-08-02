package cn.lnu.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSendDemo {

	/**
	 * 需求：建立udp的发送端
	 * 思路：
	 * 1，建立可以实现udp传输的socket服务
	 * 2，明确具体发送的数据
	 * 3，通过socket服务将具体的数据发送出去
	 * 4，关闭服务
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("UDP发送客户端启动...");
		//1，创建udp服务
		DatagramSocket ds=new DatagramSocket(8888);//指定一个发送端端口，也可以不指定，这样发送数据的端口是随机的
		//2，明确数据
		String str="注意了，UDP数据要来了";
		//3，发送数据，先将数据封装到数据包中
			//3.1将数据封装到数据包对象中，数据包会明确目的地址和端口
		byte[] buf=str.getBytes();
		int length=buf.length;
		DatagramPacket dp=new DatagramPacket(buf,length,InetAddress.getByName("127.0.0.1"),8000);
			//3.2发送数据包
		ds.send(dp);
		
		//4,关闭服务
		ds.close();
		
	}

}
