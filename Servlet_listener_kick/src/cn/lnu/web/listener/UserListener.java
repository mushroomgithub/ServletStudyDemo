package cn.lnu.web.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import cn.lnu.domain.User;

//只要用户登录了，就往session中添加一个user属性，这个监听器的attributeAdded方法就会执行
public class UserListener implements HttpSessionAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		//Map map=new HashMap();//这种方法不好，因为每次登陆一个用户，添加一个属性就要new一个Map集合
		Map map=(Map) se.getSession().getServletContext().getAttribute("map");//得到servletContext中的添加的map集合
		if(map==null){//使用这种方法，只会在第一个用户登录,向session中添加属性的时候才会创建map集合
			map=new HashMap();
			se.getSession().getServletContext().setAttribute("map", map);//只要有用户登录进来就添加到servletContext域中的map集合中
		}
		Object obj=se.getValue();
		if(obj instanceof User){//判断session中取出的值是不是user对象，如果是，表示用户登录了
			User user=(User)obj;
			map.put(user.getUsername(),se.getSession());//放到一个map集合中，便于管理（踢人操作）登陆的用户
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

}
