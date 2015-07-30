package cn.lnu.thread.newInfo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//多生产者-消费者都需要操作的资源
public class Resource2 {
	private String name;
	private int count=1;
	private boolean flag=true;//表示有面包可消费
	//创建新Lock锁
	private Lock lock=new ReentrantLock();
	//创建和Lock绑定的监视器对象,创建两个
	//生产者监视器与lock锁绑定
	private Condition producer_con=lock.newCondition();
	//消费者监视器与lock锁绑定
	private Condition consumer_con=lock.newCondition();
	//提供一个给生产者给资源赋值的方法
	public void setRes(String name){
		//获得锁
		lock.lock();
		try{
			while(flag){//判断标记为true,执行wait等待，为false就生产
				try {
					producer_con.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
			this.name=name+"---"+count;
			count++;
			System.out.println(Thread.currentThread().getName()+"------生产者------"+this.name);
			//生产完毕，将标记改为true
			flag=true;
			//生产完毕，应该唤醒一个消费者来消费
			consumer_con.signalAll();
		}finally{
			//释放锁
			lock.unlock();
		}
	}
	
	//提供一个供消费者获取商品的方法
	public void getRes(){
		//获得锁
		lock.lock();
		try{
			while(!flag){//如果没有商品可以消费，则等待
				try {
					consumer_con.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName()+"------------消费者---------"+this.name);
			//消费完毕，将标记改为false
			flag=false;
			//消费完毕，应该唤醒一个生产者生产
			producer_con.signalAll();
		}finally{
			//释放锁
			lock.unlock();
		}
		
	}
}
