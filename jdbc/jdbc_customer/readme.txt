.1.搭建环境
	1.1导开发包
		mysql驱动
		beanUtils
		log4j开发包
		jstl开发包
		
	1.2 创建组织程序的包
		cn.lnu.domain
		cn.lnu.dao
		cn.lnu.dao.impl
		cn.lnu.service
		cn.lnu.service.impl
		cn.lnu.web.controller
		cn.lnu.web.UI
		cn,lnu.utils
		cn.lnu.exception
		junit.test
		
		/WEB-INF/jsp
		
	1.3 为应用创建相应的库和表
		create database jdbc_customer character set utf8 collate utf8_general_ci;
		use jdbc_customer;
		
		create table customer(
			id varchar(40) primary key,
			name varchar(40) not null,
			gender varchar(4) not null,
			birthday date,
			cellphone varchar(20),
			email varchar(40),
			preference varchar(255),
			type varchar(100) not null,
			description varchar(255)
			
		);
		
	2. 建实体，这里指的是在cn.lnu.domain创建Customer类，建立数据库相同的字段
	
	3，写dao层,即在cn.lnu.dao.impl包建立CustomerDaoImpl类，根据需求设计方法，将方法中的公共代码，写到一个工具类中，方法设计好之后，抽象接口到dao的接口包
	
	4.写service层，即在cn.lnu.service.impl包上建立BusinessServiceImpl类，根据service对web层提供什么服务，来设计方法,将方法中的公共代码，写到一个工具类中，方法设计好之后，抽象接口到service的接口包
	
	5,写web层
			通常在网站首页对用户提供功能，如增删改查用户