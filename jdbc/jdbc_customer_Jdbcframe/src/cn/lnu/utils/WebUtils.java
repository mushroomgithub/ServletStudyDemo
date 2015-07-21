package cn.lnu.utils;

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
	//将request中数据封装到一个bean中
	public static <T> T requestToBean(HttpServletRequest request,Class<T> beanClass){
		try {
			T bean = beanClass.newInstance();
			//得到request里面所有数据，保存到一个map集合中
			Map map=request.getParameterMap();
			//由于map{name=aa,password=123,birthday=1990-09-09}-->bean{name=aa,password=123,birthday=Date}生日不是8种基本类型，这里需要注册一个日期转换器
			ConvertUtils.register(new Converter(){
				public Object convert(Class type, Object value) {//type表示转的什么类型，value表示将哪个值转成Date
					if(value==null){
						return null;
					}
					//不为空，强转为一个String
					String str=(String) value;
					//转为字符串之后再判断一下字符串是否为空
					if(str.trim().equals("")){
						return null;
					}
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					try {
						return df.parse(str);
					} catch (ParseException e) {
						throw new RuntimeException(e);
					}	
				}
				
			}, Date.class);
			//使用BeanUtils的填充方法将map属性集合中的数据，填充到bean对象中
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String generateID(){
		return UUID.randomUUID().toString();
	}
}
