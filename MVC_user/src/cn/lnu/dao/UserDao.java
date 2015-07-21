package cn.lnu.dao;

import cn.lnu.domain.User;

public interface UserDao {

	//增加一个新用户
	void addUser(User user);

	//根据用户名和密码查找用户
	User find(String username, String password);

	//查找注册的用户是否在数据库中已经存在
	boolean find(String username);

}