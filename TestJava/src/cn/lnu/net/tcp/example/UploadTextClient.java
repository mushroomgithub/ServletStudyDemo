package cn.lnu.net.tcp.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class UploadTextClient {

	/**
	 * 实现客户端文本上传
	 * 上传文本的客户端，读取本地文本数据，发送给服务器端，服务端接收完毕后，回馈“上传成功"字样
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		System.out.println("上传文本客户端启动...");
		Socket s=new Socket("127.0.0.1",10005);
		//1，确定数据源，本地文本文件
		BufferedReader bufr=new BufferedReader(new FileReader("client.txt"));//FileReader可以读取字符/文本文件，BufferedReader为包装流可以每次读取一行文本
		//2，确定目的，socket的输出流
		//BufferedWriter bufw=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));//将每次读取到的字符流不用转换之间写到字节流中，内部帮我们实现字符流到字节流的转换，为了方便，每次写一行，使用BufferedWriter包装流
		PrintWriter out=new PrintWriter(s.getOutputStream(),true);//printWriter可以实现原样文本数据的输出，读到什么就写什么，为了保证自动刷新，将第二个参数设置为true
		String line=null;
		while((line=bufr.readLine())!=null){
			out.println(line);
		}
		//为了告诉服务器文件发送完毕，给服务器发送结束标记，这是第一种方式
		//out.println("over");//容易出现重复，如果文件有此标记的话
		//第二种方式，使用socket的禁用输出流方法，这个方法是在客户端发送数据之后，调用该方法，告诉服务器端数据发送完毕，内部会带一个结束标记过去
		s.shutdownOutput();
		//3,通过socket读取流获取服务器端返回的数据
		BufferedReader bufIn=new BufferedReader(new InputStreamReader(s.getInputStream()));
		String lineIn=bufIn.readLine();
		System.out.println(lineIn);
		
		//4，关闭资源
		bufr.close();
		s.close();
	}

}
