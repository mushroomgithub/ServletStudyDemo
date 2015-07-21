package cn.lnu.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import cn.lnu.utils.JdbcUtils;

//测试jdbc得批处理
/*
 	create table testbatch
 	(
 		id varchar(40) primary key,
 		name varchar(40)
 	);
 * */
public class Demo3 {
	@Test
	public void test1() throws SQLException{//实现批处理得第一种方式，可以发不同类型的sql，可以执行多种操作
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtils.getConnection();
			String sql1="insert into testbatch(id,name) values(1,'mushroom')";
			String sql2="update testbatch set name='mogu' where id='1'";
			st=conn.createStatement();//Statement内部维护了一个list集合
			st.addBatch(sql1);
			st.addBatch(sql2);
			st.executeBatch();//向数据库发送一批sql
			st.clearBatch();
			
		}finally{
			JdbcUtils.release(conn, st, rs);
			
		}
	}
	
	@Test
	public void test2() throws SQLException{//jdbc批处理得第二种方式，只能发一种类型的sql，只适合做批量的插入和批量更新操作
		long starttime=System.currentTimeMillis();
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtils.getConnection();
			String sql="insert into testbatch(id,name) values(?,?)";
			st=conn.prepareStatement(sql);
			//相当于向st中加入了十条sql语句
			for(int i=1;i<=100;i++){
				st.setString(1, i+"");
				st.setString(2, "mushroom"+i);
				st.addBatch();
				if(i%1000==0){
					st.executeBatch();
					st.clearBatch();
				}
			}
			st.executeBatch();
			st.clearBatch();
		}finally{
			JdbcUtils.release(conn, st, rs);
			
		}	
		long endtime=System.currentTimeMillis();
		System.out.println("总共用时："+(endtime-starttime)/1000+"秒");
	}
	
}
