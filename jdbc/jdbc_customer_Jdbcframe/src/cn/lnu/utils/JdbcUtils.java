package cn.lnu.utils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
//使用dbcp数据源构建数据库连接池的方式为用户维护一批连接
public class JdbcUtils {
	private static DataSource ds=null;//先申明一个数据库连接池，用户记住从工厂类中创建的连接池
	//静态代码块，只加载一次mysql驱动，读取一次配置文件
	static{
		try{
			InputStream in=JdbcUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");//将配置文件读到一个流中
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
	
	//在工具类中抽取增删改操作的公共代码，它们都是只有sql语句和参数不同,这里将其公共部分抽取到一个方法中(对增删改的优化)
	public static void update(String sql,Object params[]) throws SQLException{
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try{
			conn=getConnection();
			st=conn.prepareStatement(sql);
			//使用传递过来的参数数组填充传递过来的sql语句，使其成为一条完整的sql语句
			for(int i=0;i<params.length;i++){
				st.setObject(i+1, params[i]);
			}
			st.executeUpdate();
		}finally{
			release(conn, st, rs);
		}
	}
	
	//工具类中实现对查询操作公共代码抽取的查询优化
	public static Object query(String sql,Object params[],ResultSetHandler handler) throws SQLException{
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try{
			conn=getConnection();
			st=conn.prepareStatement(sql);
			//使用传递过来的参数数组填充传递过来的sql语句，使其成为一条完整的sql语句
			for(int i=0;i<params.length;i++){
				st.setObject(i+1, params[i]);
			}
			rs=st.executeQuery();
			//处理结果集
			return handler.handler(rs);//使用用户传递过来的自己实现框架暴露接口的结果集处理器处理结果集，这里可能是将结果集合处理到一个list集合或者一个javabean中
		}finally{
			release(conn, st, rs);
		}
	}
}
