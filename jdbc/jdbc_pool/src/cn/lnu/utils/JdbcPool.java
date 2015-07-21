package cn.lnu.utils;

import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import cn.lnu.utils.JdbcUtils_DBCP;
//写一个数据库连接池，这个程序是每次启动先向数据库要一批数据库连接，然后使用一个集合来管理这批数据库连接
public class JdbcPool implements DataSource {
	
	private static LinkedList<Connection> list=new LinkedList();//由于数据库连接池涉及到大量的增删改查操作，这里使用LinkedList链表结构的集合来维护数据库连接
	
	private static Properties config=new Properties();
	//静态代码块，只加载一次mysql驱动，读取一次配置文件
	static{//使用静态代码块只在程序类初始化启动的时候，向数据库要一批数据库连接
		try{
		//读配置文件db.properties
		config.load(JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream("db.properties"));
		Class.forName(config.getProperty("driver"));//加载数据库驱动
		for(int i=0;i<10;i++){
			Connection conn=DriverManager.getConnection(config.getProperty("url"), config.getProperty("username"), config.getProperty("password"));
			list.add(conn);
		}
		}catch(Exception e){
			//抛一个初始化错误异常
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/*//由于conn.close();方法默认是将数据库连接还给数据库而不是连接池，这里就说明Connection这个类的close方法的功能满足不了需求了，需要增强
	 * 在实际开发中,发现对象的方法满足不了开发需求时，有三种方式对其增强，
	 * 1.写一个connection子类，覆盖close方法，增强close方法
	 * 2.用包装设计模式（这种方式也比较好）
	 * 3.使用动态代理(终极解决方式)  aop面向切面编程
	 * */
	public  Connection getConnection() throws SQLException {//使用这个方法向数据库连接池申请连接
		// TODO Auto-generated method stub
		if(list.size()<=0){//表示数据库连接池中没有连接了
			throw new RuntimeException("数据库正忙,请稍后再来！");
		}
		Connection conn=list.removeFirst();//如果连接池中有连接，就始终从连接池中拿出第一个连接，然后删除该链接
		
		MyConnection myconn=new MyConnection(conn);
		return myconn;
	}

	//这里采用包装设计模式增强conn对象，一般步骤如下：
	//1.定义一个类，实现与被增强对象相同的接口
	//2.在类中定义一个变量，记住被增强对象
	//3.定义一个构造函数，接受被增强对象
	//4.覆盖想增强的方法
	//5.对于不想增强的方法，直接调用目标对象(被增强对象)的方法
	class MyConnection implements Connection{
		
		private Connection conn;

		public MyConnection(Connection conn){
			this.conn=conn;
		}

		public void close() throws SQLException {//连接使用完之后，还给连接池，而不是数据库
			// TODO Auto-generated method stub
			list.add(this.conn);
		}

		public void clearWarnings() throws SQLException {
			// TODO Auto-generated method stub
			this.clearWarnings();
		}

		public void commit() throws SQLException {
			// TODO Auto-generated method stub
			this.commit();
		}

		public Statement createStatement() throws SQLException {
			// TODO Auto-generated method stub
			return this.createStatement();
		}

		public Statement createStatement(int resultSetType,
				int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public Statement createStatement(int resultSetType,
				int resultSetConcurrency) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean getAutoCommit() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		public String getCatalog() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public int getHoldability() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		public DatabaseMetaData getMetaData() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public int getTransactionIsolation() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		public Map<String, Class<?>> getTypeMap() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public SQLWarning getWarnings() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean isClosed() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isReadOnly() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		public String nativeSQL(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public CallableStatement prepareCall(String sql, int resultSetType,
				int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public CallableStatement prepareCall(String sql, int resultSetType,
				int resultSetConcurrency) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public CallableStatement prepareCall(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public PreparedStatement prepareStatement(String sql,
				int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public PreparedStatement prepareStatement(String sql,
				int resultSetType, int resultSetConcurrency)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public PreparedStatement prepareStatement(String sql,
				int autoGeneratedKeys) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public PreparedStatement prepareStatement(String sql,
				int[] columnIndexes) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public PreparedStatement prepareStatement(String sql,
				String[] columnNames) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public PreparedStatement prepareStatement(String sql)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		public void rollback() throws SQLException {
			// TODO Auto-generated method stub
			
		}

		public void rollback(Savepoint savepoint) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		public void setAutoCommit(boolean autoCommit) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		public void setCatalog(String catalog) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		public void setHoldability(int holdability) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		public void setReadOnly(boolean readOnly) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		public Savepoint setSavepoint() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public Savepoint setSavepoint(String name) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		public void setTransactionIsolation(int level) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			// TODO Auto-generated method stub
			
		}
		
	}
	public Connection getConnection(String arg0, String arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

}
