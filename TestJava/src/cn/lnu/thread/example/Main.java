package cn.lnu.thread.example;

public class Main {//主线程

	/**
	 * 生产者与消费者实现放票和售票功能
	 * 
	 * 当Consumer线程售出票后，available值变为false，当Producer线程放入票后，
	 * available值变为true。只有available为true时，Consumer线程才能售票，否则
	 * 就必须等待Producer线程放入新的票后通知Consumer售票；反之，只有available为false时，
	 * Producer线程才能放票，否则必须等待Consumer线程售出票后再通知Producer放票
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tickets t=new Tickets(10);//设置总票数10张
		Producer p=new Producer(t);//创建放票者线程
		Consumer c=new Consumer(t);//创建售票者线程
		//开启线程，实现放票10张，售票10张的功能
		p.start();
		c.start();
		
	}

}
