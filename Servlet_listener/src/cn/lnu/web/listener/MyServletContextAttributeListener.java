package cn.lnu.web.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements
		ServletContextAttributeListener {

	public void attributeAdded(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
		String Attrname=scab.getName();
		Object AttrValue=scab.getValue();
		System.out.println("向servletContext中存了"+Attrname+"="+AttrValue);
	}

	public void attributeRemoved(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
		System.out.println("从servletContext中删除了"+scab.getName()+"属性");
	}

	public void attributeReplaced(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
		System.out.println("servletContext中的"+scab.getName()+"属性值被替换了");
	}

}
