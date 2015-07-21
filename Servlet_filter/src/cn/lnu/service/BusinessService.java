package cn.lnu.service;

import java.util.ArrayList;
import java.util.List;

import cn.lnu.domain.User;


//service本身应该是去调dao的这里为了测试方便，使用User类模拟数据库
public class BusinessService {
	//service中维护一个集合
	private static List<User> list=new ArrayList();
	//为了模拟数据库，这里先先使用静态代码块向List集合中添加几个用户
	static{
		list.add(new User("mushroom","123"));
		list.add(new User("jhon","123"));
		list.add(new User("tom","123"));
	}
	
	//检查这个list集合中有无指定用户名和密码的用户
	public User login(String username,String password){
		for(User user:list){
			if(user.getUsername().equals(username)&&user.getPassword().equals(password)){
				return user;
			}
		}
		return null;
	}
	
	//根据用户名查找用户
	public User findUser(String username){
		for(User user:list){
			if(user.getUsername().equals(username)){
				return user;
			}
		}
		return null;
	}
}
