package cn.lnu.web.listener.example;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class coutNumListener implements HttpSessionListener {
	
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session=se.getSession();
		ServletContext context=session.getServletContext();
		
		Integer count=(Integer) context.getAttribute("count");
		if(count==null){//当用户第一次登陆网站时，count=null
			count=1;
			context.setAttribute("count", count);
		}else{
			count++;
			context.setAttribute("count", count);
		}
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession seesion=se.getSession();
		ServletContext context=seesion.getServletContext();
		Integer count=(Integer) context.getAttribute("count");
		
		count--;
		context.setAttribute("count", count);
	}

}
