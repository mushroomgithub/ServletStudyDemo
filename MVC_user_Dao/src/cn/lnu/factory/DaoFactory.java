package cn.lnu.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//负责生产Dao，现在有两个Dao实现类，cn.lnu.dao.impl.UserDaoJdbcImpl.java和cn.lnu.dao.impl.UserDaoXmlImpl.java具体使用哪个通过配置文件
//工厂模式一般要做成单例的，即希望所有dao只有一个工厂生产
public class DaoFactory {
	
	private Properties daoConfig=new Properties();
	private DaoFactory(){
		InputStream in=DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
		//将配置文件的信息load到一个prop对象中去
		try {
			daoConfig.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("读配置文件失败！");
		}
	}
	
	private static DaoFactory instance=new DaoFactory();
	
	public static DaoFactory getInstance(){
		return instance;
	}
	
	//生产dao               //UserDao.class
						    //DepartmentDao.class
	public <T> T createDao(Class<T> clazz) {//参数为接口类型,并且使用泛型，这样你传递进来的是什么类型的T就是代表那种类型的，然后返回的还是你传递进来的类型
		//根据传进来的接口的名称检索dao.properties配置文件找到这个接口的实现类
		//1,得到传递进来的接口的名称
		//clazz.getName();//cn.lnu.dao.UserDao.java
		String name=clazz.getSimpleName();//获得接口的简单名称这里既是UserDao
		//2,读配置文件检索获得要实例化的类的类名
		String className=daoConfig.getProperty(name);
		
		try {
			Class.forName(className);
			T dao=(T) Class.forName(className).newInstance();
			return dao;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}
}
