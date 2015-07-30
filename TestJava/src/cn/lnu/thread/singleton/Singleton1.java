package cn.lnu.thread.singleton;
//单例模式的饿汉式，实际开发中用这种方式比较多
public class Singleton1 {
	
	private static final Singleton1 instance=new Singleton1();
	private Singleton1(){}
	
	public static Singleton1 getInstance(){
		return instance;
	}
}
