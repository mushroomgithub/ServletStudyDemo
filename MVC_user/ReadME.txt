1.搭建开发环境
	1.1 导入开发包
			dom4j(操作xml)+xpath（jaxen）开发包
			jstl开发包（JSP el+jstl）
			beanUtils开发包(JavaBean)
			log4j开发包(配合beanUtils开发)
			commons_logging开发包(配合beanUtils开发)
	1.2 创建组织程序的包
		cn.lnu.domain
		cn.lnu.dao
		cn.lnu.dao.impl
		cn.lnu.service
		cn.lnu.service.impl
		cn.lnu.web.controller（处理请求的servlet）
		cn.lnu.web.UI （为用户提供用户界面 的servlet）
		cn.lnu.utils	（工具类包，用于编写需要公共访问的代码，比如说增删改查方法都需要链接数据库的那段代码或者都需要访问xml文档的那段代码）
		junit.test	(测试程序包)
		
		WEB-INF/jsp	(保存网站所有jsp)
	1.3	创建代表数据库的xml文件(实际开发中链接数据库)
		在类目录下创建一个代表数据库的users.xml的文件