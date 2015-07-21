package cn.itcast.domain;

import java.util.List;

public class PageBean {

	private List list;
	private int totalrecord;
	private int pagesize;
	private int totalpage;
	private int currentpage;
	private int previouspage;
	private int nextpage;
	private int[] pagebar;
	
	
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
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getTotalpage() {
		//100   5   20
		//101   5   21  =20---1
		//99    5   20  =19---4
		
		if(this.totalrecord%this.pagesize==0){
			this.totalpage = this.totalrecord/this.pagesize;
		}else{
			this.totalpage = this.totalrecord/this.pagesize+1;
		}
		
		
		return totalpage;
	}
	
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getPreviouspage() {
		if(this.currentpage-1<1){
			this.previouspage = 1;
		}else{
			this.previouspage = this.currentpage-1;
		}
		return previouspage;
	}
	
	public int getNextpage() {
		if(this.currentpage+1>=this.totalpage){
			this.nextpage = this.totalpage;
		}else{
			this.nextpage = this.currentpage +1;
		}
		return nextpage;
	}
	
	public int[] getPagebar() {
		int startpage;
		int endpage;
		int pagebar[] = null;
		if(this.totalpage<=10){
			pagebar = new int[this.totalpage];
			startpage = 1;
			endpage = this.totalpage;
		}else{
			pagebar = new int[10];
			startpage = this.currentpage - 4;
			endpage = this.currentpage + 5;
			
			//总页数=30      3    -1
			//总页数=30      29   34   21   30
			if(startpage<1){
				startpage = 1;
				endpage = 10;
			}
			
			if(endpage>this.totalpage){
				endpage = this.totalpage;
				startpage = this.totalpage - 9;
			}
		}
		
		int index = 0;
		for(int i=startpage;i<=endpage;i++){
			pagebar[index++] = i;
		}
		
		this.pagebar = pagebar;
		return this.pagebar;
		/*int pagebar[] = new int[this.totalpage];
		for(int i=1;i<=this.totalpage;i++){
			pagebar[i-1] = i;
		}
		this.pagebar = pagebar;
		return pagebar;*/
	}
	
}
