package cn.lnu.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc_demo1 {
	/*
	  create database jdbc_demo;
	  use jdbc_demo;
	  
	  create table users(
			id int primary key,
			name varchar(40),
			password varchar(40),
			email varchar(60),
			birthday date
			);

	  insert into users(id,name,password,email,birthday) values(1,'zs','123456','zs@sina.com','1980-12-04');
	  insert into users(id,name,password,email,birthday) values(2,'lisi','123456','lisi@sina.com','1981-12-04');
	  insert into users(id,name,password,email,birthday) values(3,'wangwu','123456','wangwu@sina.com','1981-12-04');
	  */
	public static void main(String[] args) throws SQLException{
		String url="jdbc:mysql://localhost:3306/jdbc_demo";//固定写法：协议+主机+端口号(指定哪个数据库，这里是3306mysql应用程序)+使用的数据库名
		String username="root";
		String password="root";
		
		//1,复制jar包，加载驱动，首先在项目目录下创建一个lib目录，将mysql的驱动jar包复制到lib目录下，然后将jar包Build Path-->add to build path加到java的classpath构建路径中去，然后就可以加载驱动了
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//向DriverManager注册给定驱动程序
		//2,获得数据库的链接导入import java.sql.Connection;包
			Connection conn=DriverManager.getConnection(url, username, password);//试图建立到给定数据库url的链接
		//3，链接上数据库之后，创建向数据库发送sql语句的statement的对象
			Statement st=conn.createStatement();
		//4，向数据库发送sql语句，获取数据库返回的结果集
			ResultSet rs=st.executeQuery("select * from users");
		//5，从结果集中获得数据
			System.out.println("----------------取的所有行数据---------------------");
			while(rs.next()){//初始的next含义是将游标从表头移动到第一条记录行上，如果该行有记录返回true,否则返回false
				System.out.println("id="+rs.getObject("id"));
				System.out.println("name="+rs.getObject("name"));
				System.out.println("email="+rs.getObject("password"));
				System.out.println("email="+rs.getObject("email"));
				System.out.println("birthday="+rs.getObject("birthday"));
				System.out.println("-------------------------------------");
			}
			
			//取指定行的数据
			System.out.println("----------------取第二行数据---------------------");
			rs.absolute(2);//绝对定位到第二行，取第二行数据
			System.out.println("id="+rs.getObject("id"));
			System.out.println("name="+rs.getObject("name"));
			System.out.println("email="+rs.getObject("password"));
			System.out.println("email="+rs.getObject("email"));
			System.out.println("birthday="+rs.getObject("birthday"));
			System.out.println("-------------------------------------");
			
			//取第一行数据
			System.out.println("----------------取第一行数据---------------------");
			rs.beforeFirst();
			rs.next();
			System.out.println("id="+rs.getObject("id"));
			System.out.println("name="+rs.getObject("name"));
			System.out.println("email="+rs.getObject("password"));
			System.out.println("email="+rs.getObject("email"));
			System.out.println("birthday="+rs.getObject("birthday"));
			System.out.println("-------------------------------------");
			
			//取最后一行数据
			System.out.println("----------------取最后一行数据---------------------");
			rs.afterLast();
			rs.previous();
			System.out.println("id="+rs.getObject("id"));
			System.out.println("name="+rs.getObject("name"));
			System.out.println("email="+rs.getObject("password"));
			System.out.println("email="+rs.getObject("email"));
			System.out.println("birthday="+rs.getObject("birthday"));
			System.out.println("-------------------------------------");
		//6，释放资源，主要是放链接,把链接还给数据库
			rs.close();
			st.close();
			conn.close();
	}
}
