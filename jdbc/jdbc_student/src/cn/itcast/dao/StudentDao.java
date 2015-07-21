package cn.itcast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.domain.QueryResult;
import cn.itcast.domain.Student;
import cn.itcast.utils.JdbcUtils;

public class StudentDao {
	
	
	public QueryResult pageQuery(int startindex,int pagesize){

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		QueryResult qr = new QueryResult();
		try{
			conn = JdbcUtils.getConnection();
			String sql = "select * from student limit ?,?";
			st = conn.prepareStatement(sql);
			
			st.setInt(1, startindex);
			st.setInt(2, pagesize);
			
			rs = st.executeQuery();
			
			List list = new ArrayList();
			while(rs.next()){
				Student s = new Student();
				s.setId(rs.getString("id"));
				s.setName(rs.getString("name"));
				list.add(s);
			}
			qr.setList(list);
			
			sql = "select count(*) from student";
			rs = conn.prepareStatement(sql).executeQuery();
			if(rs.next()){
				qr.setTotalrecord(rs.getInt(1));
			}
			return qr;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	
}
