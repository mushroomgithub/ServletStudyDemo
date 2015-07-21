package cn.lnu.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
//框架设计者帮我们事先事先几个实际开发中经常使用的处理器，由于框架设计者不知道将结果集处理到什么对象或者集合中所以在实现接口的时候创建一个构造函数接收用户想要将结果集处理到什么对象或者集合中
public class BeanHandler implements ResultSetHandler{//预先实现一个将结果集处理到一个bean对象中的类
	private Class clazz;
	public BeanHandler(Class clazz){
		this.clazz=clazz;//接收用户传递过来的类，这里记住这个参数，框架设计者根据这个类，将结果集封装处理到这个类的对象中返回
	}
	public Object handler(ResultSet rs) {
		try {
				if(!rs.next()){//如果结果集中没有数据，无需处理直接返回null
					return null;
				}
				//有数据，创建封装结果集的bean
				Object bean=clazz.newInstance();
				//由于框架设计者不知道结果集中都有什么数据，这就需要用到结果集元数据了,
				ResultSetMetaData meta=rs.getMetaData();//得到结果集的元数据，以获得结果集的信息
				int count=meta.getColumnCount();//获得结果集上总的列数
				for(int i=0;i<count;i++){
					String name=meta.getColumnName(i+1);//获得结果集每列的列名（结果集列下表从1开始）
					Object value=rs.getObject(name);//获得每列列名对应的值
					
					//通过每次取得的结果集列名反射出bean上与该列名对应的属性
					Field f=bean.getClass().getDeclaredField(name);
					f.setAccessible(true);//由于bean对象类在设计时，属性一般为私有，这里强制设为公有使得外界可以操作对象属性名
					f.set(bean, value);//将反射出的属性名为f的值保存到bean对象对应属性上
				}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}