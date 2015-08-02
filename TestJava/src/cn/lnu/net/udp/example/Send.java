package cn.lnu.net.udp.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//负责发送任务，通过UDPsocket发送数据
public class Send implements Runnable {
	//任务对象一建立，需要socket对象完成数据的发送
	private DatagramSocket ds;
	public Send(DatagramSocket ds){
		super();
		this.ds=ds;
	}
	public void run() {
		//具体的发送数据的任务内容
		//1，要发送的数据来自哪里？键盘录入
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			//1.1读取数据
			String line=null;
			try {
				while((line=br.readLine())!=null){
					
					//1.2将数据变成字节数组
					byte[] buf=line.getBytes();
					//2，将数据封装到数据包中
					DatagramPacket dp=new DatagramPacket(buf,buf.length,InetAddress.getByName("127.0.0.1"),10000);
					//3，将数据包发送出去
					ds.send(dp);
					
					if("over".equals(line)){
						break;
					}
				}
			ds.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
