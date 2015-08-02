package cn.lnu.net.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPDemo {

	/**
	 *演示IP对象
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		InetAddress ip=InetAddress.getLocalHost();
		System.out.println(ip);//	MoGu-PC/222.26.26.142
		
		String str_ip=ip.getHostAddress();
		String host_name=ip.getHostName();
		System.out.println(str_ip+":"+host_name);//222.26.26.142:MoGu-PC
		
		//获取其他主机信息
		ip=InetAddress.getByName("www.baidu.com");
		System.out.println(ip);//	www.baidu.com/220.181.111.188
		
		String str_Bip=ip.getHostAddress();
		String host_Bname=ip.getHostName();
		System.out.println(str_Bip+":"+host_Bname);//220.181.111.188:www.baidu.com
	}

}
