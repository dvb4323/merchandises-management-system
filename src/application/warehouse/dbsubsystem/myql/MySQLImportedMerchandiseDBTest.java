package application.warehouse.dbsubsystem.myql;

import javax.annotation.processing.Generated;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Generated(value = "org.junit-tools-1.1.0")
public class MySQLImportedMerchandiseDBTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	private MySQLImportedMerchandiseDB createTestSubject() {
		return new MySQLImportedMerchandiseDB();
	}

	@Test
	public void testUpdateQuantity() throws Exception {
		MySQLImportedMerchandiseDB testSubject;
		String merchandiseCode = "";
		int receivedNumberOfItem = 0;
		boolean result;

		// test 1
		testSubject = createTestSubject();
		merchandiseCode = "MH001";
		receivedNumberOfItem = 0;
		result = testSubject.updateQuantity(merchandiseCode, receivedNumberOfItem);
//		boolean expected = false;
		Assert.assertEquals(false, result);

		// test 2
		testSubject = createTestSubject();
		merchandiseCode = "MH002";
		receivedNumberOfItem = 0;
		result = testSubject.updateQuantity(merchandiseCode, receivedNumberOfItem);
		Assert.assertEquals(false, result);

		// test 3
		testSubject = createTestSubject();
		merchandiseCode = "MH003";
		receivedNumberOfItem = 0;
		result = testSubject.updateQuantity(merchandiseCode, receivedNumberOfItem);
		Assert.assertEquals(false, result);

		// test 4
		testSubject = createTestSubject();
		merchandiseCode = "MH004";
		receivedNumberOfItem = -1;
		result = testSubject.updateQuantity(merchandiseCode, receivedNumberOfItem);
		Assert.assertEquals(false, result);

		// test 5
		testSubject = createTestSubject();
		merchandiseCode = "MH005";
		receivedNumberOfItem = 1;
		result = testSubject.updateQuantity(merchandiseCode, receivedNumberOfItem);
		Assert.assertEquals(false, result);
	}
}