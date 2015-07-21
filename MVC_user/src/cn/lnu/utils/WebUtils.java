package cn.lnu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

//把request中的数据封装到bean中
public class WebUtils {                                        //ResgisterForm.class,最后返回RegisterForm
	public static <T> T request2Bean(HttpServletRequest request,Class<T> beanClass){
		try{
		//1，创建要封装数据的bean
		T bean=beanClass.newInstance();
		
		//2,把数据整到bean中
		Enumeration e=request.getParameterNames();
		while(e.hasMoreElements()){
			String name=(String)e.nextElement();//username password email ...
			String value=request.getParameter(name);
			//将name对应的值，反射到bean对象的相应属性上
			BeanUtils.setProperty(bean,name,value);
		}
		return bean;
	}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	//注册用户时，需要将保存表单数据的bean转换为user
	public static void copyBean(Object src,Object dest){
		//为了能将表单中的日期格式保存到user对象中的String 类型的birthday，这里还需要注册一个日期转换器
		ConvertUtils.register(new Converter(){
			public Object convert(Class type,Object value){
				if(value==null){
					return null;
				}
				String str=(String)value;
				if(str.trim().equals("")){
					return null;
				}
				
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				try {
					return df.parse(str);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				}
			}
			}, Date.class);
		
		try {
			BeanUtils.copyProperties(dest, src);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	//产生全球唯一的id,128位
	public static String generateID(){
		return UUID.randomUUID().toString();
	}
}
