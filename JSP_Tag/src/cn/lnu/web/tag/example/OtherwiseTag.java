package cn.lnu.web.tag.example;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class OtherwiseTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		ChooseTag parent=(ChooseTag) this.getParent();
		
		if(!parent.isDo()){//如果哥哥标签没有执行
			this.getJspBody().invoke(null);
			
			parent.setDo(true);
		}
	}
	
}
