package cn.lnu.net.tcp.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadTextServer {

	/**
	 * 上传文本的服务端，接收文本数据，并存储在文件中，服务器接收完毕后，回馈”上传成功“字样
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("上传文本服务器端启动...");
		//1，服务端对象
		ServerSocket ss=new ServerSocket(10005);
		//2，获取客户端socket
		Socket s=ss.accept();
		String client_ip=s.getInetAddress().getHostAddress();
		System.out.println(client_ip+".....connected!");
		//3，获取读取流，确定源，网络socket
		BufferedReader bufIn=new BufferedReader(new InputStreamReader(s.getInputStream()));
		//4，确定目的，服务器端文件
		PrintWriter pw=new PrintWriter(new FileWriter("server.txt"));//PrintWriter流读到什么什么写什么
		//5，频繁读写
		String line=null;
		while((line=bufIn.readLine())!=null){
			/*if("over".equals(line)){
				break;
			}*/
			pw.write(line);
		}
		
		//6，给客户端返回信息
		//BufferedWriter out=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		PrintWriter out=new PrintWriter(s.getOutputStream(),true);
		out.println("上传成功");
		//out.write("上传成功");
		
		//7，关闭资源
		pw.close();
		s.close();
		ss.close();
	}

}
