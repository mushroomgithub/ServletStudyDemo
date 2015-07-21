package cn.lnu.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.lnu.domain.User;

public class jdbc_demo2 {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		String url="jdbc:mysql://localhost:3306/jdbc_demo";//固定写法：协议+主机+端口号(指定哪个数据库，这里是3306mysql应用程序)+使用的数据库名
		String username="root";
		String password="root";
		
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		try{
		//1,DriverManager.registerDriver(new com.mysql.jdbc.Driver());方法并不建议使用，这会导致mysql驱动会被加载两次
			Class.forName("com.mysql.jdbc.Driver");//使用这种方法不会造成mysql驱动被加载两次，而且只需要知道一个驱动字符串，就可以保证驱动类只加载一次
		//2,获得数据库的链接导入import java.sql.Connection;包
			conn=DriverManager.getConnection(url, username, password);//试图建立到给定数据库url的链接
		//3，链接上数据库之后，创建向数据库发送sql语句的statement的对象
			st=conn.createStatement();
		//4，向数据库发送sql语句，获取数据库返回的结果集
			rs=st.executeQuery("select * from users");
		//5，从结果集中获得数据
			while(rs.next()){//初始的next含义是将游标从表头移动到第一条记录行上，如果该行有记录返回true,否则返回false
				//将从数据库中获得数据封装到一个bean中
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setBirthday(rs.getDate("birthday"));
				
				System.out.println("id="+rs.getObject("id"));
				System.out.println("name="+rs.getObject("name"));
				System.out.println("email="+rs.getObject("password"));
				System.out.println("email="+rs.getObject("email"));
				System.out.println("birthday="+rs.getObject("birthday"));
				System.out.println("-------------------------------------");
			}	
		}finally{
		//6，释放资源，主要是放链接,把链接还给数据库
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
}
