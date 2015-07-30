package cn.lnu.scanner.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Demo1 {

	/**
	 * 测试使用Java Scanner从控制台输入测试数据
	 */
	public static void main(String[] args) {
		String str1="Hello";
		String str2="Hello";
		System.out.println(str1==str2);//true
		
		String str3=new String("Hello");
		System.out.println(str1==str3);//false
		
		String str4=new String("Hello");
		System.out.println(str3==str4);//false
		
		final int a3;//不初始化，也是可以的，但是必须在使用a3之前对其初始化，否则会报错，
		a3=4;
		System.out.println(a3);
		int a=10;
		int b=10;
		
		System.out.println(a==b);//true
		int a1=new Integer(10);
		int b1=new Integer(10);
		System.out.println(a1==b1);//true
		
		System.out.println("------------------------------------");
		try {
			Scanner s=new Scanner(new File("c:\\1.txt"));
			while(s.hasNext()){
				System.out.println(s.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("文件读取失败！");
		}
		System.out.println("------------------------------------");
		String str = "1.2 s.4 5 6.7 8 9";
		Scanner scanner = new Scanner(str);
		//token以.分割  
		scanner.useDelimiter("\\.");
		while (scanner.hasNext()) {
			System.out.println(scanner.next());
		}   
		
		System.out.println("------------------------------------");
		 Scanner s = new Scanner(System.in); 
		 s.useDelimiter(" ");
         System.out.println("请输入字符串："); 
         String s1,s2;
         while (true) { 
        	 	s1=s.nextLine();
        	 	s2=s.nextLine();
                String line = s.nextLine(); 
                if (line.equals("exit")) break; 
         } 
         System.out.println(s1+s2);
	}

}
