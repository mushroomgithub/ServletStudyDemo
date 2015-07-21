package cn.lnu.domain;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class myBean implements HttpSessionActivationListener,Serializable {//实现了这个接口的bean也不需在web.xml中注册监听器，因为监听的是它自己

	public void sessionDidActivate(HttpSessionEvent se) {//调用这个方法这个javabean会随着session活化到内存
		// TODO Auto-generated method stub 
		System.out.println("myBean随着session从硬盘回到活化到内存中了！！");
	}

	public void sessionWillPassivate(HttpSessionEvent se) {//调用这个方法这个javabean会随着session钝化到磁盘
		// TODO Auto-generated method stub
		System.out.println("myBean随着session从内存回到钝化到磁盘中了！！");
	}

}
