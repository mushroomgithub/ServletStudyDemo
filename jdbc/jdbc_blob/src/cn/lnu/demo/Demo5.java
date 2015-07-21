package cn.lnu.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import cn.lnu.utils.JdbcUtils;

public class Demo5 {

	/**调用存储过程
	 *功能：将参数一的值写到参数二中，在金融证券行业使用存储过程很多
	 * 将下面这个存储过程先在mysql客户端执行一下：
		 delimiter $$
		 CREATE PROCEDURE demoSp(IN inputParam VARCHAR(255),INOUT intOutParam varchar(255))
		 BEGIN
		 		SELECT CONCAT('zyxw---',inputParam) into intOutParam;
		 END $$
		 
		 delimiter;
	 */
	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		CallableStatement  st = null;
		ResultSet rs = null;
		
		try{
			conn = JdbcUtils.getConnection();
			st = conn.prepareCall("{call demoSp(?,?)}");
			st.setString(1, "aaaaa");
			st.registerOutParameter(2, Types.VARCHAR);
			st.execute();
			
			System.out.println(st.getString(2));
			
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
}
