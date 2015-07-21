package junit.test;

import java.util.Date;

import org.junit.Test;

import cn.lnu.dao.UserDao;
import cn.lnu.dao.impl.UserDaoXmlImpl;
import cn.lnu.domain.User;

public class UserDaoXmlTest {
	@Test
	public void testAdd(){
		User user=new User();
		
		user.setId("233");
		user.setUsername("Jhon");
		user.setPassword("123456");
		user.setEmail("a123@163.com");
		user.setBirthday(new Date());
		user.setNickname("dss");
		
		UserDao dao=new UserDaoXmlImpl();
		dao.addUser(user);
	}
	@Test
	public void testFind(){
		UserDao dao=new UserDaoXmlImpl();
		dao.find("mushroom","123");
	}
	@Test
	public void testFindByName(){
		UserDao dao=new UserDaoXmlImpl();
		System.out.print(dao.find("mushroom"));
	}
}
