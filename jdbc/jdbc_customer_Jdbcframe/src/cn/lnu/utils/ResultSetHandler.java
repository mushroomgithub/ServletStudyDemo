package cn.lnu.utils;

import java.sql.ResultSet;
//框架设计者不知道如何处理结果集，而用户知道，所以这里框架设计者对外暴露一个接口，由用户调用实现，返回用户处理之后的结果集
public interface ResultSetHandler{
	public Object handler(ResultSet rs);
}
