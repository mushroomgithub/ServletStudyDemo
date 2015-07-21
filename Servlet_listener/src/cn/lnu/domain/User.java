package cn.lnu.domain;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

//创建一个javaBean User，它向监听自己有没有绑定到一个session中去，可以让这个javabean类实现HttpSessionBindingListener这个接口，并且不需要在web.xml中注册监听器了，因为它既是监听器又是事件源
public class User implements HttpSessionBindingListener {

	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		System.out.println("user对象自己保存到session中了！！");
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		System.out.println("user对象自己从session中接触绑定了！！");
	}

}
