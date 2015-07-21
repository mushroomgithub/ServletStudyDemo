package cn.lnu.pool.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.lnu.utils.JdbcUtils_DBCP;

public class Demo1 {

	/**
	 测试使用dbcp数据源创建数据库连接池为用户维护一批连接供其申请的方式
	 它是使用包装类(内部增强了close方法)的方式将数据库连接在调用close方法时，归还连接给数据库连接池而不是数据库
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtils_DBCP.getConnection();//底层是从dbcp数据库连接池中要的连接
			//System.out.println(conn);//打印出这个数据库连接，说明从数据库连接池中成功获得了连接
			System.out.println(conn.getClass().getName());//返回这个connection对象的包装类名
		}finally{
			JdbcUtils_DBCP.release(conn, st, rs);
		}
	}

}
