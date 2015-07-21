package cn.lnu.generic;
//如何在类上声明泛型
public class Demo2<T>{//类上声明的泛型只能作用于一般方法，静态方法要使用泛型时，需要自己先声明一个自己的泛型(声明位置在static关键字后面)，然后才能使用
	public void add(T a){
		
	}
	public void delete(T a){
		
	}
	
	public static <T> void update(T a){
		
	}
}
