package cn.lnu.dbutils.demo;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.lnu.domain.Account;
import cn.lnu.utils.JdbcUtils;

//使用dbutils框架进行事物处理
public class AccountDao {
	
	private Connection conn;//记住通过构造函数获得service层处理业务逻辑传递进来数据库连接，在操作事务时，将涉及到crud操作的sql，都在这个连接上执行
	public AccountDao() {
		super();
	}
	public AccountDao(Connection conn){
		this.conn=conn;
	}
	public void update(Account a){
		try {
			QueryRunner runner=new QueryRunner();
			String sql="update account set money=? where id=?";
			Object params[]={a.getMoney(),a.getId()};
			runner.update(JdbcUtils.getConnection(),sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Account find(int id){
		try {
			QueryRunner runner=new QueryRunner();
			String sql="select * from account where id=?";
			return (Account) runner.query(JdbcUtils.getConnection(),sql, id, new BeanHandler(Account.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//模拟从a-->b账户转账100元
	public void transfer() throws SQLException{
		Connection conn=null;
		try{
			
			conn=JdbcUtils.getConnection();
			conn.setAutoCommit(false);//在连接上开启事务
			
			QueryRunner runner=new QueryRunner();
			String sql1="update account set money=money-100 where name='aaa'";
			runner.update(conn, sql1);//把开启事务的连接传递给update，runner会将这条sql以事务的方式执行
			
			String sql2="update account set money=money+100 where name='bbb'";
			runner.update(conn, sql2);//这样就将两条sql放在同一个开启事务的连接下执行了
			
			conn.commit();
		}finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
}
