package cn.lnu.eclipse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Demo3 {

	/**
	 * java自动装箱和拆箱
	 * 自动装箱：指开发人员可以把一个基本数据类型直接赋值给对应的包装类
	 * 自动拆箱：指开发人员可以把一个包装类对象直接赋值给对应的基本数据类型
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//jdk5.0以上支持自动装箱和拆箱
		Integer i=1;//自动装箱，等价于Integer i=new Integer(1);,即自动将基本类型数据装箱成一个对象
		
		int j=i;//自动拆箱操作，即java内部自动将一个对象封装的基本类型数据赋给对应的基本类型变量
		
		
		//典型应用：
		List list=new ArrayList();
		list.add(1);//add函数默认参数是一个对象类型，有了自动装箱操作之后，可以将基本类型直接使用add函数添加到结合中，java会自动将其装箱成对象
		list.add(2);//《==》list.add(new Integer(1))
		list.add(3);
		
		Iterator it=list.iterator();
		while(it.hasNext()){
			int k= (Integer)it.next();//自动拆箱技术将返回的integer对象中的数据自动拆箱成基本类型
		}
	}
	

}
