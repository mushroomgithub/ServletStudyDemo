package cn.lnu.net.udp.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

//负责接收任务
public class Recv implements Runnable {
	//任务对象一建立，需要socket对象完成数据的接收
	private DatagramSocket ds;
	public Recv(DatagramSocket ds){
		super();
		this.ds=ds;
	}
	
	public void run() {
		while(true){
			//接收的具体任务内容
			//1，接收的数据最终都会存储到数据包中，而数据包中必须有字节数组。
			byte[] buf=new byte[1024];
			//2，创建数据包对象
			DatagramPacket dp=new DatagramPacket(buf,buf.length);
			//3将接收到数据数据存储到数据包中
			try {
				ds.receive(dp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//4，获取数据
			String fromip=dp.getAddress().getHostAddress();
			String data=new String(dp.getData(),0,dp.getLength());
			System.out.println(fromip+":"+data);
			if("over".equals(data)){
				System.out.println(fromip+"-----离开聊天室了");
			}
		}
		
	}

}
