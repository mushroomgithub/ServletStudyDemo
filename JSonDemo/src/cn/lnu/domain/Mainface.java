package cn.lnu.domain;

public class Mainface  {
	private String id;
	private String btnname;
	private String btnid;
	
	
	public Mainface() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mainface(String id, String btnname, String btnid) {
		super();
		this.id = id;
		this.btnname = btnname;
		this.btnid = btnid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBtnname() {
		return btnname;
	}
	public void setBtnname(String btnname) {
		this.btnname = btnname;
	}
	public String getBtnid() {
		return btnid;
	}
	public void setBtnid(String btnid) {
		this.btnid = btnid;
	}
	
	
}
