package application.site.test ;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.processing.Generated;

import org.junit.Test;

import application.site.dbsubsystem.mysql.MySQLOrderList;

@Generated(value = "org.junit-tools-1.1.0")
public class MySQLOrderListTest {

	private MySQLOrderList createTestSubject() {
		return new MySQLOrderList();
	}
	private MySQLOrderList mySQLOrderList = new MySQLOrderList();

	
	@Test
	public void testUpdateOrderListStatusSuccess() throws Exception {
		
		int orderId = 123;
		String newOrderListStatus = "Đã xử lí";
		mySQLOrderList = createTestSubject();
		mySQLOrderList.updateOrderListStatus(orderId, newOrderListStatus);
	}
	
	@Test
	public void testUpdateOrderListStatusNonOrderID() throws Exception {
		
		int orderId = -1;
		String newOrderListStatus = "Đã xử lí";
		Exception exception = assertThrows(RuntimeException.class,()->{
			
			mySQLOrderList.updateOrderListStatus(orderId, newOrderListStatus);
		});
		
		assertEquals("Non-existing OrderID", exception.getMessage());
	}
	
	
}

