package cn.lnu.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListDemo {
	
	public static void main(String[] args){
		List list=new ArrayList();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		
		//Iterator it=list.iterator();//大小为4
		//为避免并发修改异常，我们这里使用迭代器的孩子，它具有增删改查的功能，在迭代元素的同时，增删改查不会出现并发修改异常问题
		ListIterator it=list.listIterator();
		while(it.hasNext()){
			String str=(String) it.next();
			//list.add("eee");//java.util.ConcurrentModificationException集合的并发修改异常，为了避免这种异常，我们可以调用迭代器的方法完成添加元素的操作
			it.add("eee");//使用子迭代器的方法完成增删改查问题，这样迭代器会知道你的增删改查操作，不会再抛出并发修改异常
		}
	}
}	
