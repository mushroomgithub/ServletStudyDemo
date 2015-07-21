package cn.lnu.eclipse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Test;

public class Demo4 {

	/**
	 * java增强for循环
	 */

	@Test
	public void test1() {
		int atrr[] = { 1, 2, 3, 4, 5 };
		for (int num : atrr) {
			System.out.print(" " + num);
		}
		System.out.println();
	}

	@Test
	public void test2() {
		List list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		for (Object obj : list) {
			int i = (Integer) obj;
			System.out.println(i);
		}
	}

	// map集合不可以直接迭代，要想迭代map集合有以下方式：
	@Test
	public void test3() {
		Map map = new LinkedHashMap();
		map.put("1", "aa");
		map.put("2", "bb");
		map.put("3", "cc");
		map.put("4", "dd");
		System.out.println("-----------传统迭代map集合方式一--------------------");
		// 传统方式1迭代map,先将map集合的key转化为一个set集合
		Set set = map.keySet();

		Iterator it = set.iterator();
		while (it.hasNext()) {// 迭代出的每个元素都是一个原map集合中的key
			String key = (String) it.next();
			String value = (String) map.get(key);
			System.out.println(key + ":" + value);
		}
		System.out.println("-----------传统迭代map集合方式二--------------------");
		// 传统方式2迭代map
		Set set2 = map.entrySet();// 每个元素是一个个原来map集合中的键值对(Map.entry)
		Iterator it2 = set2.iterator();
		while (it2.hasNext()) {
			Map.Entry entry = (Entry) it2.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			System.out.println(key + ":" + value);
		}

	}

	@Test
	public void test4() {
		Map map = new LinkedHashMap();
		map.put("1", "aa");
		map.put("2", "bb");
		map.put("3", "cc");
		map.put("4", "dd");

		System.out
				.println("-----------使用增强for循环迭代map集合元素的第一种方式(对应传统方式一)--------------------");

		for (Object obj : map.keySet()) {// 迭代出的元素都是一个个原map集合中的key
			String key = (String) obj;
			String value = (String) map.get(key);
			System.out.println(key + ":" + value);
		}

		System.out
				.println("-----------使用增强for循环迭代map集合元素的第二种方式(对应传统方式二)--------------------");

		for (Object obj : map.entrySet()) {// 迭代出来的每个元素都是原来map集合中的键值对(Map.entry)
			Map.Entry entry = (Entry) obj;
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			System.out.println(key + ":" + value);
		}
	}

	// 使用增强for循环需要注意的几个问题：增强for循环只适合取数据,如需要修改数组或集合中的数据，需要使用传统方式
	@Test
	public void test5() {
		int atrr[] = { 1, 2, 3, 4, 5 };
		for (int i : atrr) {
			i = 10;
		}
		System.out.println(atrr[0]);//1
		System.out.println(atrr[1]);//2
		System.out.println(atrr[2]);//3
		System.out.println(atrr[3]);//4
		System.out.println(atrr[4]);//5
		
		System.out.println("--------------------------");
		List list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		for (Object obj : list) {
			obj=9;
		}
		
		System.out.println(list.get(0));//1
	}

}
