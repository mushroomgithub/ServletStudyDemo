package cn.lnu.reflect;

public class Demo1 {

	/**
	 * java反射，加载类，返回指定类的字节码Class对象，包含了该类的所有信息
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		//三种方式加载指定类返回指定封装了指定类全部信息的字节码对象
		//1.
		Class clazz=Class.forName("cn.lnu.reflect.Person");//使用反射加载person类，返回封装该类信息的字节码对象，给定的参数必须是类的完整名称，相当于把硬盘中Person类的字节码加载到内存中
		//System.out.println(clazz);//class cn.lnu.reflect.Person
		//2.
		Class clazz1=new Person().getClass();
		//System.out.println(clazz1);//class cn.lnu.reflect.Person
		//3.
		Class clazz2=Person.class;
		System.out.println(clazz2);//class cn.lnu.reflect.Person
	}

}
