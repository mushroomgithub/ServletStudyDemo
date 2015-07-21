package cn.lnu.web.tag.example;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class WhenTag extends SimpleTagSupport {
	private boolean test;

	public void setTest(boolean test) {
		this.test = test;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		//访问父亲标签的属性
		ChooseTag parent=(ChooseTag) this.getParent();
		
		if(test && !parent.isDo()){//如果test返回true，并且弟弟标签没有被执行
			JspFragment jf=this.getJspBody();
			jf.invoke(null);
			
			parent.setDo(true);
		}
	}
	
}
