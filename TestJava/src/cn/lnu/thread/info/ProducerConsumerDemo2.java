package cn.lnu.thread.info;

public class ProducerConsumerDemo2 {

	/**JDK1.4
	 * 多生产者，多消费者问题,会有连续的消费或者连续的生产问题,：使用循环判断标记，最后使用notifyAll()全部唤醒
	 * 多线程通信,同步+等待/唤醒机制
	 * 
	 * 问题1,：
	 * 重复生产重复消费。
	 * 原因:经过复杂的分析，发现被唤醒的吸纳长没有再去判断标记flag就开始工作(生产或者消费)了。
	 * 导致了重复的生产和消费的产生。
	 * 解决：那就是被唤醒的线程必须判断标记，使用while循环判断标记
	 * 
	 * 问题2:
	 * 死锁了，所有的线程都处于冻结状态
	 * 原因:本线程在唤醒时，又一次唤醒了本方线程，而本方线程循环判断标记flag,又继续等待。从而导致所有的线程都等待了
	 * 
	 * 解决：希望本方如果唤醒对方线程就可以解决
	 * 可以使用notifyAll()方法
	 * 那不是全唤醒了吗？是的，既有本方线程，又有对方线程，但是本方醒后，会判断标记继续等待，这样对方就有线程可以执行了.
	 * 
	 * 本程序已经实现了多生产-多消费
	 * 但是有些小问题，效率有点低，因为notifyAll()，也唤醒了本方，做了不必要的判断
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
