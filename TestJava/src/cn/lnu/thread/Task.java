package cn.lnu.thread;
//同步锁嵌套实现死锁
public class Task implements Runnable {
	private boolean flag;
	public Task(boolean flag){
		this.flag=flag;
	}
	public void run() {
		if(flag){
			while(true){
				synchronized(myLock.LOCKA){
					System.out.println("if---------locka");
					synchronized(myLock.LOCKB){
						System.out.println("if---------lockb");
					}
				}
			}
			
		}else{
			while(true){
				synchronized(myLock.LOCKB){
					System.out.println("else---------lockb");
					synchronized(myLock.LOCKA){
						System.out.println("else---------locka");
					}
				}
			}	
		}
	}
}


class myLock{//创建自己的两把锁
	public static final Object LOCKA=new Object();
	public static final Object LOCKB=new Object();
	
}