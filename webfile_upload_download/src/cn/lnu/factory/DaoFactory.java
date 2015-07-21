package cn.lnu.factory;

import java.io.InputStream;
import java.util.Properties;

//使用工厂，完全解耦service层与dao层之间的联系，而且要把工厂做成单例的
public class DaoFactory {
	private static Properties daoconfig=new Properties();//将dao的配置文件信息加载到prop对象
	static{
		try{
			InputStream in=DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
			daoconfig.load(in);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	private DaoFactory(){}
	private static DaoFactory instance=new DaoFactory();
	public static final DaoFactory getInstance(){
		return instance;
	}
	
	//提供一个方法产生dao
	public static <T> T createDao(Class<T> interfaceClass){
		String name=interfaceClass.getSimpleName();//获得这个接口的简单名称，这里既是UpfileDao
		//读配置文件获得这个接口的实际实现类名,即我们要创建dao的实际类名
		String daoClassName=daoconfig.getProperty(name);//cn.lnu.dao.impl.UpfileDaoImpl
		//得到接口实现类的实际类名，通过反射技术加载这个类，创建一个这个实现类的实例
		try {
			return (T) Class.forName(daoClassName).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
}
