package cn.lnu.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
//将结果集每一条记录处理先处理到一个bean中，然后在将多个bean加到一个List集合中，需实现框架设计者暴露的handler接口处理器
public class BeanListHandler implements ResultSetHandler{
	private Class clazz;
	public BeanListHandler(Class clazz){//传需要将结果集每一行记录封装到哪个类的对象中的类过来，这样用户使用这个处理器的时候，就不必要像以前那样先new出一个对象在传递过来，而是直接传递过来一个类
		this.clazz=clazz;
	}
	public Object handler(ResultSet rs) {
		List list=new ArrayList();
		try {
			while(rs.next()){
				Object bean=clazz.newInstance();//每取出一条记录就new出一条该类的bean对象
				ResultSetMetaData meta=rs.getMetaData();
				int count=meta.getColumnCount();
				for(int i=0;i<count;i++){
					String name=meta.getColumnName(i+1);//每次取出结果集每一列的列名
					Object value=rs.getObject(name);
					
					//反射，通过每次取出的列名反射出bean对象上对应的属性
					Field f=bean.getClass().getDeclaredField(name);
					f.setAccessible(true);//强制设置该属性为公有访问属性，打开该属性的访问权限，让这个属性上可以被赋值
					f.set(bean, value);//将结果集每列的值赋值到bean对象对应属性上	
				}
				list.add(bean);//将每次反射出的bean添加到一个list集合中
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}