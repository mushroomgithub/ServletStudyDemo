package cn.lnu.net.udp.example;

import java.net.DatagramSocket;
import java.net.SocketException;

public class ChatTest {

	/**
	 * 通过UDP协议
	 * 完成一个聊天程序
	 * 一个负责发送数据的任务，一个负责接收数据的任务，两个任务需要同时执行
	 * 可以使用多线程技术
	 * @throws SocketException 
	 */
	public static void main(String[] args) throws SocketException {
		// TODO Auto-generated method stub
		//1,创建socket服务
		DatagramSocket send=new DatagramSocket(8888);
		DatagramSocket recv=new DatagramSocket(10000);
		new Thread(new Send(send)).start();
		new Thread(new Recv(recv)).start();
	}

}
