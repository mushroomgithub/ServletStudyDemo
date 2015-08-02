package cn.lnu.string.demo;

import java.util.Arrays;

public class StringDemo4 {

	/**
	 * 对字符串中字符进行自然顺序排序
	 * 思路：
	 * 1，要排序
	 * 2，怎么能把字符串转成数组呢？
	 * 3，到字符串中找方法
	 * 4，排序
	 * 5，将排序后的数组变成字符串
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str="jbsahhavb";
		String sortString=sortChar(str);
		System.out.print(sortString);
	}

	public static String sortChar(String str) {
		//将字符串转成字符数组
		char[] chs=str.toCharArray();
		//对数组进行排序
		sort(chs);
		//将字符数组转成字符串
		return new String(chs);
	}

	public static void sort(char[] chs) {
		// TODO Auto-generated method stub
		Arrays.sort(chs);
	}

}
