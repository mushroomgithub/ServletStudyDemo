package cn.lnu.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//监听web应用的启动和销毁，可以对web资源进行一些初始化设置
public class MyServletContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		//System.out.println("servletContext被销毁！");
	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		//System.out.println("servletContext被创建！");
	}

}
