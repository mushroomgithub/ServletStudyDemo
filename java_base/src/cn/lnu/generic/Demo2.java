package cn.lnu.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

//泛型集合数据的存取，使用泛型之后，就可以避免从泛型集合中取数据时的类型强制转换
public class Demo2 {
	
	@Test
	public void test1(){
		
		List<String> list=new ArrayList<String>();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		list.add("dd");
		System.out.println("-----------传统方式迭代集合数据------------");
		//传统方式获取集合数据
		Iterator<String> it=list.iterator();//告诉iterator处理的类型是String类型,得到集合的迭代器
		while(it.hasNext()){
			String str=it.next();
			System.out.println(str);
		}
		System.out.println("-----------增强for迭代集合数据------------");
		//使用增强for
		for(String str1:list){
			System.out.println(str1);
		}
	}
	
	//HashMap是根据关键字的hash值来确定键值对存放位置，所以不是有序存放，而LinkedHashMap则是使用链表的方式存放元素所以是有序存放
	@Test
	public void test2(){
		
		Map<Integer,String> map=new LinkedHashMap<Integer,String>();//创建一个变量去指向一个map集合，这个变量通过<Integer,String>说明后面new出的集合的处理类型的关键字为int型，值为String类型，类型指定的类型必须是个对象，不能是基本类型
		map.put(1, "aa");
		map.put(2, "bb");
		map.put(3, "cc");
		map.put(4, "dd");
		System.out.println("-----------传统方式的Map.keySet迭代集合数据------------");
		//传统方式1(使用map.ketSet迭代)获取集合数据
		Set<Integer> set=map.keySet();//告诉iterator处理的key类型是Integer类型,得到集合的迭代器
		Iterator<Integer> it=set.iterator();
		while(it.hasNext()){
			int key=it.next();
			String value=map.get(key);
			System.out.println(key+":"+value);
		}
	}
	
	@Test
	public void test3(){
		
		Map<Integer,String> map=new LinkedHashMap<Integer,String>();//创建一个变量去指向一个map集合，这个变量通过<Integer,String>说明后面new出的集合的处理类型的关键字为int型，值为String类型，类型指定的类型必须是个对象，不能是基本类型
		map.put(1, "aa");
		map.put(2, "bb");
		map.put(3, "cc");
		map.put(4, "dd");
		System.out.println("-----------传统方式的Map.entrySet迭代集合数据------------");
		//传统方式2(使用map.entrySet迭代)获取集合数据
		Set<Map.Entry<Integer, String>> set=map.entrySet();//先将map.entrySet返回的一个个entry保存到一个Set集合中，便于下面的迭代
		Iterator<Map.Entry<Integer, String>> it=set.iterator();//告诉迭代器取出的每个元素都是一个个Map.Entry类型的键值对。键的类型是Integer，值的类型是String
		while(it.hasNext()){
			Map.Entry<Integer, String> entry=it.next();
			int key=entry.getKey();
			String value=entry.getValue();
			System.out.println(key+":"+value);
		}
	}
	
	//使用增强for循环迭代map集合中的数据（重点）
	@Test
	public void test4(){
		
		Map<Integer,String> map=new LinkedHashMap<Integer,String>();//创建一个变量去指向一个map集合，这个变量通过<Integer,String>说明后面new出的集合的处理类型的关键字为int型，值为String类型，类型指定的类型必须是个对象，不能是基本类型
		map.put(1, "aa");
		map.put(2, "bb");
		map.put(3, "cc");
		map.put(4, "dd");
		System.out.println("-----------增强for循环迭代集合数据------------");
		
		for(Map.Entry<Integer, String>entry:map.entrySet()){//迭代出的是一个Map.Entry,entry(条目)的键值对类型对应是Integer和String类型
			int key=entry.getKey();
			String value=entry.getValue();
			System.out.println(key+":"+value);
		}
	}
	
	
	//使用泛型时需要注意的问题
	@Test
	public void test5(){
		//使用泛型时，如果两边都使用泛型，则两边声明的要处理的数据类型必须一样
		//ArrayList<Object> list=new ArrayList<String>(); //错误写法
		//ArrayList<String> list=new ArrayList<Object>(); //错误写法
		
		//使用泛型时，如果只有一边使用了泛型，则就不会出现问题，这是sun公司为考虑兼容性故意这么设计的
		ArrayList<String> list=new ArrayList();//正确,为了考虑到低级程序员调用高级程序员使用泛型编写的函数的兼容性
		ArrayList list1=new ArrayList<String>();//正确，sun公司为了考虑到jdk1.5之前没有泛型，之后版本又有泛型的兼容性
		
		
	}
}
