package cn.lnu.thread.singleton;
//懒汉式单例设计模式，延迟加载模式，面试时问这种比较多
/*
 * 	在多线程并发访问时，会出现线程安全问题，
 * 加了同步就可以解决问题，无论是同步函数，还是同步代码块都行，但是这么做效率低了，因为每次开启一个线程都需要去判断锁，浪费资源，而事实上只需要第一次访问时才需要判断锁
 * 
 * 那么如何解决效率低的问题？
 * 可以通过if对单例对象的双重判断的形式，即双校验锁
 * */
public class Singleton2 {
	private static Singleton2 instance=null;
	private Singleton2(){
		
	}
	
	/*public static Singleton2 getInstance(){
		if(instance==null){
			instance=new Singleton2();
		}
		return instance;
	}*/
	
	/*public static synchronized Singleton2 getInstance(){
	if(instance==null){
		instance=new Singleton2();
	}
	return instance;
}*/
	public static Singleton2 getInstance(){
		
		if(instance==null){
			
			synchronized(Singleton2.class){//静态方法使用类字节码文件对象作为锁
				if(instance==null){
					instance=new Singleton2();
				}
			}
		}
		return instance;
	}
	
	
}
