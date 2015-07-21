package cn.lnu.enumeration2;

import org.junit.Test;

//如何定义带抽象方法的枚举
enum Grade{ //使用enum关键字定义 枚举类,A 100-90 优  B 89-80 良  C 79-70 一般  D 69-60 差   E 59-0 不及格
	A("100-90"){//枚举类的A对象
		public String localeValue(){
			return "优";
		}
	}
	,B("89-80"){
		public String localeValue(){
			return "良";
		}
	}
	,C("79-70"){
		public String localeValue(){
			return "一般";
		}
	}
	,D("69-60"){
		public String localeValue(){
			return "差";
		}
	}
	,E("59-0"){
		public String localeValue(){
			return "不及格";
		}
	}; //Object
	
	private String value;
	private Grade(String value){//枚举类的构造方法必须是私有的，避免别人在给print函数传参的时候，人为new出别的枚举对象之外的其他值，这时，就不是枚举类了
		this.value=value;
	}
	public String getValue(){
		return this.value;
	}
	
	public abstract String localeValue();//定义枚举的抽象方法，如果枚举带有抽象方法，那么这个枚举类每个对象的时候就必须实现这个方法
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
public class Demo2 {
	
	@Test
	public void test(){//通过定义枚举类可以限定print函数的参数只能接收有限的几个参数这里是A,B,C,D,E
		print(Grade.D);//B
	}
	
	public void print(Grade g){
		String value=g.getValue();
		System.out.println(value+":"+g.localeValue());
	}
	
}
