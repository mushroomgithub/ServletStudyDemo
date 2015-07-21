package cn.lnu.dbutils.demo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.lnu.utils.JdbcUtils;

//测试dbutils的各个结果集处理器
public class Demo2 {
	@Test
	public void test1() throws SQLException{//测试将结果集每一行记录处理到一个数组中，注意如果结果是多行，只返回第一行记录
		QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());//通过传递一个数据库连接池，构建一个dbutils对象，连接不用我们手动释放，dbutils框架会帮我们释放
		String sql="select * from users where id=?";
		Object res[]=(Object[]) runner.query(sql, 1, new ArrayHandler());
		for(int i=0;i<res.length;i++){
			System.out.println(res[i]);
		}
	}
	
	@Test
	public void test2() throws SQLException{//测试将结果集每一行记录处理到一个数组中，然后将数组添加到一个list集合的方式处理结果集
		QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());//通过传递一个数据库连接池，构建一个dbutils对象，连接不用我们手动释放，dbutils框架会帮我们释放
		String sql="select * from users";
		List list=(List) runner.query(sql,new ArrayListHandler());
		System.out.println(list);
	}
	
	@Test
	public void test3() throws SQLException{//将结果集指定列上的数据处理到一个ArrayList集合中
		QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());//通过传递一个数据库连接池，构建一个dbutils对象，连接不用我们手动释放，dbutils框架会帮我们释放
		String sql="select * from users";
		List list=(List) runner.query(sql,new ColumnListHandler("name"));
		System.out.println(list);
	}
	
	@Test
	public void test4() throws SQLException{//测试将结果集每一行记录都封装到一个Map，关键字为属性名，值为属性值里，再把这些map再存到一个map里，其key为指定的key
		QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());//通过传递一个数据库连接池，构建一个dbutils对象，连接不用我们手动释放，dbutils框架会帮我们释放
		String sql="select * from users";
		Map<Integer,Map<String,Object>> map=(Map<Integer, Map<String, Object>>) runner.query(sql,new KeyedHandler("id"));
		for(Map.Entry<Integer,Map<String,Object>> me:map.entrySet()){
			int id=me.getKey();
			for(Map.Entry<String, Object> entry:me.getValue().entrySet()){
				String name=entry.getKey();
				Object value=entry.getValue();
				System.out.println(name+"="+value);
			}
			System.out.println("----------------------");
		}
	}
	
	@Test
	public void test5() throws SQLException{//使用ArrayHandler()结果集处理器类，可以使用下面方法返回结果集的总数
		QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());//通过传递一个数据库连接池，构建一个dbutils对象，连接不用我们手动释放，dbutils框架会帮我们释放
		String sql="select count(*) from users";
		/*Object res[]=(Object[]) runner.query(sql,new ArrayHandler());
		long totalrecord=(Long) res[0];
		int count=(int)totalrecord;
		System.out.println(count);
		
		int totalrecord=((Long)res[0]).intValue;
		System.out.println(totalrecord);
		*/
		
		int totalrecord=((Long)runner.query(sql, new ScalarHandler(1))).intValue();
		System.out.println(totalrecord);
	}
}
