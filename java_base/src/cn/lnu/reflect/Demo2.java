package cn.lnu.reflect;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

//解剖类的构造函数，创建类的对象
public class Demo2 {

	/**
	 * 反射类的构造函数创建对象
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	//反射构造函数：public Person(){
	@Test
	public void test1() throws Exception{
		Class clazz=Class.forName("cn.lnu.reflect.Person");//加载Person类字节码到内存，产生一个字节码对象
		Constructor c=clazz.getConstructor(null);//这个方法接受一个可变参数，由于现在要解析无参构造函数，这里传递null，返回一个代表无参构造函数的对象
		
		Person p=(Person) c.newInstance(null);//通过一个构造函数对象的方法创建person类的对象
		System.out.println(p.name);
	}
	
	//反射构造函数：public Person(String name){
	@Test
	public void test2() throws Exception{
		Class clazz=Class.forName("cn.lnu.reflect.Person");//加载Person类字节码到内存，产生一个字节码对象
		Constructor c=clazz.getConstructor(String.class);//这个方法接受一个可变参数，由于现在要解析带有一个String类型参数的构造函数，这里传递String.class
		
		Person p=(Person) c.newInstance("mogu");//通过一个构造函数对象的方法创建person类的对象
		System.out.println(p.name);
	}
	
	//反射构造函数：public Person(String name,int password){
	@Test
	public void test3() throws Exception{
		Class clazz=Class.forName("cn.lnu.reflect.Person");//加载Person类字节码到内存，产生一个字节码对象
		Constructor c=clazz.getConstructor(String.class,int.class);//这个方法接受一个可变参数，由于现在要解析带有一个String类型参数和一个整形的构造函数，这里传递String.class和int.class
		
		Person p=(Person) c.newInstance("mogu",123);//通过一个构造函数对象的方法创建person类的对象
		System.out.println(p.name);
	}
	
	//反射构造函数：private Person(List list){
	@Test
	public void test4() throws Exception{
		Class clazz=Class.forName("cn.lnu.reflect.Person");//加载Person类字节码到内存，产生一个字节码对象
		Constructor c=clazz.getDeclaredConstructor(List.class);
		c.setAccessible(true);//不管构造函数是什么访问属性，都给它打开成public，进行暴力反射
		
		List list=new ArrayList();
		Person p=(Person) c.newInstance(list);//通过一个构造函数对象的方法创建person类的对象
		System.out.println(p.name);
	}
	
	//创建对象的另外一种途径
	@Test
	public void test5() throws Exception{
		Class clazz=Class.forName("cn.lnu.reflect.Person");//加载Person类字节码到内存，产生一个字节码对象
		Person p=(Person) clazz.newInstance();//内部是反射person类的无参构造函数，使用这种方式，被反射类必须提供一个无参构造函数，等价于上面的test1方法先反射出无参构造函数再去创建对象的方法
		System.out.println(p.name);
	}
}
