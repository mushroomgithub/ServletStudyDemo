package cn.lnu.thread.info;
//生产者
public class Producer2 implements Runnable {
	private Resource2 r2;//测试多生产者多消费者
	public Producer2(Resource2 r2){
		this.r2=r2;
	}
	public void run() {
		while(true)
			r2.setRes("面包");
	}

}
