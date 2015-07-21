package cn.lnu.simpletag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
//编写使用简单标签的方法实现修改标签体内容的标签处理器类
public class SimpleTagDemo3 extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		
		//首先获得标签体
		JspFragment jf=this.getJspBody();
		//将标签体内容修改为大写，首先需要将标签体内容数据写到缓存缓存
		StringWriter sw=new StringWriter();
		jf.invoke(sw);
		//从缓存中获得标签体内容，做出修改
		String content=sw.toString();
		content=content.toUpperCase();
		//再将修改之后的内容写给浏览器
		this.getJspContext().getOut().write(content);
	}
}
