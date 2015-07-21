package cn.lnu.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.lnu.dao.Blskh044Dao;
import cn.lnu.domain.Blskh043;
import cn.lnu.domain.Blskh044;
import cn.lnu.utils.JdbcUtils;

public class Blskh044DaoImpl implements Blskh044Dao{
	
	public void add(Blskh044 b){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="insert into blskh0424(id,btnname,btnid) values (?,?,?)";
			Object params[]={b.getId(),b.getBtnname(),b.getBtnid()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String btnid){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="delete from blskh044 where btnid=?";
			runner.update(sql, btnid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void update(Blskh044 b){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="update blskh044 set btnid=? where id=?";
			Object params[]={b.getBtnid(),b.getId()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	public Blskh044 find(String id){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from blskh044 where id=?";
			return (Blskh044) runner.query(sql, id, new BeanHandler(Blskh044.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Blskh044> getAll(){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from blskh043";
			return  (List<Blskh044>) runner.query(sql,new BeanListHandler(Blskh044.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
