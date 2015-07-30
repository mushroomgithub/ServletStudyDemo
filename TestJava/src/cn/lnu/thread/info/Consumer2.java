package cn.lnu.thread.info;
//Ïû·ÑÕß
public class Consumer2 implements Runnable {
	private Resource2 r2;
	
	public Consumer2(Resource2 r2){
		this.r2=r2;
	}
	public void run() {
		while(true)
			r2.getRes();

	}

}
