package cn.lnu.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
//控制整个页面内容是否输出的处理器类
public class TagDemo2 extends TagSupport {

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		
		//return Tag.EVAL_PAGE;//表示结束标签之后继续处理整个页面内容
		return Tag.SKIP_PAGE;//表示结束标签之后不再继续处理整个页面内容
	}

}
