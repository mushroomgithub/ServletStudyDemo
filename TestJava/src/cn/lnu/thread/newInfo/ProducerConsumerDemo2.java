package cn.lnu.thread.newInfo;

public class ProducerConsumerDemo2 {

	/**JDK1.5
	 *解决了多生产者多消费者的效率低下问题
	 *使用了JDK1.5 java.util.concurrent.locks包中的对象。
	 *Lock接口：它的出现比synchronized有更多的操作。
	 *			lock():获取锁
	 *			unlock():释放锁
	 *同步代码块或者同步函数的锁操作是隐式的。
	 *JDK1.5 Lock接口，按照面向对象的思想，将锁单独封装成了一个对象，并提供了对锁的显示操作。
	 *Lock接口就是同步的替代
	 *1，将程序中的1.4同步更换为lock接口的形式
	 *2,替换完之后运行失败了，因为wait没有了同步区域，没有了所属的同步锁。
	 *同步升级了，其中锁已经不是在任意对象，而是Lock类型的对象。
	 *那么和任意对象绑定的监视器方法，是不是也升级了，有了专门和Lock类型锁的绑定的监视器方法吗，从而代替之前的wait，motify，notifyAll
	 *查阅api,Condition接口替代了Object中的监视器方法。
	 *以前监视器方法封装在每个对象中，现在将监视器方法封装到了Condition对象中
	 *方法名为：await,signal signalAll
	 *
	 *监视器对象Condition如何与Lock绑定呢
	 *可以通过Lock接口的newCondition()方法完成
	 *但是问题依旧，一样唤醒了本方，效率依旧低下
	 *可以创建两个lock锁的监视器，生产者监视器，消费者监视器，用于唤醒对方，这样不会唤醒本方从而解决了效率问题。
	 */
	public static void main(String[] args) {
		//1，创建资源
		Resource2 r2=new Resource2();
		//2,创建生产者与消费者任务
		Producer2 p=new Producer2(r2);
		Consumer2 c=new Consumer2(r2);
		//3，创建线程
		Thread t1=new Thread(p);
		Thread t2=new Thread(p);
		Thread t3=new Thread(c);
		Thread t4=new Thread(c);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();

	}

}
