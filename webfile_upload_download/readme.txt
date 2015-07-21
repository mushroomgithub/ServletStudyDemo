1，准备环境
	1.1导入开发包
		(dao层包)
		mysql驱动
		c3p0连接池 (优化jdbc性能)，c3p0在工作的时候，需要一个配置文件c3p0-config.xml支持，将其导入到src类目录下
		dbutils框架包 (简化jdbc编码)
		
		（web层开发包）
		fileupload组件(需要io包的支持)
		io包
		
		beanUtils开发包，便于请求数据封装到bean中
		log4j支持beanUtils包
		
		jstl开发包
	1.2 创建组织程序的包
	
	1.3 组织库和表	
		create database webfile_upload_download character set utf8 collate utf8_general_ci;
		use webfile_upload_download;
		create table upfile
		(
			id varchar(40) primary key,
			uuidname varchar(100) not null unique,
			realname varchar(100) not null,
			savepath varchar(255) not null,
			uptime datetime not null,
			description varchar(255),
			username varchar(40) not null
		);
		
2.做实体

3.做dao

4.做service

5.做web层（从首页开始写）
