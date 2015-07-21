package cn.lnu.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import cn.lnu.utils.JdbcUtils;

public class Demo2 {

	/**
	 *实现a用户向b转100元,多条sql操作数据库使用事务，要么全做，要么全不做
	 		*事务回滚点*
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
		Savepoint sp=null;//java中代表回滚点的对象
		try{
			conn=JdbcUtils.getConnection();//获得链接事务是自动提交的，要操作事务，需要在这个链接上设置事务不自动提交，并开启事务
			
			conn.setAutoCommit(false);//设置在这个数据库链接上事务不自动提交，相当于数据库中的start transaction开启事务操作
			
			String sql1="update account set money=money-1000 where name='aaa'";
			String sql2="update account set money=money+1000 where name='bbb'";
			String sql3="update account set money=money+1000 where name='ccc'";
			
			st=conn.prepareStatement(sql1);
			st.executeUpdate();
			
			sp=conn.setSavepoint();//设置回滚点
			
			st=conn.prepareStatement(sql2);
			st.executeUpdate();
			
			int x=1/0;//异常
			
			st=conn.prepareStatement(sql3);
			st.executeUpdate();
			
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback(sp);
			conn.commit();//手动回滚后，一定要记住提交事务
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}

}
