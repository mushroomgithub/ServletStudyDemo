package cn.lnu.thread.info;
//Ïû·ÑÕß
public class Consumer implements Runnable {
	private Resource r;
	public Consumer(Resource r){
		this.r=r;
	}
	
	public void run() {
		while(true)
			r.getRes();

	}

}
