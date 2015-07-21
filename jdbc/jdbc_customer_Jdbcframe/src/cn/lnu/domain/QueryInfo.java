package cn.lnu.domain;
//封装客户端带过来的查询数据
public class QueryInfo {
	private int currentpage=1;//表示客户当前看的页
	private int pagesize=5;//记住用户想看的页面大小
	
	//得到这两个值可以算出用户看的页面的数据在数据库中的起始位置
	private int startindex;

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getStartindex() {
		this.startindex=(this.currentpage-1)*this.pagesize;
		return startindex;
	}
}
