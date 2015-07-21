package cn.lnu.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.lnu.dao.MainfaceDao;
import cn.lnu.domain.Mainface;
import cn.lnu.utils.JdbcUtils;

public class MainfaceDaoImpl implements MainfaceDao {
	
	public void add(Mainface m){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="insert into mainface(id,btnname,btnid) values (?,?,?)";
			Object params[]={m.getId(),m.getBtnname(),m.getBtnid()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String btnid){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="delete from mainface where btnid=?";
			runner.update(sql, btnid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void update(Mainface m){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="update mainface set btnid=? where id=?";
			Object params[]={m.getBtnid(),m.getId()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	public Mainface find(String id){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from mainface where id=?";
			return (Mainface) runner.query(sql, id, new BeanHandler(Mainface.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Mainface> getAll(){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from mainface";
			return (List<Mainface>) runner.query(sql,new BeanListHandler(Mainface.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
