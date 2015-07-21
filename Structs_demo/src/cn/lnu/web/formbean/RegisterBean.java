package cn.lnu.web.formbean;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

public class RegisterBean extends ActionForm{
	private String username;
	private String password;
	private String email;
	private FormFile file;//struts会自动将上传的文件数据封装到这个FormFile中去，也就是说这个字段保存了上传文件
	
	
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override 
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		ActionErrors errors=new ActionErrors();//创建一个ActionErrors对象，然后对表单数据进行校验，如果校验失败，errors中就有数据了，
			//这样当structs检测到ActionErrors中有数据的话，表示校验失败，否则校验成功，structs调用你的action处理你的请求
		
		ResourceBundle rb=ResourceBundle.getBundle("MessageResource");//国际化编程中的这个类，可以给定一个资源文件的基名
		
		if(this.username==null || this.username.trim().equals("")){
			errors.add("username",new ActionMessage("prompt.message",rb.getString("prompt.username")));
		}else{
			if(!this.username.matches("[A-Za-z]{3,6}")){
				errors.add("username",new ActionMessage("prompt.errors.username"));
			}
		}
		
		if(this.password==null || this.password.trim().equals("")){
			errors.add("password", new ActionMessage("prompt.message",rb.getString("prompt.password")));
		}else{
			if(!this.password.matches("\\d{3,6}")){
				errors.add("password", new ActionMessage("prompt.errors.password"));
			}
		}
		return errors;
	}
	
}
