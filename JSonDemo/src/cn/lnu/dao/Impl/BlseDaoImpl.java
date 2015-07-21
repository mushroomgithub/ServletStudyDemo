package cn.lnu.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.lnu.dao.BlseDao;
import cn.lnu.domain.Bls;
import cn.lnu.domain.Blse;
import cn.lnu.utils.JdbcUtils;

public class BlseDaoImpl implements BlseDao{
	
	public void add(Blse b){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="insert into blse(id,btnname,btnid) values (?,?,?)";
			Object params[]={b.getId(),b.getBtnname(),b.getBtnid()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String btnid){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="delete from blse where btnid=?";
			runner.update(sql, btnid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void update(Blse b){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="update blse set btnid=? where id=?";
			Object params[]={b.getBtnid(),b.getId()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	public Blse find(String id){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from blse where id=?";
			return (Blse) runner.query(sql, id, new BeanHandler(Blse.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Blse> getAll(){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from blse";
			return  (List<Blse>) runner.query(sql,new BeanListHandler(Blse.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
