package cn.lnu.enumeration;

import org.junit.Test;

import cn.lnu.enumeration.Grade;

//如何定义枚举的构造函数，方法和字段，去封装更多的信息
enum Grade{ //使用enum关键字定义 枚举类,A 100-90 B 89-80 C 79-70 D 69-60 E 59-0 //用于定义几个枚举值(Grade类型的)
	A("100-90"),B("89-80"),C("79-70"),D("69-60"),E("59-0"); //Object 
	
	private String value;
	private Grade(String value){//枚举类的构造方法必须是私有的
		this.value=value;
	}
	public String getValue(){
		return this.value;
	}
}

//等价于下面类的定义
/*class Grade{
	private Grade(){
		
	}
	
	public static final Grade A=new Grade();
	public static final Grade B=new Grade();
	public static final Grade C=new Grade();
	public static final Grade D=new Grade();
	public static final Grade E=new Grade();
}*/
public class Demo1 {
	
	@Test
	public void test(){//通过定义枚举类可以限定print函数的参数只能接收有限的几个参数这里是A,B,C,D,E
		print(Grade.D);//B
	}
	
	public void print(Grade g){
		String value=g.getValue();
		System.out.println(value);
	}
	
	
	//测试枚举的常用方法
	@Test
	public void test2(){
		System.out.println(Grade.C.name());//C
		System.out.println(Grade.C.ordinal());//2 返回枚举类中D对象声明的位置(顺序)
		
		String str="B";
		//Grade g=Grade.valueOf(Grade.class, str);//B 把一个字符串转为一个枚举值
		Grade g=Grade.valueOf(str);//B
		System.out.println(g);
	
		System.out.println("---------------------");
		Grade gs[]=Grade.values();//返回枚举类中枚举对象的一个数组，可以知道这个枚举类中有几个枚举值
		for(Grade g1:gs){
			System.out.println(g1);
		}
	}
}
