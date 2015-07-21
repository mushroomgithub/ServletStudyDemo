package cn.lnu.domain;

import java.util.List;

//封装从数据库中查询结果数据
public class QueryResult {
	private List list;//记住用户看的页的数据
	private int totalrecord;//记住总记录数
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}
	
}
