package cn.lnu.web.thread;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ScreenRun implements Runnable {
	private Object bean;
	HttpServletResponse response;
	public ScreenRun(Object bean,HttpServletResponse response){
		this.bean=bean;
		this.response=response;
	}
	public void run() {
		while(bean!=null){
			JSONObject json=JSONObject.fromObject(bean);
			String jstr=json.toString();
			System.out.print(jstr);
			try {
				response.getOutputStream().write(jstr.getBytes("UTF-8"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			} 
		}
		
	}

}
