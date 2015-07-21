package cn.lnu.dao.impl;
//记得这个实现类写完之后，将实现类的方法重构到一个接口包中，具体方法是在CustomerDaoImpl.java单击鼠标右键，选择Refactor-->Extract Interface,勾选这几个方法，输入接口类的名，这里为CustomerDao，然后在CustomerDao.java上右键,Refactor-->Move,将这个接口移动到接口包cn.lnu.dao中
import java.util.List;

import cn.lnu.dao.CustomerDao;
import cn.lnu.domain.Customer;
import cn.lnu.domain.QueryResult;
import cn.lnu.exception.DaoException;
import cn.lnu.utils.BeanHandler;
import cn.lnu.utils.BeanListHandler;
import cn.lnu.utils.IntHandler;
import cn.lnu.utils.JdbcUtils;

//根据用户需求,创建数据层需要提供的接口
public class CustomerDaoImpl implements CustomerDao {
	public void add(Customer c){
		try{
			String sql="insert into customer(id,name,gender,birthday,cellphone,email,preference,type,description) values(?,?,?,?,?,?,?,?,?)";
			Object params[]={c.getId(),c.getName(),c.getGender(),c.getBirthday(),c.getCellphone(),c.getEmail(),c.getPreference(),c.getType(),c.getDescription()};
			JdbcUtils.update(sql, params);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	
	public void update(Customer c){
		try{
			String sql="update customer set name=?,gender=?,birthday=?,cellphone=?,email=?,preference=?,type=?,description=? where id=?";
			Object params[]={c.getName(),c.getGender(),c.getBirthday(),c.getCellphone(),c.getEmail(),c.getPreference(),c.getType(),c.getDescription(),c.getId()};
			JdbcUtils.update(sql, params);
		}catch(Exception e){
			throw new DaoException(e);
		}	
	}
	
	public void delete(String id){
		try{
			String sql = "delete from customer where id=?";
			Object params[]={id};
			JdbcUtils.update(sql, params);
		}catch(Exception e){
			throw new DaoException(e);
		}	
		
	}
	
	public Customer find(String id){//根据用户主键id返回一个用户
		try{
			String sql="select * from customer where id=?";
			Object params[]={id};
			return (Customer) JdbcUtils.query(sql, params, new BeanHandler(Customer.class));
		}catch(Exception e){
			throw new DaoException(e);
		}	
		
	}
	
	//取得数据库中所有用户，最后保存到一个list集合中
	public List<Customer> getAll(){
		try{
			String sql="select * from customer";
			Object params[]={};
			return (List<Customer>) JdbcUtils.query(sql, params, new BeanListHandler(Customer.class));
		}catch(Exception e){
			throw new DaoException(e);
		}	
		
	}
	
	//实现分页,添加这个函数，返回页面查询数据和页面大小
	public QueryResult pageQuery(int startindex,int pagesize){
		QueryResult qr=new QueryResult();
		
		try{
			String sql="select * from customer limit ?,?";
			Object params[]={startindex,pagesize};
			List list=(List) JdbcUtils.query(sql, params, new BeanListHandler(Customer.class));
			qr.setList(list);
			
			sql="select count(*) from customer";
			params=new Object[]{};
			int totalrecord=(Integer) JdbcUtils.query(sql, params, new IntHandler());
			qr.setTotalrecord(totalrecord);
			return qr;
		}catch(Exception e){
			throw new DaoException(e);
		}	
	}
}
