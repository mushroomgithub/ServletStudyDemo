package cn.lnu.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.lnu.domain.Category;
import cn.lnu.utils.JdbcUtils;

//操作数据库分类信息的dao
public class CategoryDao {
	
	public List<Category> getAll(){//把所有节点封装到一个list集合中
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());//使用dbutils框架，传递一个数据库连接池(数据源)
			String sql="select child.id,child.name,child.lft,child.rgt,count(child.name) depth from category parent,category child where child.lft>=parent.lft and child.rgt<=parent.rgt group by(child.name) order by child.lft;";
			List list=(List) runner.query(sql, new BeanListHandler(Category.class));
			return list;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
