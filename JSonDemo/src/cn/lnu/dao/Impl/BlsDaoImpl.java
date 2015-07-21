package cn.lnu.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.lnu.dao.BlsDao;
import cn.lnu.domain.Bls;
import cn.lnu.domain.Mainface;
import cn.lnu.utils.JdbcUtils;

public class BlsDaoImpl implements BlsDao{
	
	public void add(Bls b){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="insert into bls(id,btnname,btnid) values (?,?,?)";
			Object params[]={b.getId(),b.getBtnname(),b.getBtnid()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String btnid){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="delete from bls where btnid=?";
			runner.update(sql, btnid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void update(Bls b){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="update bls set btnid=? where id=?";
			Object params[]={b.getBtnid(),b.getId()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	public Bls find(String id){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from bls where id=?";
			return (Bls) runner.query(sql, id, new BeanHandler(Bls.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Bls> getAll(){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from mainface";
			return  (List<Bls>) runner.query(sql,new BeanListHandler(Bls.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
