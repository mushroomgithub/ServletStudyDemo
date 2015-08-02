package cn.lnu.string.demo;

public class StringDemo1 {

	/**
	 * String²Ù×÷
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1="hello";
		String s2="java";
		 test(s1,s2);
		 System.out.println(s1+"-----"+s2);//hello-----java
	}
	
	public static void test(String s1,String s2){
		//s2=s2.replace('a', 'o');
		s2+=s1;
		s1=s2;
		//System.out.println(s1+"-----"+s2);//jovo-----jovo
		System.out.println(s1+"-----"+s2);//javahello-----javahello
	}
}
