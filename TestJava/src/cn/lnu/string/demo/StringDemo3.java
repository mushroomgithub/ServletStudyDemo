package cn.lnu.string.demo;

public class StringDemo3 {

	/**
	 * 求两个字符串的最大相同子串
	 * "sadfcctvghjkl"
	 * "zxcctvcv"
	 * 
	 * 思路：
	 * 1，以短的字符串为主，到长的字符串中判断是否存在，如果存在，已找到并返回
	 * 2，如果没有找到，将短的字符串的长度递减获取子串继续在长的串中查找，只要找到就结束，
	 * 3，没有找到，说明没有相同的
	 */
	public static void main(String[] args) {
		String str="sadfcctvghjkl";
		String str2="zxcctvcv";
		String maxSubString=getMaxSubString(str,str2);
		System.out.println("最大子串是："+maxSubString);
	}

	public static String getMaxSubString(String s1, String s2) {
		String longStr,shortStr;
		longStr=s1.length()>s2.length()?s1:s2;
		shortStr=s1.equals(longStr)?s2:s1;
		//对短的字符串操作，从短串中取子串，到长字符串中判断，是否存在。
		for(int x=0;x<shortStr.length();x++){
			for(int y=0,z=shortStr.length()-x;z<=shortStr.length();y++,z++){
				//根据y，z获取子串
				String temp=shortStr.substring(y, z);
				//System.out.println(temp);
				if(longStr.contains(temp)){
					return temp;
				}
			}
		}
		return null;
	}

}
