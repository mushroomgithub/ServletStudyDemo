package cn.lnu.service.impl;

import cn.lnu.dao.UserDao;
import cn.lnu.domain.User;
import cn.lnu.exception.UserExistException;
import cn.lnu.factory.DaoFactory;
import cn.lnu.utils.ServiceUtils;
//service实现层
//对web层提供所有的业务服务（根据业务需求），这里是提供注册和登陆两个服务(都需要查询数据库)
public class BusinessServiceImpl {
	
	//private UserDao dao=new UserDaoJdbcImpl();//要想底层dao层变化了，上层不用修改这里可以使用工厂模式或者spring方法(解耦方法)
	private UserDao dao=DaoFactory.getInstance().createDao(UserDao.class);//这样的话就是使用工厂模式了，纯粹依赖与接口，这样底层变了并不会影响service层，service需要哪个dao只需要读取利用工厂类读取配置文件就行，实现了service与底层的完全解耦
	
	//对web层提供注册业务
	public void register(User user) throws UserExistException{
		//先判断当前要注册的用户是否存在
		boolean b=dao.find(user.getUsername());
		if(b){
			throw new UserExistException();//发现要注册的用户已经存在，则给web层抛一个编译时异常，提醒web层必须处理这个异常，给用户一个友好提示
		}else{
			//将用户的密码经过md5转码之后再保存到数据库
			user.setPassword(ServiceUtils.md5(user.getPassword()));
			//如果用户不存在，就将该新用户添加到数据库中去
			dao.addUser(user);
		}
	}
	//对web层提供登陆功能
	public User login(String username,String password){
		
		//由于保存到数据库中的密码是经过md5编码的，所以查询时，传递进来的密码也需要先经过md5转码
		password=ServiceUtils.md5(password);
		
		return dao.find(username, password);
	}
}
