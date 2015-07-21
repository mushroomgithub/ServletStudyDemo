package cn.lnu.utils;

import java.sql.ResultSet;

public class IntHandler implements ResultSetHandler {

	public Object handler(ResultSet rs) {
		try{
			if(rs.next()){
				return rs.getInt(1);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return 0;
	}

}
