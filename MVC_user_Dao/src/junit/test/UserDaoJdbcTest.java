package junit.test;

import java.util.Date;

import org.junit.Test;

import cn.lnu.dao.UserDao;
import cn.lnu.dao.impl.UserDaoJdbcImpl;
import cn.lnu.dao.impl.UserDaoXmlImpl;
import cn.lnu.domain.User;

public class UserDaoJdbcTest {
	@Test
	public void testAdd(){
		User user=new User();
		
		user.setId("233");
		user.setUsername("Jhon");
		user.setPassword("123456");
		user.setEmail("a123@163.com");
		user.setBirthday(new Date());
		user.setNickname("Ä¢¹½");
		
		UserDao dao=new UserDaoJdbcImpl();
		dao.addUser(user);
	}
	@Test
	public void testFind(){
		UserDao dao=new UserDaoJdbcImpl();
		User user=dao.find("Jhon","123456");
		System.out.println(user);
	}
	@Test
	public void testFindByName(){
		UserDao dao=new UserDaoJdbcImpl();
		System.out.print(dao.find("mushroom"));
	}
}
