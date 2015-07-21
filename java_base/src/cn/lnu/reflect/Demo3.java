package cn.lnu.reflect;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.junit.Test;

//反射类的方法
public class Demo3 {
	
	
	//反射类的方法:public void aa(){
	@Test
	public void test1() throws Exception{
		//Person p=new Person();
		Class clazz=Class.forName("cn.lnu.reflect.Person");
		Person p=(Person) clazz.newInstance();
		Method  method=clazz.getMethod("aa", null);
		method.invoke(p,null);//反射出方法后，调用时需要指定调用这个函数的对象是哪个，以及这个方法需要什么参数
	}
	
	//反射类的方法:public void aa(String name,int password){
	@Test
	public void test2() throws Exception{
		//Person p=new Person();
		Class clazz=Class.forName("cn.lnu.reflect.Person");
		Person p=(Person) clazz.newInstance();
		
		Method  method=clazz.getMethod("aa", String.class,int.class);//需要指定要反射方法的参数类型
		method.invoke(p,"mushroom",123456);//反射出方法后，调用时需要指定调用这个函数的对象是哪个，以及这个方法需要什么参数
	}
	
	
	//反射类的方法:public Class[] aa(String name,int[] password){
	@Test
	public void test3() throws Exception{
		//Person p=new Person();
		Class clazz=Class.forName("cn.lnu.reflect.Person");
		Person p=(Person) clazz.newInstance();
		
		Method  method=clazz.getMethod("aa", String.class,int[].class);//需要指定要反射方法的参数类型
		Class clazz1[]=(Class[]) method.invoke(p,"mushroom",new int[]{1,2,3});//反射出方法后，调用时需要指定调用这个函数的对象是哪个，以及这个方法需要什么参数
		System.out.println(clazz1[0]);
	}
	
	//反射类的方法:private void aa(InputStream in){
	@Test
	public void test4() throws Exception{
		//Person p=new Person();
		Class clazz=Class.forName("cn.lnu.reflect.Person");
		Person p=(Person) clazz.newInstance();
		
		Method  method=clazz.getDeclaredMethod("aa", InputStream.class);//需要指定要反射方法的参数类型，私有方法需要使用getDeclaredMethod，病设置暴力打开私有属性
		method.setAccessible(true);
		method.invoke(p,new FileInputStream("c:\\1.txt"));//反射出方法后，调用时需要指定调用这个函数的对象是哪个，以及这个方法需要什么参数
	}
	
	//反射类的方法:public static void aa(int num){
	@Test
	public void test5() throws Exception{
		Class clazz=Class.forName("cn.lnu.reflect.Person");	
		Method  method=clazz.getMethod("aa", int.class);//需要指定要反射方法的参数类型
		method.invoke(null,2);//静态反射出方法后，调用时不需要指定调用这个函数的对象是哪个，传null就行，当然传递个对象更可以，其次还有指定这个方法需要什么参数
	}
	
	//反射类的Main方法:public static void main(String[] args){
	@Test
	public void test6() throws Exception{
		Class clazz=Class.forName("cn.lnu.reflect.Person");	
		Method  method=clazz.getMethod("main", String[].class);//需要指定要反射方法的参数类型
		//method.invoke(null,new Object[]{new String[]{"hello","world"}});//静态反射出方法后，调用时不需要指定调用这个函数的对象是哪个，传null就行，当然传递个对象更可以，其次还有指定这个方法需要什么参数
		method.invoke(null,(Object)new String[]{"hello","world"});//静态反射出方法后，调用时不需要指定调用这个函数的对象是哪个，传null就行，当然传递个对象更可以，其次还有指定这个方法需要什么参数
	}
}
