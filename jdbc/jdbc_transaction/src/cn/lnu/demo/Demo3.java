package cn.lnu.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import cn.lnu.utils.JdbcUtils;
//测试事务隔离性涉及的四种级别对脏读，不可重复读，虚读问题
public class Demo3 {
	public static void main(String[] args) throws SQLException, InterruptedException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		Savepoint sp=null;//java中代表回滚点的对象
		try{
			conn=JdbcUtils.getConnection();//mysql 默认获得是不可重复读的数据库连接
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);//可避免所有问题的隔离级别
			conn.setAutoCommit(false);//取消事务默认提交功能，改为手动提交方式，并开始事务
			String sql1="select * from account";
			conn.prepareStatement(sql1).executeQuery();
			
			//程序休眠20秒
			Thread.sleep(20*1000);
			
			conn.commit();
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
}
