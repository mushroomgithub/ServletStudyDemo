package cn.lnu.web.formbean;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class CotrolForm {
	private String tcount;
	private String scount;
	private String mcount;
	private String vcount;
	
	//定义一个集合，用户保存错误的消息
	private Map errors=new HashMap();
	
	public String getTcount() {
		return tcount;
	}

	public void setTcount(String tcount) {
		this.tcount = tcount;
	}

	public String getScount() {
		return scount;
	}

	public void setScount(String scount) {
		this.scount = scount;
	}

	public String getMcount() {
		return mcount;
	}

	public void setMcount(String mcount) {
		this.mcount = mcount;
	}

	public String getVcount() {
		return vcount;
	}

	public void setVcount(String vcount) {
		this.vcount = vcount;
	}

	public Map getErrors() {
		return errors;
	}

	public void setErrors(Map errors) {
		this.errors = errors;
	}
	
	//自定义一个方法对表单中的数据进行校验，校验成功返回true,失败返回false
	/*
	 * 	校验规则：
	 * 教师机数目不能为空，并且要是2-4之间的数字
	 * 学生机数目不能为空，并且是1-2之间的数字
	 * 模拟人数目不能为空，并且是1-2之间的数字
	 * TV数目不能为空，并且必须是1
	 * */
	public boolean validate(HttpServletRequest request) throws Exception{
		boolean isOK=true;
		//验证教师机约束规则
		if(this.tcount==null || this.tcount.trim().equals("")){
			isOK=false;
			errors.put("tcount", "教师机数目不能为空！");
		}else {
			if(!this.tcount.matches("[234]")){
				isOK=false;
				errors.put("tcount", "教师机数目必须是2-4范围的数字！");
			}
		}
		//验证学生机约束规则
		if(this.scount==null || this.scount.trim().equals("")){
			isOK=false;
			errors.put("scount", "学生机数目不能为空！");
		}else {
			if(!this.scount.matches("[12]")){
				isOK=false;
				errors.put("scount", "学生机数目必须是1-2范围的数字！");
			}
		}
		
		//验证模拟人约束规则
		if(this.mcount==null || this.mcount.trim().equals("")){
			isOK=false;
			errors.put("mcount", "模拟人数目不能为空！");
		}else {
			if(!this.mcount.matches("[12]")){
				isOK=false;
				errors.put("mcount", "模拟人数目必须是1-2范围的数字！");
			}
		}
		//验证TV约束规则
		if(this.vcount==null || this.vcount.equals("")){
			isOK=false;
			errors.put("vcount", "TV数目不能为空！");
		}else{
			if(!this.vcount.matches("[1]")){
				isOK=false;
				errors.put("vcount", "TV数目必须为数字1！");
			}
		}
		
		return isOK;
	}
}
