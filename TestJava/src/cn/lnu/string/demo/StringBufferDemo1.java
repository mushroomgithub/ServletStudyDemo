package cn.lnu.string.demo;

public class StringBufferDemo1 {

	/**
	 * StringBuffer:字符串缓冲区
	 * 作为一个字符容器
	 * 特点：
	 * 1，长度可以变化
	 * 2，可以对内容通过指定的方法进行修改
	 * 3，容器对象一般都会具备对容器中的元素进行操作的功能，比如说增删改查
	 * 4，缓冲区可以存储不同类型的数据
	 * 5,最终缓冲区存储完的数据都会变成字符串，他只是起到临时存储的作用
	 */
	public static void main(String[] args) {
		String str="a"+4+'c';
		//在内存中的过程：1，创建一个字符串缓冲区，2把将要组成字符串的元素先存储起来，3，最后将缓冲区填充数据变成字符串
		str=new StringBuffer().append("a").append(4).append('c').toString();
		
		
	}

}
