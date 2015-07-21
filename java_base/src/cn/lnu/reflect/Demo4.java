package cn.lnu.reflect;

import java.lang.reflect.Field;

import org.junit.Test;

//反射类的字段，为其封装一些数据
public class Demo4 {
	
	//反射字段：public String name="mushroom";
	@Test
	public void test1() throws Exception{
		Class clazz=Class.forName("cn.lnu.reflect.Person");
		Person p=(Person) clazz.newInstance();
		Field f=clazz.getField("name");
		//获取字段封装的数据值
		Object value=f.get(p);//获取指定反射字段封装的数据，需要指定封装这个字段的类的对象，表明是从哪个对象的这个字段上取数据
		Class type=f.getType();//获取反射字段的数据类型
		//System.out.println(type);//class java.lang.String
		if(type.equals(String.class)){
			String svalue=(String) value;
			System.out.println(svalue);
		}
		
		//设置字段的值
		f.set(p, "蘑菇");
		System.out.println(p.name);//蘑菇
	}
	
	//反射字段：private int password;
	@Test
	public void test2() throws Exception{
		Class clazz=Class.forName("cn.lnu.reflect.Person");
		Person p=(Person) clazz.newInstance();
		Field f=clazz.getDeclaredField("password");
		f.setAccessible(true);//暴力打开这个字段的访问属性，之后就想操作公有属性一样可以随意操作这个字段
		
		System.out.println(f.get(p));//123
	}
	
	//反射字段：private static int age=26;
	@Test
	public void test3() throws Exception{
		Class clazz=Class.forName("cn.lnu.reflect.Person");
		Person p=(Person) clazz.newInstance();
		Field f=clazz.getDeclaredField("age");
		f.setAccessible(true);//暴力打开这个字段的访问属性，之后就想操作公有属性一样可以随意操作这个字段
		
		System.out.println(f.get(p));//26
	}
}
