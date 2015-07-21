使用jdbc的dbutils开源框架操作数据库
1，这里建立一个java Project工程，名为jdbc_dbutils
2,搭建开发环境
	2.1，首先鼠标右键项目名jdbc_dbutils，New-->Folder,创建一个lib文件夹
	2.2 ,将以下jar包添加到lib目录下
		mysql驱动
		commons-dbcp.jar  为了提高数据库访问性能这里使用dbcp数据库连接池
		commons-pool.jar		为了提高数据库访问性能这里使用dbcp数据库连接池
		（dbcp在工作过程中需要读配置文件dbcpconfig.properties，所以还要将这个配置文件拷贝到src目录下）
		commons-dbutils.jar  为了简化jdbc在crud四个操作上的jdbc编码，还需要导入dbutils开源框架的jar包
	2.3 将lib下添加到jar包build到工程项目path中，方法是，选择四个jar包，鼠标右键，选择Build Path-->Add to Build Path.
			至此使用jdbc操作开源jdbc dbutils框架的开发环境就算搭建完毕了
			
	3.要使用开源框架操作数据库，需要将数据库的表创建出来
	create database jdbc_dbutils;
	 use jdbc_dbutils;
	 create table users(
			id int primary key,
			name varchar(40),
			password varchar(40),
			email varchar(100),
			birthday date
			);
insert into users(id,name,password,email,birthday) values(1,'zs','123456','zx@sina.com','1980-10-15');
insert into users(id,name,password,email,birthday) values(2,'lisi','123456','lisi@sina.com','1980-10-15');
insert into users(id,name,password,email,birthday) values(3,'wangwu','123456','wangwu@sina.com','1980-10-15');
4,在操作dbutils时，需要给其传递数据库连接池，所以还需要写工具类，创建数据库连接池
5,使用dbutils完成数据库的crud操作