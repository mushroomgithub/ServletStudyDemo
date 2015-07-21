package cn.lnu.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	private static DataSource ds=null;
	static{
		InputStream in=JdbcUtils.class.getClassLoader().getResourceAsStream("c3p0-config.xml");
		ds=new ComboPooledDataSource();//使用默认的c3p0配置文件创建一个数据库连接池
	}
	
	public static DataSource getDataSource(){
		return ds;
	}
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
}
