package cn.lnu.web.formbean;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

//代表用户提交的表单
public class RegisterForm {
	private String username;
	private String password;
	private String password2;
	private String email;
	private String birthday;
	private String nickname;
	private String checkcode;
	//定义一个集合，用户保存错误的消息
	private Map errors=new HashMap();
	
	public Map getErrors() {
		return errors;
	}
	public void setErrors(Map errors) {
		this.errors = errors;
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
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	//自定义一个方法对表单中的数据进行校验，校验成功返回true,失败返回false
	/*
	 * 	校验规则：
	 * 用户名不能为空，并且要是3-8位字母
	 * 密码不能为空，并且是6-8位数字
	 * 确认密码不能为空，并且要和上面保持一致
	 * 电子邮箱不能为空，并且要是一个格式合法的邮箱
	 * 生日可以为空，不为空时，必须是一个日期
	 * 昵称不可以为空，并且要是汉字
	 * */
	public boolean validate(HttpServletRequest request) throws Exception{
		boolean isOK=true;
		//用户名不能为空，并且必须是3-8位的字母组合
		if(this.username==null||this.username.trim().equals("")){
			isOK=false;
			errors.put("username", "用户名不能为空！");
		}else{
			if(!this.username.matches("[a-zA-Z]{3,8}")){
				isOK=false;
				errors.put("username", "用户名必须为3-8为的字母!");
			}
		}
		//输入密码不能为空，并且只能是6-8位的数字
		if(this.password==null||this.password.trim().equals("")){
			isOK=false;
			errors.put("password", "密码不能为空！");
		}else{
			if(!this.password.matches("\\d{6,8}")){
				isOK=false;
				errors.put("password", "密码必须为6-8为的数字!");
			}
		}
		//两次密码必须一致
		if(this.password2==null||this.password2.trim().equals("")){
			isOK=false;
			errors.put("password2", "确认密码不能为空！");
		}else{
			if(!this.password.equals(this.password2)){
				isOK=false;
				errors.put("password2", "两次输入密码必须一致！");
			}
		}
		//邮箱不能为空，并且必须是一个合法的邮箱
		if(this.email==null||this.email.trim().equals("")){
			isOK=false;
			errors.put("email", "邮箱不能为空！");
		}else{
			//邮箱正则表达式
			if(!this.email.matches("\\w+@\\w+(\\.\\w+)+")){
				isOK=false;
				errors.put("email", "请输入一个合法的邮箱！");
			}
		}
		///生日可以为空，不为空时，必须是一个日期格式
		if(this.birthday!=null && !this.birthday.trim().equals("")){
			//日期的校验，采用beanutils中一个类DateLocaleConverter(本地日期转换器)
			try{
				DateLocaleConverter dl=new DateLocaleConverter();
				dl.convert(this.birthday, "yyyy-MM-dd");
			}catch(Exception e){
				isOK=false;
				errors.put("birthday", "输入日期格式不正确！");
				}
		}
		//昵称不能为空，并且必须是汉字，汉字区间为 \u4e00-\u9fa5
		if(this.nickname==null||this.nickname.trim().equals("")){
			isOK=false;
			errors.put("nickname", "昵称不能为空！");
		}else{
			if(!this.nickname.matches("^([\u4e00-\u9fa5]+)$")){
				isOK=false;
				errors.put("nickname", "昵称必须是汉字！");
			}
		}
		
		String c_checkedcode=this.checkcode;
		//得到服务器session中保存的图片验证码
		String s_checkedcode=(String) request.getSession().getAttribute("checkcode");
		if(c_checkedcode==null || s_checkedcode==null || !c_checkedcode.equals(s_checkedcode)){
			isOK=false;
			errors.put("checkcode", "验证码不正确！");
		}
		return isOK;
	}
	
	
}
