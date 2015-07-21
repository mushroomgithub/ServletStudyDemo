package cn.lnu.pool.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.lnu.utils.JdbcUtils_DBCP;

public class Demo1 {

	/**
	 ����ʹ��dbcp����Դ�������ݿ����ӳ�Ϊ�û�ά��һ�����ӹ�������ķ�ʽ
	 ����ʹ�ð�װ��(�ڲ���ǿ��close����)�ķ�ʽ�����ݿ������ڵ���close����ʱ���黹���Ӹ����ݿ����ӳض��������ݿ�
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtils_DBCP.getConnection();//�ײ��Ǵ�dbcp���ݿ����ӳ���Ҫ������
			//System.out.println(conn);//��ӡ��������ݿ����ӣ�˵�������ݿ����ӳ��гɹ����������
			System.out.println(conn.getClass().getName());//�������connection����İ�װ����
		}finally{
			JdbcUtils_DBCP.release(conn, st, rs);
		}
	}

}