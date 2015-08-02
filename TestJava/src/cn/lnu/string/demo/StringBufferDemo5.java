package cn.lnu.string.demo;

public class StringBufferDemo5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer buf1=new StringBuffer("hello");
		StringBuffer buf2=new StringBuffer("java");
		 test(buf1,buf2);
		 System.out.println(buf1+"-----"+buf2);//hello-----javahello主要考察String与StringBuffer之间的区别，结合StringDemo1比较
	}
	
	public static void test(StringBuffer s1,StringBuffer s2){
		s2.append(s1);
		s1=s2;
		System.out.println(s1+"-----"+s2);//javahello-----javahello
	}
}
