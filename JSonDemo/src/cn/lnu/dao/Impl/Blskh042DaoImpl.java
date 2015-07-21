package cn.lnu.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.lnu.dao.Blskh042Dao;
import cn.lnu.domain.Blskh;
import cn.lnu.domain.Blskh042;
import cn.lnu.utils.JdbcUtils;

public class Blskh042DaoImpl implements Blskh042Dao{
	
	public void add(Blskh042 b){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="insert into blskh042(id,btnname,btnid) values (?,?,?)";
			Object params[]={b.getId(),b.getBtnname(),b.getBtnid()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String btnid){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="delete from blskh042 where btnid=?";
			runner.update(sql, btnid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void update(Blskh042 b){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="update blskh042 set btnid=? where id=?";
			Object params[]={b.getBtnid(),b.getId()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	public Blskh042 find(String id){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from blskh042 where id=?";
			return (Blskh042) runner.query(sql, id, new BeanHandler(Blskh042.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Blskh042> getAll(){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from blskh042";
			return  (List<Blskh042>) runner.query(sql,new BeanListHandler(Blskh042.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
