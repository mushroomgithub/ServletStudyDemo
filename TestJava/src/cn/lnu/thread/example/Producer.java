package cn.lnu.thread.example;

public class Producer extends Thread {
	Tickets t=null;
	public Producer(Tickets t){
		this.t=t;
	}
	@Override
	public void run() {//·ÅÆ±Ïß³Ì
		// TODO Auto-generated method stub
		super.run();
		while(t.getNumber()<t.getSize()){
			t.put();
		}
	}
	
}
