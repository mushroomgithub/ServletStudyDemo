package cn.lnu.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import cn.lnu.utils.JdbcUtils_C3P0;


//获得数据库的元数据
public class Demo1 {
	public static void main(String[] args) throws SQLException{	
			test1();
	}

	public static void test1() throws SQLException {
		Connection conn=JdbcUtils_C3P0.getConnection();
		DatabaseMetaData meta=conn.getMetaData();
		System.out.println(meta.getDatabaseProductName());
		System.out.println(meta.getDatabaseProductVersion());
		System.out.println(meta.getURL());
		System.out.println(meta.getDriverName());
		System.out.println(meta.getDriverVersion());
	}
}
