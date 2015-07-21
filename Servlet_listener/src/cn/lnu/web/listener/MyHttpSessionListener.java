package cn.lnu.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
//可以用在统计网站在线人数
public class MyHttpSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		//System.out.println("session被创建！");
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		//System.out.println("session被销毁！");
	}

}
