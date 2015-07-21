package cn.lnu.generic;

import java.util.ArrayList;
import java.util.List;

public class Demo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		List list=new ArrayList();
		list.add("abc");
		
		//Integer i=(Integer) list.get(0);//Exception in thread "main" java.lang.ClassCastException: java.lang.String
		
		List<String> list2=new ArrayList<String>();
		list2.add("mushroom");
		
		String str=list2.get(0);
		System.out.println(str);//mushroom
	}

}
