package cn.lnu.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
//编写使用简单标签的方法实现是否执行标签体功能的标签处理器类
public class SimpleTagDemo1 extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		
		//首先获得标签体
		JspFragment jf=this.getJspBody();
		//将标签体写给浏览器,若是不写个浏览器，则标签体的内容不会执行，即不会显示
		//jf.invoke(this.getJspContext().getOut());
	}
	
}
