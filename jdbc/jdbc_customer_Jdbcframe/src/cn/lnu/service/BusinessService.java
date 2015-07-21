package cn.lnu.service;

import java.util.List;

import cn.lnu.domain.Customer;
import cn.lnu.domain.PageBean;
import cn.lnu.domain.QueryInfo;

public interface BusinessService {

	//由于web层可能要添加新的用户，从web层获得要添加的用户
	void addCustomer(Customer c);

	//web层要更新一个用户
	void updateCustomer(Customer c);

	//web层要删除一个用户
	void deleteCustomer(String id);

	//web层要查找一个用户
	Customer findCustomer(String id);

	List<Customer> getAllCustomer();

	public PageBean pageQuery(QueryInfo queryinfo);
}