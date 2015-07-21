package cn.lnu.domain;

/*
 * id varchar(40) primary key,
	btnname varchar(255) not null,
	btnid varchar(40) not null unique
 * */
public class Xffsface1 {
	private String id;
	private String btnname;
	private String btnid;
	
	
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
