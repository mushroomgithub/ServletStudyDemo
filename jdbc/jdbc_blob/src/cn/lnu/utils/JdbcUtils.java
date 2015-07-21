package cn.lnu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//CustomerDaoImpl类五个方法的公共代码部分抽象出来的工具类，主要完成从配置文件中读取信息，创建数据库连接，以及最后释放资源的公共代码
public class JdbcUtils {
	//下面这部分为了不再程序中写死，我将下面三个数据库连接信息写到一个db.properties（在src目录下创建这个配置文件）配置文件中
	/*private static final String url="jdbc:mysql://localhost:3306/jdbc_demo";
	private static final String user="root";
	private static final String password="root";*/
	
	private static Properties config=new Properties();
	//静态代码块，只加载一次mysql驱动，读取一次配置文件
	static{
		try{
		//读配置文件db.properties
		config.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));
		Class.forName(config.getProperty("driver"));
		}catch(Exception e){
			//抛一个初始化错误异常
			throw new ExceptionInInitializerError(e);
		}
	}
	
	
	//获得一个数据库连接
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(config.getProperty("url"),config.getProperty("username"),config.getProperty("password"));
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
