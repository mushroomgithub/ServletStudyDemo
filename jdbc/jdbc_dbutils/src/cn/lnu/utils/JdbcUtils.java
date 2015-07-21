package cn.lnu.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

//工具类，使用dbcp创建使用dbutils框架时需要数据库连接池，这里也不需再写relesse方法了，因为dbutils开源框架会自动为我们释放链接
public class JdbcUtils {
	private static DataSource ds=null;
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();//ThreadLocal内部维护的是一个map集合，以当前线程thread为key,set函数绑定的值为value
	static{
		try{
			Properties dbcpconfig=new Properties();
			InputStream in=JdbcUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			dbcpconfig.load(in);
			BasicDataSourceFactory factory=new BasicDataSourceFactory();
			ds=factory.createDataSource(dbcpconfig);
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static DataSource getDataSource(){
		return ds;
	}
	
	
	public static Connection getConnection() throws SQLException{//这样就能保证获得的连接始终是绑定到当前线程上的连接了
		try{
			//得到当前线程上绑定连接
			Connection conn=tl.get();
			if(conn==null){//代表线程是第一次启动，线程上没有绑定连接
				conn=ds.getConnection();//从数据库连接池获取一个连接
				tl.set(conn);//将连接绑定到当前线程
			}
			return conn;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	//使用ThreadLocal开启事务
	public static void startTransaction(){
		try{
			//得到当前线程上绑定连接
			Connection conn=tl.get();
			if(conn==null){//代表线程是第一次启动，线程上没有绑定连接
				conn=ds.getConnection();//从数据库连接池获取一个连接
				tl.set(conn);//将连接绑定到当前线程
			}
			conn.setAutoCommit(false);//将绑定到当前线程上的连接开启事务
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	//使用TreadLocal提交事务
	public static void commitTransaction(){
		try{
			//得到当前线程绑定的连接
			Connection conn=tl.get();
			if(conn!=null){
				conn.commit();
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static void closeConnection(){
		try{
			//得到当前线程绑定的连接
			Connection conn=tl.get();
			if(conn!=null){
				conn.close();//关闭连接，将连接还给连接池
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			tl.remove();//千万注意，解除当前线程上绑定的连接(从ThreadLocal容器中移除对应当前线程的连接)，这个remove会以当前线程为key,移除掉绑定到这个线程上的连接
		}
	}
	
}
