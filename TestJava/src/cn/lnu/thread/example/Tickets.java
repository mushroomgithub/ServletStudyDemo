package cn.lnu.thread.example;


public class Tickets {
	private int size;//票总数
	private int number=0;//存票序号
	private boolean available=false;//是否有待售的票
	
	public Tickets(int size){
		this.size=size;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public synchronized void put() {//放票,每次放一张票
		if(available){//如果还有存票待售，则存票线程等待
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Producer puts ticket "+(++number));
		available=true;
		notify();//存票后唤醒售票线程开始售票
	}
	
	public synchronized void sell() {//售票，每次售一张票
		if(!available){//如果当前无票可售，则售票线程等待
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Consumer buys ticket "+(number));
		available=false;
		notify();//售票后唤醒放票线程开始放票
		if(number==size) number=size+1;//在售完最后一张票后，设置一个结束标志即number>size表示售票结束
	}
	
}
