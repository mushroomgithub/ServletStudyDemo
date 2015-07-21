package cn.lnu.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.lnu.dao.Blskh043Dao;
import cn.lnu.domain.Blskh042;
import cn.lnu.domain.Blskh043;
import cn.lnu.utils.JdbcUtils;

public class Blskh043DaoImpl implements Blskh043Dao{
	
	public void add(Blskh043 b){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="insert into blskh0423(id,btnname,btnid) values (?,?,?)";
			Object params[]={b.getId(),b.getBtnname(),b.getBtnid()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String btnid){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="delete from blskh043 where btnid=?";
			runner.update(sql, btnid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void update(Blskh043 b){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="update blskh043 set btnid=? where id=?";
			Object params[]={b.getBtnid(),b.getId()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	public Blskh043 find(String id){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from blskh043 where id=?";
			return (Blskh043) runner.query(sql, id, new BeanHandler(Blskh043.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Blskh043> getAll(){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from blskh043";
			return  (List<Blskh043>) runner.query(sql,new BeanListHandler(Blskh043.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
