package cn.lnu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.lnu.dao.UserDao;
import cn.lnu.domain.User;
import cn.lnu.exception.DaoException;
import cn.lnu.utils.JdbcUtils;

public class UserDaoJdbcImpl implements UserDao{
	
	//添加用户
	/*public void addUser(User user) {
		// TODO Auto-generated method stub
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			conn=JdbcUtils.getConnection();
			st=conn.createStatement();
			String sql="insert into users(id,username,password,email,birthday,nickname) values('"+user.getId()+"','"+user.getUsername()+"','"+user.getPassword()+"','"+user.getEmail()+"','"+user.getBirthday().toLocaleString()+"','"+user.getNickname()+"')";
			int num=st.executeUpdate(sql);
			if(num<1){
				throw new RuntimeException("注册用户失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}*/
	
	//使用preparedStatement进行改造，防止sql注入
	public void addUser(User user) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try {
			conn=JdbcUtils.getConnection();
			String sql="insert into users(id,username,password,email,birthday,nickname) values(?,?,?,?,?,?)";
			st=conn.prepareStatement(sql);//对上面sql进行预编译
			//替换预编译之后sql语句中的占位符？
			st.setString(1, user.getId());
			st.setString(2, user.getUsername());
			st.setString(3, user.getPassword());
			st.setString(4, user.getEmail());
			st.setDate(5, new java.sql.Date(user.getBirthday().getTime()));//需要将date转为sql.date
			st.setString(6, user.getNickname());
			int num=st.executeUpdate();
			if(num<1){
				throw new RuntimeException("注册用户失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	//根据用户名和密码查找用户
	/*public User find(String username, String password) {
		// TODO Auto-generated method stub
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			conn=JdbcUtils.getConnection();
			st=conn.createStatement();
			String sql="select * from users where username='"+username+"' and password='"+password+"'";
			rs=st.executeQuery(sql);
			if(rs.next()){
				User user=new User();
				user.setBirthday(rs.getDate("birthday"));
				user.setEmail(rs.getString("email"));
				user.setId(rs.getString("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				return user;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}*/
	
	
	/*
	 * statement和preparedStatement的区别
	 * 1，preparedStatement是statement的孩子
	 * 2，preparedStatement可以防止sql注入的问题
	 * 3，preparedStatement会对sql语句进行预编译，以减轻数据库服务器的压力
	 * */
	
	//防止sql注入使用prepareStatement，一般是在登陆或者url时带过来的数据比如说用户名和密码，会被人采用恶意注入sql命令的方法登陆系统，
	//比如说登陆框中不输入用户名，而是直接输入' or 1=1 or username='，即便没有用户名和密码也可以登入系统，这就是著名的sql注入问题了，要预防它
	//可以采用在web层，校验用户输入参数是不是一条sql命令，如果时，拒绝登陆；或者更专业的就是采用prepareStatement方法对用户输入参数转义来预防sql注入
	public User find(String username, String password) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try {
			conn=JdbcUtils.getConnection();
			String sql="select * from users where username=? and password=?";
			st=conn.prepareStatement(sql);//预编译这条sql,这条语句一执行，st这个对象就代表是上面那条sql语句，此时的sql语句不是完整的，上面使用的是？占位符，代码传入的参数
			//替换问号,可以对参数进行转义防止sql注入
			//st.setString(1, "' or 1=1 or username='");//prepareStatement执行这条语句时会对' or 1=1 or username='这个sql注入命令进行转义，转义之后，再去下面调用查询语句就不会返回任何记录集了
			st.setString(1, username);
			st.setString(2, password);
			//之后st对象内部封装的sql语句就是完整的了，就可以调用查询语句了
			//st.executeQuery(sql);//此时就不要再传递sql参数来调用查询函数了，因为sql语句是不完整的，而st中已经封装了完整的sql语句
			rs=st.executeQuery();
			if(rs.next()){
				User user=new User();
				user.setBirthday(rs.getDate("birthday"));
				user.setEmail(rs.getString("email"));
				user.setId(rs.getString("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				return user;
			}
			
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	//根据用户名查找用户
	/*public boolean find(String username) {
		// TODO Auto-generated method stub
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			conn=JdbcUtils.getConnection();
			st=conn.createStatement();
			String sql="select * from users where username='"+username+"'";
			rs=st.executeQuery(sql);
			if(rs.next()){
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}*/
	
	//根据用户名查找用户,使用PreparedStatement改造
	public boolean find(String username) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		try {
			conn=JdbcUtils.getConnection();
			String sql="select * from users where username=?";
			st=conn.prepareStatement(sql);
			st.setString(1, username);
			rs=st.executeQuery();
			if(rs.next()){
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	
}
