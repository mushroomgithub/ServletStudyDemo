package cn.lnu.web.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class HttpSessionAndServletRequestAttributeListener implements
		HttpSessionAttributeListener, ServletRequestAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		System.out.println("向session中添加了属性！！");
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		System.out.println("从session中删除了属性！！");
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		System.out.println("向session中替换了属性！！");
	}

	public void attributeAdded(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		System.out.println("向request中添加了属性！！");
	}

	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		System.out.println("从request中删除了属性！！");
	}

	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		System.out.println("向request中替换了了属性！！");
	}	

}
