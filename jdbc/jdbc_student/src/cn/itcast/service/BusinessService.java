package cn.itcast.service;

import cn.itcast.dao.StudentDao;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.QueryInfo;
import cn.itcast.domain.QueryResult;

public class BusinessService {
	
	public PageBean pageQuery(QueryInfo info){
		
		StudentDao dao = new StudentDao();
		QueryResult qr = dao.pageQuery(info.getStartindex(), info.getPagesize());
		
		PageBean bean = new PageBean();
		bean.setCurrentpage(info.getCurrentpage());
		bean.setList(qr.getList());
		bean.setPagesize(info.getPagesize());
		bean.setTotalrecord(qr.getTotalrecord());
		
		
		return bean;
	}
	
}
