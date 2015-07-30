package cn.lnu.thread;

public class ThreadDemo1 {

	/**
	 *测试通过继承Thread类实现多线程售票功能,尚未考虑线程同步
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("main thread is start.");
		
		SellTickets1 t1=new SellTickets1();
		SellTickets1 t2=new SellTickets1();
		SellTickets1 t3=new SellTickets1();
		
		
		//启动多线程
		t1.start();
		t2.start();
		t3.start();
		System.out.println("sell thread is start,main thread is end.");
	}

}
