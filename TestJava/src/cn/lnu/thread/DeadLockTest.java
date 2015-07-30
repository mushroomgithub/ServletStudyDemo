package cn.lnu.thread;

public class DeadLockTest {

	/**
	 * ²âÊÔÍ¬²½Ç¶Ì×ËÀËø
	 */
	public static void main(String[] args) {
		Task t1=new Task(true);
		Task t2=new Task(false);
		
		Thread thread1=new Thread(t1);
		Thread thread2=new Thread(t2);
		thread1.start();
		thread2.start();
	}

}
