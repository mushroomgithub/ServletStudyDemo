package cn.lnu.web.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ViewIPTag extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) this.pageContext.getRequest();
		JspWriter out=this.pageContext.getOut();
		
		String ip=request.getRemoteAddr();
    	try {
			out.print(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		return super.doStartTag();
	}
	
}
