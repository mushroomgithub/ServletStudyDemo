package cn.lnu.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.lnu.domain.User;
import cn.lnu.utils.JdbcUtils;

//对于以下几个方法有一些公共代码，这里将其抽取到一个工具类中，位于新创建的cn.lnu.utils包下JdbcUtils类
public class jdbc_demo3 {
	Connection conn=null;
	Statement st=null;
	ResultSet rs=null;
	@Test
	//操作数据库的插入操作
	public void insert() throws SQLException{
		try{
		    conn=JdbcUtils.getConnection();
			st=conn.createStatement();
			String sql="insert into users(id,name,password,email,birthday) values(4,'mushroom','123456','mogu@163.com','1989-10-24')";
			int num=st.executeUpdate(sql);
			if(num>0){
				System.out.println("插入成功！");
			}
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	@Test
	//操作数据库的修改操作
	public void update() throws SQLException{
		try{
			conn=JdbcUtils.getConnection();
			st=conn.createStatement();
			String sql="update users set name='mogu' where id='4'";
			int num=st.executeUpdate(sql);
			if(num>0){
				System.out.print("更新数据表成功！");
			}
			
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	@Test
	//操作数据库的删除操作
	public void delete() throws SQLException{
		try{
			conn=JdbcUtils.getConnection();
			st=conn.createStatement();
			String sql="delete from users where id='4'";
			int num=st.executeUpdate(sql);
			if(num>0){
				System.out.print("删除数据表数据成功！");
			}
			
			
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	@Test
	//操作数据库的查询操作
	public void find() throws SQLException{
		try{
			conn=JdbcUtils.getConnection();
			st=conn.createStatement();
			String sql="select * from users where id='2'";
			rs=st.executeQuery(sql);
			while(rs.next()){
				System.out.println("id="+rs.getInt("id"));
				System.out.println("name="+rs.getString("name"));
				System.out.println("password="+rs.getString("password"));
				System.out.println("email="+rs.getString("email"));
				System.out.println("birthday="+rs.getDate("birthday"));
				System.out.println("------------------------------");
			}
			
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	@Test
	//操作数据库获得所有纪录
	public void getAll() throws SQLException{
		try{
			conn=JdbcUtils.getConnection();
			st=conn.createStatement();
			String sql="select * from users";
			rs=st.executeQuery(sql);
			
			List list=new ArrayList();//将取得的每一条记录保存到一个bean对象中，然后将这个bean对象存在list中
			while(rs.next()){
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setBirthday(rs.getDate("birthday"));
				list.add(user);
			}
			
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
}
