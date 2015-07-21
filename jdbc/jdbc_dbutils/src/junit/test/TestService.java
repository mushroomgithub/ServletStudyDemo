package junit.test;

import java.sql.SQLException;

import org.junit.Test;

import cn.lnu.service.BusinessService;

public class TestService {
	@Test
	public void testTransfer() throws SQLException{
		BusinessService service=new BusinessService();
		//service.transfer1(1, 2, 100);
		service.transfer2(1, 2, 100);
	}
	
	
}
