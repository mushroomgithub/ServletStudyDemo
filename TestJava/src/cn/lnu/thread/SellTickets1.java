package cn.lnu.thread;

public class SellTickets1 extends Thread {
	private static int tickets=200;
	public void run() {
		System.out.println("ticket sell thread"+Thread.currentThread().getId()+" is start.");
		while(tickets>0){
			System.out.println(Thread.currentThread().getId()+":"+Thread.currentThread().getName()+
					" is selling ticket "+tickets--);
		}
		System.out.println("ticket sell thread"+Thread.currentThread().getId()+" is end.");
	}
	
}
