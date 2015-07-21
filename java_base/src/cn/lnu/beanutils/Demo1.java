package cn.lnu.beanutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

//使用beanUtils框架操作bean对象的属性,首先将beanUtils框架依赖的开发包集成到程序中
//主要依赖开发包有：commons-beanutils-1.8.0.jar commons_logging.jar（日志记录器jar包）是commons-beanutils-1.8.0.jar框架过程中依赖的jar包实现beanUtils框架过程中的日志记录信息
public class Demo1 {
	
	@Test
	public void test1() throws Exception{
		Person bean=new Person();
		BeanUtils.setProperty(bean, "name", "zhangsan");//表示设置哪个bean的哪个属性，就为这个属性赋值为value
		
		System.out.println(bean.getName());//zhangsan

	}
	
	//下面的这段代码有问题，他只能将字符串转化为八种基本数据类型，不能转化字符串到非八种基本类型的复杂类型，如生日日期，需要为其转化复杂类型注册转换器，
	//没有为复杂类型注册转换器时，会给出警告:     DateConverter does not support default String to 'Date' conversion.
	@Test
	public void test2() throws Exception{
		String name="aaa";
		String password="123";
		String age="24";
		String birthday="1980-09-09";
		
		Person bean=new Person();
		BeanUtils.setProperty(bean, "name", name);//表示设置哪个bean的哪个属性，就为这个属性赋值为value
		BeanUtils.setProperty(bean, "password", password);
		BeanUtils.setProperty(bean, "age", age);//只支持字符串到8种基本数据类型自动转换
		BeanUtils.setProperty(bean, "birthday", birthday);
		
		System.out.println(bean.getName());//aaa
		System.out.println(bean.getPassword());//123
		System.out.println(bean.getAge());//24，可以看到前台传递过的数据都都是字符串类型的，但是bean对象age属性是int型，也就是beanUtils框架支持将前台的字符串数据自动转型为八种基本类型数据，但是复杂类型，就不可以了，需要我们为复杂类型注册一个转化器告诉beanUtils用这个转换器去将前台string类型转化为bean对象的复杂类型属性，如生日日期
		System.out.println(bean.getBirthday());//1980-09-09
	}

	//为beanUtils框架注册实现将字符串转换为复杂数据类型的转换器，这里是将将字符串转化为生日日期的转换器
	@Test
	public void test3() throws Exception{
		String name="aaa";
		String password="123";
		String age="24";
		String birthday="1980-09-09";
		
		//为了让日期赋到bean的birthday属性上，我们给beanUtils注册一个日期转换器，beanUtils为了指定了为复杂类型注册转换器的方法ConvertUtils工具类
		ConvertUtils.register(new Converter(){//在beanUtils转换之前注册的这个转化复杂类型的转化器

			public Object convert(Class type, Object value) {//这个函数有beanUtils调用，将value字符串类型数值转化为复杂的type类型(这里是Date类型)
				//首先检查前台带过来的字符串数据value是否为空，为空不给于转化
				if(value==null){
					return null;
				}
				
				if(!(value instanceof String)){//判断beanUtils传递进来的前台要转化为复杂类型的数据是不是String类型
					throw new RuntimeException("支持String类型的转换");
				}
				
				String str=(String)value;//将value转化为String类型
				if(str.trim().equals("")){//防止前台传递过来的字符串是一系列的空格如  “     ”
					return null;
				}
				//说明是字符串且有值
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				try {
					return df.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e);//不能不写这个e，异常链不能断
				}
			}
		}, Date.class);//为beanUtils注册一个实现将字符串转化为clazz标示的复杂类型的转换器
		
		Person bean=new Person();
		BeanUtils.setProperty(bean, "name", name);//表示设置哪个bean的哪个属性，就为这个属性赋值为value
		BeanUtils.setProperty(bean, "password", password);//只支持字符串到8种基本数据类型自动转换
		BeanUtils.setProperty(bean, "age", age);
		BeanUtils.setProperty(bean, "birthday", birthday);
		
		System.out.println(bean.getName());//aaa
		System.out.println(bean.getPassword());//123
		System.out.println(bean.getAge());//24，可以看到前台传递过的数据都都是字符串类型的，但是bean对象age属性是int型，也就是beanUtils框架支持将前台的字符串数据自动转型为八种基本类型数据，但是复杂类型，就不可以了，需要我们为复杂类型注册一个转化器告诉beanUtils用这个转换器去将前台string类型转化为bean对象的复杂类型属性，如生日日期
		System.out.println(bean.getBirthday().toLocaleString());//1980-9-9 0:00:00
	}
	
	//使用Apache帮我们实现的转化日期的转换器，实现将字符串转换为复杂类型的日期类型,但是这个转换器有问题如果String birthday="";,本来给bean的属性值赋值为null.但是程序会抛出异常
	@Test
	public void test4() throws Exception{
		String name="aaa";
		String password="123";
		String age="24";
		String birthday="1980-09-09";
		
		//使用apache的已经实现Convert接口的转换器类DateLocaleConverter实现将前端发送的日期类型的字符串数据转换为bean对象属性的日期类型数据
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		
		Person bean=new Person();
		BeanUtils.setProperty(bean, "name", name);//表示设置哪个bean的哪个属性，就为这个属性赋值为value
		BeanUtils.setProperty(bean, "password", password);
		BeanUtils.setProperty(bean, "age", age);//只支持字符串到8种基本数据类型自动转换
		BeanUtils.setProperty(bean, "birthday", birthday);
		
		System.out.println(bean.getName());//aaa
		System.out.println(bean.getPassword());//123
		System.out.println(bean.getAge());//24，可以看到前台传递过的数据都都是字符串类型的，但是bean对象age属性是int型，也就是beanUtils框架支持将前台的字符串数据自动转型为八种基本类型数据，但是复杂类型，就不可以了，需要我们为复杂类型注册一个转化器告诉beanUtils用这个转换器去将前台string类型转化为bean对象的复杂类型属性，如生日日期
		System.out.println(bean.getBirthday().toLocaleString());//1980-9-9 0:00:00
	}
	
	@Test
	public void test5() throws Exception{
		Map map=new HashMap();
		map.put("name", "aaa");
		map.put("password", "123");
		map.put("age", "23");
		map.put("birthday", "1980-09-09");
		
		//使用apache的已经实现Convert接口的转换器类DateLocaleConverter实现将前端发送的日期类型的字符串数据转换为bean对象属性的日期类型数据
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		Person bean =new Person();
		BeanUtils.populate(bean, map);//使用map集合的key-value值填充bean对象的属性，内部是将map关键字key为name的属性value值填充到bean对象对应的name属性值上，即map的关键字名称必须与bean属性的属性名保持一致
		
		System.out.println(bean.getName());//aaa
		System.out.println(bean.getPassword());//123
		System.out.println(bean.getAge());//24，可以看到前台传递过的数据都都是字符串类型的，但是bean对象age属性是int型，也就是beanUtils框架支持将前台的字符串数据自动转型为八种基本类型数据，但是复杂类型，就不可以了，需要我们为复杂类型注册一个转化器告诉beanUtils用这个转换器去将前台string类型转化为bean对象的复杂类型属性，如生日日期
		System.out.println(bean.getBirthday().toLocaleString());//1980-9-9 0:00:00
	}
}
