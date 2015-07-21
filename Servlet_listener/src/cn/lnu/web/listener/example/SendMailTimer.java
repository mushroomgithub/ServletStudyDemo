package cn.lnu.web.listener.example;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SendMailTimer implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		Timer timer=new Timer();
		Calendar c=Calendar.getInstance();
		c.set(2015, 6, 4, 11, 01, 0);//表示设置时间在2015-7-4 11-01-00发邮件
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//System.out.println("我给你发邮件了！");
			}
			
		},c.getTime());
	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
