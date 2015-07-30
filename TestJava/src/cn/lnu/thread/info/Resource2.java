package cn.lnu.thread.info;
//多生产者-消费者都需要操作的资源
public class Resource2 {
	private String name;
	private int count=1;
	private boolean flag=true;//表示有面包可消费
	//提供一个给生产者给资源赋值的方法
	public synchronized void setRes(String name){
		while(flag){//判断标记为true,执行wait等待，为false就生产
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		this.name=name+"---"+count;
		count++;
		System.out.println(Thread.currentThread().getName()+"------生产者------"+this.name);
		//生产完毕，将标记改为true
		flag=true;
		//唤醒所有等待线程，包括本方线程
		notifyAll();
	}
	//提供一个供消费者获取商品的方法
	public synchronized void getRes(){
		while(!flag){//如果没有商品可以消费，则等待
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+"------------消费者---------"+this.name);
		//消费完毕，将标记改为false
		flag=false;
		//唤醒所有等待线程，包括本方线程
		notifyAll();
	}
}
