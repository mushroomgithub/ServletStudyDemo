package cn.lnu.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.lnu.dao.BlskhDao;
import cn.lnu.domain.Bls;
import cn.lnu.domain.Blskh;
import cn.lnu.utils.JdbcUtils;

public class BlskhDaoImpl implements BlskhDao{
	
	public void add(Blskh b){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="insert into blskh(id,btnname,btnid) values (?,?,?)";
			Object params[]={b.getId(),b.getBtnname(),b.getBtnid()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String btnid){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="delete from blskh where btnid=?";
			runner.update(sql, btnid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void update(Blskh b){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="update blskh set btnid=? where id=?";
			Object params[]={b.getBtnid(),b.getId()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	public Blskh find(String id){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from blskh where id=?";
			return (Blskh) runner.query(sql, id, new BeanHandler(Blskh.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Blskh> getAll(){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from blskh";
			return  (List<Blskh>) runner.query(sql,new BeanListHandler(Blskh.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
