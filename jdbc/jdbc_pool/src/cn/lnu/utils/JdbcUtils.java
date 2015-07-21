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
	
	//在工具类中抽取增删改操作的公共代码，它们都是只有sql语句和参数不同,这里将其公共部分抽取到一个方法中
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
//框架设计者不知道如何处理结果集，而用户知道，所以这里框架设计者对外暴露一个接口，由用户调用实现，返回用户处理之后的结果集
interface ResultSetHandler{
	public Object handler(ResultSet rs);
}

//框架设计者帮我们事先事先几个实际开发中经常使用的处理器，由于框架设计者不知道将结果集处理到什么对象或者集合中所以在实现接口的时候创建一个构造函数接收用户想要将结果集处理到什么对象或者集合中
class BeanHandler implements ResultSetHandler{//预先实现一个将结果集处理到一个bean对象中的类
	private Class clazz;
	public BeanHandler(Class clazz){
		this.clazz=clazz;//接收用户传递过来的类，这里记住这个参数，框架设计者根据这个类，将结果集封装处理到这个类的对象中返回
	}
	public Object handler(ResultSet rs) {
		try {
				if(!rs.next()){//如果结果集中没有数据，无需处理直接返回null
					return null;
				}
				//有数据，创建封装结果集的bean
				Object bean=clazz.newInstance();
				//由于框架设计者不知道结果集中都有什么数据，这就需要用到结果集元数据了,
				ResultSetMetaData meta=rs.getMetaData();//得到结果集的元数据，以获得结果集的信息
				int count=meta.getColumnCount();//获得结果集上总的列数
				for(int i=0;i<count;i++){
					String name=meta.getColumnName(i+1);//获得结果集每列的列名（结果集列下表从1开始）
					Object value=rs.getObject(name);//获得每列列名对应的值
					
					//通过每次取得的结果集列名反射出bean上与该列名对应的属性
					Field f=bean.getClass().getDeclaredField(name);
					f.setAccessible(true);//由于bean对象类在设计时，属性一般为私有，这里强制设为公有使得外界可以操作对象属性名
					f.set(bean, value);//将反射出的属性名为f的值保存到bean对象对应属性上
				}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
//将结果集每一条记录处理先处理到一个bean中，然后在将多个bean加到一个List集合中，需实现框架设计者暴露的handler接口处理器
class BeanListHandler implements ResultSetHandler{
	private Class clazz;
	public BeanListHandler(Class clazz){//传需要将结果集每一行记录封装到哪个类的对象中的类过来，这样用户使用这个处理器的时候，就不必要像以前那样先new出一个对象在传递过来，而是直接传递过来一个类
		this.clazz=clazz;
	}
	public Object handler(ResultSet rs) {
		List list=new ArrayList();
		try {
			while(rs.next()){
				Object bean=clazz.newInstance();//每取出一条记录就new出一条该类的bean对象
				ResultSetMetaData meta=rs.getMetaData();
				int count=meta.getColumnCount();
				for(int i=0;i<count;i++){
					String name=meta.getColumnName(i+1);//每次取出结果集每一列的列名
					Object value=rs.getObject(name);
					
					//反射，通过每次取出的列名反射出bean对象上对应的属性
					Field f=bean.getClass().getDeclaredField(name);
					f.setAccessible(true);//强制设置该属性为公有访问属性，打开该属性的访问权限，让这个属性上可以被赋值
					f.set(bean, value);//将结果集每列的值赋值到bean对象对应属性上	
				}
				list.add(bean);//将每次反射出的bean添加到一个list集合中
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}