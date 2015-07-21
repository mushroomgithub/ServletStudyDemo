package cn.lnu.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Demo1 {

	/**
	 * ÓÊ¼þ·¢ËÍ  javamail api
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws Exception {
		
		// aaa--->bbb
		Socket socket = new Socket("localhost",25);
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		OutputStream out = socket.getOutputStream();
		
		System.out.println(br.readLine());
		out.write("ehlo flx\r\n".getBytes());
		
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		
		
		out.write("auth login\r\n".getBytes());
		System.out.println(br.readLine());
		
		out.write("YWFh\r\n".getBytes());
		System.out.println(br.readLine());
		
		out.write("MTIz\r\n".getBytes());
		System.out.println(br.readLine());
		
		out.write("mail from: <aaa@flx.com>\r\n".getBytes());
		System.out.println(br.readLine());
		
		
		out.write("rcpt to: <bbb@flx.com>\r\n".getBytes());
		System.out.println(br.readLine());
		
		out.write("data\r\n".getBytes());
		System.out.println(br.readLine());
		
		out.write("from:<aaa@flx.com>\r\nto:<bbb@flx.com>\r\nsubject:test\r\n\r\naaaaaaaaa\r\n".getBytes());
		out.write(".\r\n".getBytes());
		System.out.println(br.readLine());
		
		out.write("quit\r\n".getBytes());
		System.out.println(br.readLine());
		
		br.close();
		out.close();
		socket.close();
		
	}

}