package cn.lnu.web.tag.example;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ForeachTag extends SimpleTagSupport {
	private Object items;
	private String var;//相当于关键字属性
	
	public void setItems(Object items) {
		this.items = items;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		List list=(List)items;
		Iterator it=list.iterator();
		while(it.hasNext()){
			Object value=it.next();
			this.getJspContext().setAttribute(var, value);//每次迭代出一个数据就存在servletContext域的var属性中
			this.getJspBody().invoke(null);//通知浏览器调用el表达式从该域的var属性中取出标签体数据，下载迭代将覆盖上次var中的值
		}
	}
	
	
}
