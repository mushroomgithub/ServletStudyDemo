package cn.lnu.eclipse;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Demo5 {

	/**
	 * java可变参数
	 */
	
	@Test
	public void testSum(){	
		
		//sum(1,2,3,4,5,6,7);
		
		//也可以传递一个数组
		int arr[]={1,2,3,4,5,6,7};
		sum(arr);
	}
	
	public void sum(int ...nums){
		//可变参数可把它看成是数组
		int sum=0;
		for(int i:nums){
			sum+=i;
		}
		System.out.println(sum);
	}
	
	
	@Test
	public void testAa(){
		sum(1,2,3,4,5,6,7);
	}
	//可变参数需要注意的问题:public void aa(int ...nums,int x){这个方式定义可变参数不对
	
	public void aa(int x,int ...nums){
		
	}
	
	@Test
	public void bb(){
		List list=Arrays.asList("1","2","3");
		System.out.println(list);//[1, 2, 3]
		
		String arr[]={"1","2","4","5"};
		list=Arrays.asList(arr);
		System.out.println(list);//[1, 2, 4, 5]
		
		int nums[]={1,2,3,4,5,6};//Arrays.asList接收的可变参数类型是一个个对象，java中基本类型数组整体视为有一个对象
		list=Arrays.asList(nums);
		System.out.println(list);//[[I@4e79f1]
		
		Integer nums2[]={1,2,3,4,5,6};
		list=Arrays.asList(nums2);
		System.out.println(list);//[1, 2, 3, 4, 5, 6]
	}
	
}
