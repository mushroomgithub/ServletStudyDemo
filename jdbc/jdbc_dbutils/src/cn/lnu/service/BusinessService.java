package cn.lnu.service;

import java.sql.Connection;
import java.sql.SQLException;

import cn.lnu.dbutils.demo.AccountDao;
import cn.lnu.domain.Account;
import cn.lnu.utils.JdbcUtils;

//service层，对web层提供所有转账服务，需要调用dao层
public class BusinessService {
	//模拟从a-->b账户转账100元
	public void transfer1(int sourceid,int targetid,float money) throws SQLException{
		Connection conn=null;
		try{
			conn=JdbcUtils.getConnection();
			conn.setAutoCommit(false);//为了将所有sql语句作为一个整体，需要开启事务
			
			AccountDao dao=new AccountDao(conn);
			
			Account a=dao.find(sourceid);//底层select
			Account b=dao.find(targetid);//底层select
			
			a.setMoney(a.getMoney()-money);
			b.setMoney(b.getMoney()+money);
			//int x=1/0;//抛异常，数据库自动回滚操作
			dao.update(a);//底层update
			dao.update(b);//底层update
		
		conn.commit();//在连接上提交事务，这就把所有sql化作一个整体执行了
		}finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	//用上TreadLocal的进行用户转账的事务管理策略，从JdbcUtils工具类中获取使用ThreadLocal开始事务，提交事务，关闭连接，获得连接方法
	public void transfer2(int sourceid,int targetid,float money) throws SQLException{
		
		try{//这段代码会被多线程访问，但是没关系，多个线程一起来，就分别以当前线程为key，绑定一个连接，由于多线程之间key不同，线程之间也不会相互干扰，各自绑定的数据库连接，也必定不会相互干扰
			JdbcUtils.startTransaction();//这句代码执行后，当前线程上就绑定了一个开启事务的数据库连接
			//然后绑定开启事务连接的当前线程执行下面操作,即只要下面代码在一个线程下跑，下面这些数据库操作都是在一个绑定到当前线程下的开启事务的连接下做
			AccountDao dao=new AccountDao();
			Account a=dao.find(sourceid);//底层select，内部获得连接都是绑定到当前线程下开启事务的连接
			Account b=dao.find(targetid);//底层select
			
			a.setMoney(a.getMoney()-money);
			b.setMoney(b.getMoney()+money);
			//int x=1/0;//抛异常，数据库自动回滚操作
			dao.update(a);//底层update
			dao.update(b);//底层update
			JdbcUtils.commitTransaction();
		}finally{
			JdbcUtils.closeConnection();
		}
		
	}
}
