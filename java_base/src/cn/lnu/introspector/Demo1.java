package cn.lnu.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

//使用内省api操作bean的属性
public class Demo1 {
	
	//得到bean的所有属性  之后会学习beanutils框架操作bean对象的属性，这套框架可以使得我们方便的操作javabean的属性，实际开发中以使用beanutils最多
	@Test
	public void test1() throws Exception{
		BeanInfo beanInfo=Introspector.getBeanInfo(Person.class,Object.class);//获得指定javabean信息的BeanInfo对象,并且去掉从Object继承来的class属性，得到bean自己的属性
		PropertyDescriptor[] pds=beanInfo.getPropertyDescriptors();//根据beanInfo获得这个bean所有属性的属性描述器
		for(PropertyDescriptor pd:pds){//迭代每个属性描述器
			System.out.println(pd.getName());//age name  password//可以看到每次属性描述器返回的属性名
		}
	}
	
	//操作bean的指定属性：age
	@Test
	public void test2() throws Exception{
		Person p=new Person();
		PropertyDescriptor pd=new PropertyDescriptor("age",Person.class);//可以通过new的方法指定获得指定bean的指定属性的属性描述器
		//System.out.println(pd.getName());  //age
		//得到属性的写方法，为属性赋值
		Method method=pd.getWriteMethod();
		method.invoke(p, 28);
		
		//获得属性的值
		method=pd.getReadMethod();
		System.out.println(method.invoke(p, null));//28
	}
	
	//高级点的内容：获取当前操作的属性的类型
	@Test
	public void test3() throws Exception{
		Person p=new Person();
		PropertyDescriptor pd=new PropertyDescriptor("age",Person.class);//可以通过new的方法指定获得指定bean的指定属性的属性描述器
		Class type=pd.getPropertyType();//获得指定属性类型
		System.out.println(type);//int
	}
	
	
}
