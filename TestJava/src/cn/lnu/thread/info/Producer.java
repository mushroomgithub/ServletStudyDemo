package cn.lnu.thread.info;
//生产者
public class Producer implements Runnable {
	private Resource r;
	public Producer(Resource r){
		this.r=r;
	}
	public void run() {
		while(true)
			r.setRes("面包");
	}

}
