package cn.lnu.domain;

import java.util.List;

//封装web层需要提供给用户页面显示的数据
public class PageBean {
	private List list;//封装用户想看的页的数据
	private int totalrecord;//记录总记录数
	private int pagesize;//记录页面大小
	
	private int totalpage;//记录总页数
	private int currentpage;//记录当前页
	private int previouspage;//记录上一页
	private int nextpage;//记录下一页
	private int[] pagebar;//记录页码条 1 2 3 4 5...
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
	public int getTotalpage() {//根据总记录数和页面大小算出总页数
		//100 5 20
		//101 5 21
		//99 5 20
		if(this.totalrecord%this.pagesize==0){
			this.totalpage=this.totalrecord/this.pagesize;
		}else{
			this.totalpage=this.totalrecord/this.pagesize+1;
		}
		
		return totalpage;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getPreviouspage() {//根据当前页算出上一页
		if(this.currentpage-1<1){
			this.previouspage=1;
		}else{
			this.previouspage=this.currentpage-1;
		}
		return previouspage;
	}
	
	public int getNextpage() {//根据当前页算出下一页
		if(this.currentpage+1>=this.totalpage){
			this.nextpage=this.totalpage;
		}else{
			this.nextpage=this.currentpage+1;
		}
		return nextpage;
	}

	public int[] getPagebar() {//根据总页数生成页码条
		/*int pagebar[]=new int[this.totalpage];//页码条显示所有页
		for(int i=1;i<=pagebar.length;i++){
			pagebar[i-1]=i;
		}
		this.pagebar=pagebar;
		return pagebar;*/
		
		//现在控制页码条每次显示10页
		int pagebar[]=null;
		int startpage;//页码条显示的起始页码
		int endpage;//页码条显示的结束页码
		if(this.totalpage<=10){//如果当前页码总数小于10
			pagebar=new int[this.totalpage];
			startpage=1;
			endpage=this.totalpage;
		}else{//总页数大于10，根据当前页，算出页码条起始页和结束页
			pagebar=new int[10];
			startpage=this.currentpage-4;
			endpage=this.currentpage+5;
			//考虑特殊情况，如果总页数=30 当前页=3 startpage=-1
			//总页数=30 当前页=29 endpage=34 超出页码总数 21 30
			if(startpage<1){
				startpage=1;
				endpage=10;
			}
			if(endpage>this.totalpage){
				endpage=this.totalpage;
				startpage=this.totalpage-9;
			}
		}
		//根据页码条起始和结束页，填充页码条
		int index=0;
		for(int i=startpage;i<=endpage;i++){
			pagebar[index++]=i;
		}
		
		this.pagebar=pagebar;
		return this.pagebar;
	}
}
