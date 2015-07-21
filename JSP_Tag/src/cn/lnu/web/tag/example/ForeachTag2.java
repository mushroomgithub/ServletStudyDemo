package cn.lnu.web.tag.example;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ForeachTag2 extends SimpleTagSupport {
	private Object items;
	private String var;
	private Collection collection;//不论items是什么类型的都先转换为单列集合，最后在doTag中统一处理
	public void setItems(Object items) {
		this.items = items;
		
		if(items instanceof Collection){
			collection=(Collection)items;//list set
		}
		if(items instanceof Map){
			Map map=(Map)items;
			collection=map.entrySet();//set
		}
		
		/*if(items instanceof Object[]){
			Object obj[]=(Object[]) items;
			collection=Arrays.asList(obj);//Arrays类的asList的方法接受一个可变参数，传递一个对象数组可以返回一个list结合
		}*/
		
		if(items.getClass().isArray()==true){
			this.collection=new ArrayList();
			int length=Array.getLength(items);//Array这个反射类可以对任何一个java中的数组进行处理,这个类提供了操作java数组的接口
			for(int i=0;i<length;i++){
				Object value=Array.get(items, i);
				this.collection.add(value);
			}
		}
	}
	public void setVar(String var) {
		this.var = var;
	}
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		PageContext pageContext=(PageContext) this.getJspContext();
		//对collection进行迭代
		Iterator it=this.collection.iterator();
		while(it.hasNext()){
			Object value=it.next();
			//pageContext.setAttribute(var, value);
			this.getJspContext().setAttribute(var, value);
			this.getJspBody().invoke(null);
		}
	}

	
}
