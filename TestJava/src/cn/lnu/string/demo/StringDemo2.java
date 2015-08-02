package cn.lnu.string.demo;

public class StringDemo2 {

	/**
	 * 子串在整串中出现的次数。"nbafhafnbajfknbajfal';hnbajfanba"
	 * 
	 * 思路：
	 * 1，需要计数
	 * 2，找到一个nba就计数
	 * 3，如何在字符串中查找子字符串
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="nbafhafnbajfknbajfal';hnbajfanba";
		String key="nba";
		int count=getKeyCount(str,key);
		System.out.println("key count="+count);
	}

	public static int getKeyCount(String str, String key) {
		int count=0;
		int pos=0;
		while((pos=str.indexOf(key,pos))!=-1){
			count++;
			//每次找完一次，都要确定下次要找的起始位置，上次位置+key的长度
			pos+=key.length();
		}
		return count;
	}

}
