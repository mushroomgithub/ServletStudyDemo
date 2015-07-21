package cn.lnu.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.lnu.utils.JdbcUtils;

public class Demo1 {

	/**
	 *实现a用户向b转100元,多条sql操作数据库使用事务，要么全做，要么全不做
	 * @throws SQLException 
	 * 事务操作
	 * start transaction 开启事务
	 * ......
	 * ......
	 * [rollback]
	 * commit 提交事务，只要没有commit，数据库就会自动回滚sql操作对数据库的影响，恢复到事务执行前的状态
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtils.getConnection();//获得链接事务是自动提交的，要操作事务，需要在这个链接上设置事务不自动提交，并开启事务
			conn.setAutoCommit(false);//设置在这个数据库链接上事务不自动提交，相当于数据库中的start transaction开启事务操作
			
			String sql1="update account set money=money-1000 where name='aaa'";
			String sql2="update account set money=money+1000 where name='bbb'";
			
			st=conn.prepareStatement(sql1);
			st.executeUpdate();
			
			//int x=1/0;//遇到异常自动回滚事务
			
			st=conn.prepareStatement(sql2);
			st.executeUpdate();
			conn.commit();
			
			
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}

}
