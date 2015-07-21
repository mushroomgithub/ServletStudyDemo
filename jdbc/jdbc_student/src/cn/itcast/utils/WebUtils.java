package cn.itcast.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class WebUtils {
	
	public static <T> T request2Bean(HttpServletRequest request,Class<T>  beanClass){
		try{
			T bean = beanClass.newInstance();
			//得到request里面所有数据
			Map map = request.getParameterMap();
			//map{name=aa,password=bb,birthday=1990-09-09}  bean(name=aa,password=dd,birthday=Date)
			
			ConvertUtils.register(new Converter(){

				public Object convert(Class type, Object value) {
					if(value==null){
						return null;
					}
					String str = (String) value;
					if(str.trim().equals("")){
						return null;
					}
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					try {
						return df.parse(str);
					} catch (ParseException e) {
						throw new RuntimeException(e);
					}
				}
			}, Date.class);
			BeanUtils.populate(bean, map);   
			return bean;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String generateID(){
		return UUID.randomUUID().toString();
	}
	
	
}
