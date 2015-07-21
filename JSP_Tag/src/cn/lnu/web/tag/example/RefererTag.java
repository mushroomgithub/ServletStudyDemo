package cn.lnu.web.tag.example;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class RefererTag extends SimpleTagSupport {
	private String site;
	private String page;
	
	public void setSite(String site) {
		this.site = site;
	}

	public void setPage(String page) {
		this.page = page;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		PageContext pageContext=(PageContext) this.getJspContext();
		
		HttpServletRequest request=(HttpServletRequest) pageContext.getRequest();
		HttpServletResponse response=(HttpServletResponse) pageContext.getResponse();
		//1，得到来访者referer
		String referer=request.getHeader("referer");
		//2，判断来访者的页面是不是要防盗链的网站
		if(referer==null || !referer.startsWith(site)){//是盗链者
			if(page.startsWith(request.getContextPath())){
				response.sendRedirect(page);
			}else if(page.startsWith("/")){
				response.sendRedirect(request.getContextPath()+page);
			}else{
				response.sendRedirect(request.getContextPath()+"/"+page);
			}
			//如果是盗链者，jsp余下的页面就不让来访者查看
			throw new SkipPageException();
		}
	}
}
