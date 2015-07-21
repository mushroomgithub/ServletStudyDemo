package cn.lnu.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

import cn.lnu.utils.JdbcUtils_C3P0;

//测试如何获得元数据信息
public class Demo2 {
	public static void main(String[] args) throws SQLException {
		test1();
		test2();
		test3();
	}
	/**
	 * 数据库元数据
	 * @throws SQLException 
	 */
	public static void test1() throws SQLException {
		Connection conn=JdbcUtils_C3P0.getConnection();
		DatabaseMetaData meta=conn.getMetaData();
		System.out.println(meta.getDatabaseProductName());
		System.out.println(meta.getDatabaseProductVersion());
		System.out.println(meta.getURL());
		System.out.println(meta.getDriverName());
		System.out.println(meta.getDriverVersion());
	}
	
	/**
	 * 参数元数据
	 * @throws SQLException 
	 */
	public static void test2() throws SQLException {
		Connection conn=JdbcUtils_C3P0.getConnection();
		String sql="insert into user(id,name) values(?,?)";
		PreparedStatement st=conn.prepareStatement(sql);
		ParameterMetaData meta=st.getParameterMetaData();
		System.out.println(meta.getParameterCount());
	}

	/**
	 * 结果集元数据
	 * @throws SQLException 
	 */
	@Test
	public static void test3() throws SQLException {
		Connection conn=JdbcUtils_C3P0.getConnection();
		String sql="select * from customer";
		PreparedStatement st=conn.prepareStatement(sql);
		ResultSet rs=st.executeQuery();
		ResultSetMetaData meta=rs.getMetaData();
		System.out.println(meta.getColumnCount());
		System.out.println(meta.getColumnClassName(1));
		System.out.println(meta.getColumnClassName(2));
		System.out.println(meta.getColumnClassName(3));
		System.out.println(meta.getColumnName(1));
		System.out.println(meta.getColumnName(2));
		System.out.println(meta.getColumnName(3));
	}
}
