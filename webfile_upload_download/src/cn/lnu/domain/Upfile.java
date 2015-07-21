package cn.lnu.domain;

import java.util.Date;

/*
 * 			id varchar(40) primary key,
			uuidname varchar(100) not null unique,
			realname varchar(100) not null,
			savepath varchar(255) not null,
			uptime datetime not null,
			description varchar(255),
			username varchar(40) not null
 * */
public class Upfile {
	private String id;
	private String uuidname;//上传文件的名称，文件的uuid名
	private String realname;//上传文件的真实名称
	private String savepath;//记住上传文件的位置
	private Date uptime;//文件的上传时间
	private String description;//文件的描述
	private String username;//上传人
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUuidname() {
		return uuidname;
	}
	public void setUuidname(String uuidname) {
		this.uuidname = uuidname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getSavepath() {
		return savepath;
	}
	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}
	public Date getUptime() {
		return uptime;
	}
	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
