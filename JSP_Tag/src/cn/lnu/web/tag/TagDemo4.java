package cn.lnu.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;
//编写修还标签体类处理器，实现将标签体内容转化为大写
public class TagDemo4 extends BodyTagSupport {

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		return BodyTag.EVAL_BODY_BUFFERED;//这句执行之后，服务器在执行到标签体时，会将其看做一个对象保存起来，会调用setBodyContent(),将标签体保存到一个对象中
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		//在结束标签时获得标签体内容，然后做出修改
		BodyContent bc=this.getBodyContent();
		String content=bc.getString();
		content=content.toUpperCase();
		try {
			this.pageContext.getOut().write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return Tag.EVAL_PAGE;//这里只是对标签体内容做出修改，下面的jsp内容还要执行显示，随意此处返回EVAL_PAGE
		//return super.doEndTag();
	}
	
	
}
