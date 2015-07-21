package cn.lnu.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;
//使用c3p0(spring框架使用的方式)数据源构建数据库连接池的方式为用户维护一批连接
public class JdbcUtils_C3P0 {
	private static ComboPooledDataSource ds=null;
	//静态代码块，只加载一次mysql驱动，读取一次配置文件
	static{
		try {
			ds=new ComboPooledDataSource();//创建一个c3p0数据库连接池，设置默认从c3p0-config.xml配置文件中读取配置信息初始化这个数据库连接池，ComboPooledDataSource内部会自动搜索名字叫c3p0-config.xml的培植物，从中获得配置信息
			/*//初始化这个数据库连接池
			ds.setDriverClass("com.mysql.jdbc.Driver");//告诉c3p0使用的数据库驱动
			ds.setJdbcUrl("jdbc:mysql://localhost:3306/jdbc_customer");//设置连接的数据库url
			ds.setUser("root");
			ds.setPassword("root");
			ds.setMaxPoolSize(30);//设置数据库连接池最大30个连接
			ds.setMinPoolSize(5);//设置数据库连接池最小5个连接
			ds.setInitialPoolSize(10);//设置数据库连接池初始创建10个连接
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
