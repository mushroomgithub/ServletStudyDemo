package cn.lnu.dao.impl;
//记得这个实现类写完之后，将实现类的方法重构到一个接口包中，具体方法是在CustomerDaoImpl.java单击鼠标右键，选择Refactor-->Extract Interface,勾选这几个方法，输入接口类的名，这里为CustomerDao，然后在CustomerDao.java上右键,Refactor-->Move,将这个接口移动到接口包cn.lnu.dao中
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.lnu.dao.CustomerDao;
import cn.lnu.domain.Customer;
import cn.lnu.domain.QueryResult;
import cn.lnu.exception.DaoException;
import cn.lnu.utils.JdbcUtils;

//根据用户需求,创建数据层需要提供的接口
public class CustomerDaoImpl implements CustomerDao {
	public void add(Customer c){
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try{
			conn=JdbcUtils.getConnection();
			String sql="insert into customer(id,name,gender,birthday,cellphone,email,preference,type,description) values(?,?,?,?,?,?,?,?,?)";
			st=conn.prepareStatement(sql);
			//填充参数
			st.setString(1, c.getId());
			st.setString(2, c.getName());
			st.setString(3, c.getGender());
			st.setDate(4, new java.sql.Date(c.getBirthday().getTime()));
			st.setString(5, c.getCellphone());
			st.setString(6, c.getEmail());
			st.setString(7, c.getPreference());
			st.setString(8, c.getType());
			st.setString(9, c.getDescription());
			
			int num=st.executeUpdate();
			if(num>0){
				System.out.println("插入新用户成功！");
			}	
		}catch(Exception e){
			throw new DaoException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	public void update(Customer c){
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try{
			conn=JdbcUtils.getConnection();
			String sql="update customer set name=?,gender=?,birthday=?,cellphone=?,email=?,preference=?,type=?,description=? where id=?";
			st=conn.prepareStatement(sql);
			st.setString(1, c.getName());
			st.setString(2, c.getGender());
			st.setDate(3, new java.sql.Date(c.getBirthday().getTime()));
			st.setString(4, c.getCellphone());
			st.setString(5, c.getEmail());
			st.setString(6, c.getPreference());
			st.setString(7, c.getType());
			st.setString(8, c.getDescription());
			st.setString(9, c.getId());
			
			int num=st.executeUpdate();
			if(num>0){
				System.out.print("更新用户成功！");
			}
		}catch(Exception e){
			throw new DaoException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	public void delete(String id){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtils.getConnection();
			String sql = "delete from customer where id=?";
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			int num=st.executeUpdate();
			if(num>0){
				System.out.println("删除数据成功！");
			}
		}catch (Exception e) {
			throw new DaoException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	public Customer find(String id){//根据用户主键id返回一个用户
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try{
			conn=JdbcUtils.getConnection();
			String sql="select * from customer where id=?";
			st=conn.prepareStatement(sql);
			st.setString(1, id);
			
			rs=st.executeQuery();
			if(rs.next()){
				Customer c=new Customer();
				c.setBirthday(rs.getDate("birthday"));
				c.setCellphone(rs.getString("cellphone"));
				c.setDescription(rs.getString("description"));
				c.setEmail(rs.getString("email"));
				c.setGender(rs.getString("gender"));
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setPreference(rs.getString("preference"));
				c.setType(rs.getString("type"));
				
				return c;
			}
			return null;
		}catch(Exception e){
			throw new DaoException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	//取得数据库中所有用户，最后保存到一个list集合中
	public List<Customer> getAll(){
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		List<Customer> list=new ArrayList();
		try{
			conn=JdbcUtils.getConnection();
			String sql="select * from customer";
			st=conn.prepareStatement(sql);
			rs=st.executeQuery();
			while(rs.next()){
				Customer c=new Customer();
				c.setBirthday(rs.getDate("birthday"));
				c.setCellphone(rs.getString("cellphone"));
				c.setDescription(rs.getString("description"));
				c.setEmail(rs.getString("email"));
				c.setGender(rs.getString("gender"));
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setPreference(rs.getString("preference"));
				c.setType(rs.getString("type"));
				list.add(c);
			}
			return list;
		}catch(Exception e){
			throw new DaoException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	//实现分页,添加这个函数，返回页面查询数据和页面大小
	public QueryResult pageQuery(int startindex,int pagesize){
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		QueryResult qr=new QueryResult();
		try{
			conn=JdbcUtils.getConnection();
			String sql="select * from customer limit ?,?";
			st=conn.prepareStatement(sql);
			st.setInt(1, startindex);
			st.setInt(2, pagesize);
			rs=st.executeQuery();
			List<Customer> list=new ArrayList();
			while(rs.next()){
				Customer c=new Customer();
				c.setBirthday(rs.getDate("birthday"));
				c.setCellphone(rs.getString("cellphone"));
				c.setDescription(rs.getString("description"));
				c.setEmail(rs.getString("email"));
				c.setGender(rs.getString("gender"));
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setPreference(rs.getString("preference"));
				c.setType(rs.getString("type"));
				list.add(c);
			}
			
			qr.setList(list);
			//插叙总记录数
			sql="select count(*) from customer";
			st=conn.prepareStatement(sql);
			rs=st.executeQuery();
			if(rs.next()){
				qr.setTotalrecord(rs.getInt(1));
			}
			return qr;
		}catch(Exception e){
			throw new DaoException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
}
