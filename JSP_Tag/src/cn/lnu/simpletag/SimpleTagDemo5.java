package cn.lnu.simpletag;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
//编写处理带属性的标签处理器类,实现向浏览器输出count次标签体内容
public class SimpleTagDemo5 extends SimpleTagSupport {
	private int count;
	private Date date;//服务器仅仅支持八种基本数据类型的转换，所以传递的字符串可以自动转为八种基本类型，但是日期格式不属于八种基本类型，只能通过el表达式或者脚本表达式，当然你也可以在处理器类中定义转换器实现字符串转日期格式功能
	public void setCount(int count) {
		this.count = count;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		
		JspFragment jf=this.getJspBody();
		this.getJspContext().getOut().write(date.toLocaleString());
		for(int i=0;i<count;i++){
			jf.invoke(null);
		}
	}
	
	
}
