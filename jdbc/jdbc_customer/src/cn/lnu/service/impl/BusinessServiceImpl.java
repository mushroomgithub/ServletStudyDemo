package cn.lnu.service.impl;
//记得这个实现类写完之后，将实现类的方法重构到一个接口包中，具体方法是在BusinessServiceImpl.java单击鼠标右键，选择Refactor-->Extract Interface,勾选这几个方法，输入接口类的名，这里为BusinessService，然后在BusinessService上右键,Refactor-->Move,将这个接口移动到接口包cn.lnu.service中
import java.util.List;

import cn.lnu.dao.CustomerDao;
import cn.lnu.dao.impl.CustomerDaoImpl;
import cn.lnu.domain.Customer;
import cn.lnu.domain.PageBean;
import cn.lnu.domain.QueryInfo;
import cn.lnu.domain.QueryResult;
import cn.lnu.service.BusinessService;

//业务逻辑层根据service对web层提供什么服务，来设计方法,这里有添加客户的服务等等，主要是调用dao层的方法处理web层的请求
public class BusinessServiceImpl implements BusinessService {
	
	private CustomerDao dao=new CustomerDaoImpl();//正常为了解耦，需要使用工厂模式，这里就不这么做了，直接new出一个接口
	
	//由于web层可能要添加新的用户，从web层获得要添加的用户
	public void addCustomer(Customer c){
		dao.add(c);
	}
	//web层要更新一个用户
	public void updateCustomer(Customer c){
		dao.update(c);
	}
	//web层要删除一个用户
	public void deleteCustomer(String id){
		dao.delete(id);
	}
	//web层要查找一个用户
	public Customer findCustomer(String id){
		return dao.find(id);
	}
	
	public List<Customer> getAllCustomer(){
		return dao.getAll();
	}
	
	
	//接受查询条件对象，调用查询结果对象，返回页面要显示的pageBean
	public PageBean pageQuery(QueryInfo queryinfo){
		//调用dao获取到页面查询结果
		QueryResult qr=dao.pageQuery(queryinfo.getStartindex(), queryinfo.getPagesize());
		//根据dao查询结果，生成页面显示需要的pagebean
		PageBean bean=new PageBean();
		bean.setCurrentpage(queryinfo.getCurrentpage());
		bean.setList(qr.getList());
		bean.setPagesize(queryinfo.getPagesize());
		bean.setTotalrecord(qr.getTotalrecord());
		return bean;
	}
	
}
