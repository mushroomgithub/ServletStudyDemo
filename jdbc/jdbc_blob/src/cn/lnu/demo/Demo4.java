package cn.lnu.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.lnu.utils.JdbcUtils;

/*获取自动生成的主键
 	create table test
 	(
 		id int primary key auto_increment,
 		name varchar(40)
 	);
 * */
public class Demo4 {
	public static void main(String[] args) throws SQLException{
		
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtils.getConnection();
			String sql="insert into test(name) values('aaa')";
			st=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			st.executeUpdate();
			rs=st.getGeneratedKeys();//获得自动生成主键的记录集
			if(rs.next()){
				System.out.print(rs.getInt(1));
			}
		}finally{
			JdbcUtils.release(conn, st, rs);
			
		}
	}
}
