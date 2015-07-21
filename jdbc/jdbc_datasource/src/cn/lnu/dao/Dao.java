package cn.lnu.dao;

import java.sql.Connection;
import java.sql.SQLException;

import cn.lnu.utils.JdbcUtils_JNDI;

public class Dao {
	public void add(){
		Connection conn=null;
		try {
			conn = JdbcUtils_JNDI.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(conn);
	}
}
