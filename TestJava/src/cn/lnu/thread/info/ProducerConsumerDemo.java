package cn.lnu.thread.info;

public class ProducerConsumerDemo {

	/**
	 * 单生产者，单消费者问题
	 * 多线程通信,同步+等待/唤醒机制
	 * 通过同步可以解决没有生产就消费的问题，
	 * 但是却出现了连续的生产没有消费的情况，和需求生产一个，消费一个的情况不符，
	 * 使用了等待/唤醒机制，必须用在同步中，脱离同步使用这个机制没有意义
	 * wait()：该方法可以让线程处于冻结状态，并且将线程临时存储到线程池中
	 * notify()/notifyAll()//唤醒指定线程池中的任意一个线程/或者唤醒指定线程池中的所有线程
	 * 
	 * 这些方法必须使用在同步中，因为他们本身就是用来操作同步锁上的线程状态的
	 * 在使用着写方法时，必须标示它们所属于的锁，标示方式是： 锁对象.wait()   锁对象.notify()  锁对象.notifyAll()
	 * 
	 * 相同锁的notify()，才能唤醒相同锁的wait()
	 */
	public static void main(String[] args) {
		//1，创建资源
		Resource r=new Resource();
		//2,创建生产者与消费者任务
		Producer p=new Producer(r);
		Consumer c=new Consumer(r);
		//3，创建线程
		Thread t1=new Thread(p);
		Thread t2=new Thread(c);
		
		t1.start();
		t2.start();

	}

}
