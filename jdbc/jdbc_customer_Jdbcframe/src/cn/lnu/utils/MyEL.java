package cn.lnu.utils;
//实现从数据库中取出显示的字段过长，以省略号表示后续部分
public class MyEL {//定义完自己的el函数之后，在WEB-INF下创建一个lnu.tld文件
	public static String sub(String str){
		if(str.length()>10){
			return str.substring(0, 10)+"...";
		}
		return str;
	}
}
