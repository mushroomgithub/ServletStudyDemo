package cn.lnu.web.listener.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
//自定义session扫描器
public class SessionScannerListener implements HttpSessionListener ,ServletContextListener{
	
	private List<HttpSession> list=Collections.synchronizedList(new LinkedList<HttpSession>());
	private Object lock=new Object();//声明一把锁，被两段代码共享
	
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session=se.getSession();
		System.out.println(session+"被创建");
		synchronized(lock){//新用户登陆，向集合中添加新的元素时，先拿到同步锁，即锁旗标
			list.add(session);//存在线程安全问题,但是使用Collections.synchronizedList(List<T>)就可以返回一个线程安全的list
		}
			
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println(se.getSession()+"被摧毁");
	}

	//为了实现每五分钟扫描一次session有没有人用，这里需要创建一个定时器，而这个定时器需要随着web应用的启动而启动，所以还需要实现ServletContextListener接口，实现定时器的创建和session的扫描
	public void contextInitialized(ServletContextEvent sce) {//web应用启动的时候，就启动定时器
		// TODO Auto-generated method stub
		//创建一个定时器
		Timer timer=new Timer();
		timer.schedule(new myTask(list,lock), 0, 1*60*1000);//把同步锁lock也传递过去
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}

//这个任务就是每隔5分钟扫描一次list结合，如果集合中的元素session五分钟内无人访问就从集合中删除
class myTask extends TimerTask{
	private List list;
	private Object lock;//迭代和用户登录网站添加session必须放到一个同步代码块中，所以此处还需要接收一把同步锁
	myTask(List list,Object lock){
		this.list=list;
		this.lock=lock;
	}
	@Override
	public void run() {
		//System.out.println("定时器执行！");
		synchronized(this.lock){//避免在迭代器迭代的时候，用户新登录，向集合中添加新的session，而迭代器此时也不知道，产生并发修改异常
			//Iterator it=list.iterator();//获得集合的迭代器
			ListIterator it=list.listIterator();
			while(it.hasNext()){
				HttpSession session=(HttpSession) it.next();
				if((System.currentTimeMillis()-session.getLastAccessedTime())>1*60*1000){
					session.invalidate();//如果这个session5分钟内没人访问就摧毁它
					//list.remove(session);//这句是在集合迭代的过程中删除元素，这时迭代器并不知道，你做的修改(这里指删除元素)，会抛出一个并发修改异常，
					it.remove();//由于当前迭代器正在迭代这个session，所以我们不需要传递参数session给remove方法
				}
			}
		}
	}
}
