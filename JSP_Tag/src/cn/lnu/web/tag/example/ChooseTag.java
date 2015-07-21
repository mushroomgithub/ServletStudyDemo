package cn.lnu.web.tag.example;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ChooseTag extends SimpleTagSupport {
	private boolean isDo;

	public boolean isDo() {//get
		return isDo;
	}

	public void setDo(boolean isDo) {//set
		this.isDo = isDo;
	}
//自己是父标签,必须覆盖doTag,控制标签体执行
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		this.getJspBody().invoke(null);
	}
	
}
