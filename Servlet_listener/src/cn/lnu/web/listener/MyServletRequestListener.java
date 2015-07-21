package cn.lnu.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
//可以用于统计网站性能
public class MyServletRequestListener implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		//System.out.println("request被销毁！");
	}

	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		//System.out.println("request被创建！");
	}

}
