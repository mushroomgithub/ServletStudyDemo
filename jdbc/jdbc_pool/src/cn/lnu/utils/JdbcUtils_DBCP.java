package cn.lnu.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
//使用dbcp数据源构建数据库连接池的方式为用户维护一批连接
public class JdbcUtils_DBCP {
	private static DataSource ds=null;//先申明一个数据库连接池，用户记住从工厂类中创建的连接池
	//静态代码块，只加载一次mysql驱动，读取一次配置文件
	static{
		try{
			InputStream in=JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");//将配置文件读到一个流中
			Properties dbcpconfig=new Properties();
			dbcpconfig.load(in);
			
			BasicDataSourceFactory factory=new BasicDataSourceFactory();//new一个连接池工厂
			ds=factory.createDataSource(dbcpconfig);//根据dbcp配置文件创建一个数据库连接池
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
	
	
	//从数据库连接池ds中获得一个数据库连接
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	public static void release(Connection conn,Statement st,ResultSet rs){
		if(rs!=null){
			try{
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			rs=null;
		}
		if(st!=null){
			try{
				st.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			st=null;
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			conn=null;
		}
	}
}
