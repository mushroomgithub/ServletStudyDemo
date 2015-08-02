package cn.lnu.string.demo;

public class StringBufferDemo3 {

	/**
	 * StringBuffer:字符串缓冲区
	 * 缓冲区可以对数据进行临时存储
	 *  了解缓冲区的常见方法
	 *  添加元素
	 *  SttingBuffer append(各种类型数据);追加
	 *  StringBuffer insert（index,各种类型的数据）;指定位置添加
	 *  
	 */
	public static void main(String[] args) {
		//1，创建一个缓冲区对象
		StringBuffer sb=new StringBuffer();
		//2，追加一个字符串
		sb.append("abc");
		//3，插入一个boolean值，false
		sb.insert(1, false);//afalsebc---8
		System.out.println(sb+"---"+sb.length());//print方法会将所有要打印的数据先转成字符串再输出；对于对象会自动调用toString方法
		//4，删除字符
		//sb.deleteCharAt(0);
		sb.delete(1, 4);//包含头不包含尾
		System.out.println(sb+"---"+sb.length());//asebc---5
		
		//5，修改字符
		sb.replace(1, 5, "what");
		
		System.out.println(sb+"---"+sb.length());//awhat---5
		sb.reverse();
		System.out.println(sb+"---"+sb.length());//tahwa---5
		
		/*
		 * 字符串缓冲区中维护了一个“可变长度的数组”
		 * 解释：其实就是超出内部数组长度后，新建数组的长度要是原数组长度的1.5或者1.75等倍数，
		 * 并将原数组的数据复制到新数组中，并将新的元素也添加到新数组中。
		 * */
	}

}
