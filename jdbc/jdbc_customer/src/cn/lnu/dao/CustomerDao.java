package cn.lnu.dao;

import java.util.List;

import cn.lnu.domain.Customer;
import cn.lnu.domain.QueryResult;

public interface CustomerDao {

	void add(Customer c);

	void update(Customer c);

	void delete(String id);

	Customer find(String id);

	//取得数据库中所有用户，最后保存到一个list集合中
	List<Customer> getAll();

	public QueryResult pageQuery(int startindex,int pagesize);
}