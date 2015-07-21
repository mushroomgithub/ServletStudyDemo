package cn.lnu.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.lnu.domain.Account;

public class Demo3 {
	@Test
	public void testadd() throws SQLException{
		Account a=new Account();
		a.setName("ccc");
		a.setMoney(10000);
		add(a);
	}
	
	@Test
	public void testdelete() throws SQLException{
		
		delete(1);
	}
	
	
	@Test
	public void testupdate() throws SQLException{
		Account a=new Account();
		a.setId(2);
		a.setName("bbb");
		a.setMoney(100);
		update(a);
	}
	
	@Test
	public void testfind() throws SQLException{
		Account a=find(2);
		System.out.println(a.getId());
		System.out.println(a.getName());
		System.out.println(a.getMoney());
	}
	@Test
	public void testgetAll() throws SQLException{
		List list=getAll();
		
	}
	
	public void add(Account a) throws SQLException{
		String sql="insert into account(name,money) values(?,?)";
		Object params[]={a.getName(),a.getMoney()};
		JdbcUtils.update(sql, params);
	}
	
	public void delete(int id) throws SQLException{
		String sql="delete from account where id=?";
		Object params[]={id};
		JdbcUtils.update(sql, params);
	}
	public void update(Account a) throws SQLException{
		String sql="update account set name=?,money=? where id=?";
		Object params[]={a.getName(),a.getMoney(),a.getId()};
		JdbcUtils.update(sql, params);
	}
	//≤È—Ø
	public Account find(int id) throws SQLException{
	
		String sql="select * from account where id=?";
		Object params[]={id};
		Account a=(Account) JdbcUtils.query(sql, params, new BeanHandler(Account.class));
		return a;
	}
	
	public List getAll() throws SQLException{
		String sql="select * from account";
		Object params[]={};
		List list=(List) JdbcUtils.query(sql, params, new BeanListHandler(Account.class));
		return list;
	}
}
