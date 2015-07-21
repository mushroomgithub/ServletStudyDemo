package cn.lnu.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtils_JNDI {
	private static DataSource ds=null;
	static{
		try{
			Context initCtx = new InitialContext();//初始化jndi
			Context envCtx = (Context) initCtx.lookup("java:comp/env");//查找到jndi容器
			ds = (DataSource) envCtx.lookup("jdbc/EmployeeDB");//从容器中查找到绑定到指定jndi源上的数据库连接池并返回
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
}