package cn.lnu.thread;

public class SellTickets implements Runnable {
	private int tickets = 200;
	private Object obj = new Object();

	public void run() {

		while (true) {
			synchronized (obj) {//获得这个对象的锁旗标
				if (tickets > 0) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getId() + ":"
							+ Thread.currentThread().getName()
							+ " is selling ticket " + tickets--);
				}
			}

		}
	}
}
