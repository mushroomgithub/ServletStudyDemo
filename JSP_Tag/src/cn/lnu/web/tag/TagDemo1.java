package cn.lnu.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
//控制标签体内容是否输出的处理器类
public class TagDemo1 extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		
		
		return Tag.EVAL_BODY_INCLUDE;//表示开始标签处理之后处理标签体
		//return Tag.SKIP_BODY;//表示开始标签处理之后不在处理标签体
	}
	
}
