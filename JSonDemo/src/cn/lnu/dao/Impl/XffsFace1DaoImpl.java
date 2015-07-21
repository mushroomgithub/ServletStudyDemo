package cn.lnu.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.lnu.dao.XffsFace1Dao;
import cn.lnu.domain.Mainface;
import cn.lnu.domain.Xffsface1;
import cn.lnu.utils.JdbcUtils;

public class XffsFace1DaoImpl implements XffsFace1Dao{
	
	public void add(Xffsface1 x1){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="insert into xffsface1(id,btnname,btnid) values (?,?,?)";
			Object params[]={x1.getId(),x1.getBtnname(),x1.getBtnid()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String btnid){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="delete from xffsface1 where btnid=?";
			runner.update(sql, btnid);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void update(Xffsface1 x1){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="update xffsface1 set btnid=? where id=?";
			Object params[]={x1.getBtnid(),x1.getId()};
			runner.update(sql, params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	public Xffsface1 find(String id){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from xffsface1 where id=?";
			return (Xffsface1) runner.query(sql, id, new BeanHandler(Xffsface1.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Xffsface1> getAll(){
		try{
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from xffsface1";
			return (List<Xffsface1>) runner.query(sql,new BeanListHandler(Xffsface1.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
