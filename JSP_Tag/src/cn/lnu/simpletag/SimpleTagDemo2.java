package cn.lnu.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
//编写使用简单标签的方法实现标签体内容执行5次的标签处理器类
public class SimpleTagDemo2 extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		
		//首先获得标签体
		JspFragment jf=this.getJspBody();
		//将标签体内容执行5次
		for(int i=1;i<=5;i++){
			jf.invoke(null);//传递null也是默认写给浏览器等价于 jf.invoke(this.getJspContext().getOut());
		}
	}
	
}
