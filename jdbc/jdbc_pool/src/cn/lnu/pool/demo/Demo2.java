package cn.lnu.pool.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.lnu.utils.JdbcUtils_C3P0;
import cn.lnu.utils.JdbcUtils_DBCP;

public class Demo2 {

	/**
	 测试使用C3P0数据源创建数据库连接池为用户维护一批连接供其申请的方式
	 它是使用动态代理的方式将数据库连接在调用close方法时，归还连接给数据库连接池而不是数据库
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtils_C3P0.getConnection();//底层是从c3p0数据库连接池中要的连接
			System.out.println(conn);//打印出这个数据库连接，说明从数据库连接池中成功获得了连接
			System.out.println(conn.getClass().getName());//返回这个connection对象的动态代理名
		}finally{
			JdbcUtils_C3P0.release(conn, st, rs);
		}
	}

}
