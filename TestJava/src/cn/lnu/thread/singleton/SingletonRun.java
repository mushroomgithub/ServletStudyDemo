package cn.lnu.thread.singleton;

public class SingletonRun implements Runnable {

	public void run() {
		//singleton1.getInstance();//会被多线程并发访问，这种事饿汉式单例，在singleton1类一加载时就被创建，因此在多线程访问过程中不会产生线程安全问题
		Singleton2.getInstance();//懒汉式单例，在需要的时候才创建实例，会有线程安全问题，可以使用双校验锁机制在实现线程安全的同时，降低因判断同步锁造成的执行效率低下问题
	}

}
