package cn.lnu.utils;

import java.text.ParseException;
import java.util.Enumeration;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

//把request中的数据封装到bean中
public class WebUtils {
	public static <T> T request2Bean(HttpServletRequest request,Class<T> beanClass){
		try{
		//创建要封装数据的bean
		T bean=beanClass.newInstance();
		
		//把数据整到bean中
		Enumeration e=request.getParameterNames();
		while(e.hasMoreElements()){
			String name=(String)e.nextElement();
			String value=request.getParameter(name);
			//将name对应的值，反射到bean对象的相应属性上
			BeanUtils.setProperty(bean,name,value);
		}
		return bean;
	}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
