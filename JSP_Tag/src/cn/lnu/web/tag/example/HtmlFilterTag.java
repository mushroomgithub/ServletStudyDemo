package cn.lnu.web.tag.example;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HtmlFilterTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		JspFragment jf=this.getJspBody();
		
		StringWriter sw=new StringWriter();//new一个带缓冲的StringWriter用于暂存标签体的内容
		
		jf.invoke(sw);
		
		String content=sw.getBuffer().toString();//获得标签体的内容
		//转义输出给浏览器
		content=filter(content);
		
		this.getJspContext().getOut().write(content);
	}
	/*这个函数可以从tomcat服务器中的
	 * C:\apache-tomcat-6.0.20\webapps\examples\WEB-INF\classes\Util\HTMLFilter.java中获得*/
	public static String filter(String message) {

        if (message == null)
            return (null);

        char content[] = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuffer result = new StringBuffer(content.length + 50);
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
            case '<':
                result.append("&lt;");
                break;
            case '>':
                result.append("&gt;");
                break;
            case '&':
                result.append("&amp;");
                break;
            case '"':
                result.append("&quot;");
                break;
            default:
                result.append(content[i]);
            }
        }
        return (result.toString());

    }
}
