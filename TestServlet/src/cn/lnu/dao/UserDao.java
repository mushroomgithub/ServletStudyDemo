package cn.lnu.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//如果读取资源文件的程序不是servlet的话，就只能通过类装载器去读了,这么会降低程序之间的耦合度
public class UserDao {
	private static Properties dbconfig=new Properties();
	//由于用户的update find delete都需要在操作数据库时，都需要先连接上数据库，所以这里采用静态代码块存放这块代码
	static{
		try{
		//首先得到类的装载器
		InputStream in=UserDao.class.getClassLoader().getResourceAsStream("db.properties");
		dbconfig.load(in);
		}catch(Exception e){
			//如果数据库都访问不了，向上一层抛出错误异常，终止程序运行
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public void update()throws IOException{
		//那么在此处为了操作数据库配置文件，该怎么读
		System.out.println(dbconfig.getProperty("url"));
		//获得url连接字符串之后，就可以根据需要连接数据库，操作你的数据库了
		
		}
	
	public void find()throws IOException{
		/*
		//以下代码虽然可以读取资源文件的数据，但是无法获取配置文件在服务器上更新之后的数据
		Properties prop=new Properties();
		InputStream in=UserDao.class.getClassLoader().getResourceAsStream("db.properties");
		prop.load(in);
		System.out.println(prop.getProperty("url"));
		
		*/
		/*上面的这种采用类装载器的方式获得配置文件的方法，即不能读取到服务器上更新之后的配置文件信息，并且，配置文件不能太大，否则有内存溢出危险
		 	没修改服务器中的classes/db.properties配置文件前，输出jdbc:mysql://localhost:3306/test
			修改了服务器中的classes/db.properties配置文件前，输出jdbc:mysql://localhost:3306/test
		 */
		
		//要想获得服务器上更新之后的配置文件信息，要采用下面的方法，不要使用类装载器，而是使用传统流的方式
		//首先获得配置文件的绝对路径
		String path=UserDao.class.getClassLoader().getResource("db.properties").getPath();
		FileInputStream in=new FileInputStream(path);
		Properties prop=new Properties();
		prop.load(in);
		System.out.println(prop.getProperty("url"));
		/*
		 	没修改服务器中的classes/db.properties配置文件前，输出jdbc:mysql://localhost:3306/test
			修改了服务器中的classes/db.properties配置文件前，输出jdbc:mysql://localhost:3306/test1234
		 */
	}
	
	public void delete(){
		
	}
}
