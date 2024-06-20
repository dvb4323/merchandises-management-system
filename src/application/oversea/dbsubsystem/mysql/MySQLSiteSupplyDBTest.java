package application.oversea.dbsubsystem.mysql;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Generated;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.oversea.model.SiteSupplyModel;

@Generated(value = "org.junit-tools-1.1.0")
public class MySQLSiteSupplyDBTest {
	
	

	@Before
	public void setUp() throws Exception {
		
	    
	}

	@After
	public void tearDown() throws Exception {

	}

	private MySQLSiteSupplyDB createTestSubject() {
		return new MySQLSiteSupplyDB();
	}
	///Kiểm thử hộp đen : merchandiseCode là null
	@Test
	public void test1() throws Exception {
		MySQLSiteSupplyDB testSubject;
		String merchandiseCode = null;
		List<SiteSupplyModel> result;

		// test 1
		testSubject = createTestSubject();
		result = testSubject.findSite(merchandiseCode);
		assertEquals(null, result);
	}
	
	///Kiểm thử hộp đen : merchandiseCode là ""
	@Test
	public void test2() throws Exception {
		MySQLSiteSupplyDB testSubject;
		String merchandiseCode = "";
		List<SiteSupplyModel> result;

		// test 1
		testSubject = createTestSubject();
		result = testSubject.findSite(merchandiseCode);
		assertEquals(null, result);
	}
	
	///Kiểm thử hộp đen : merchandiseCode là không tồn tại
	@Test
	public void test3() throws Exception {
		MySQLSiteSupplyDB testSubject;
		String merchandiseCode = "123";
		List<SiteSupplyModel> result;

		testSubject = createTestSubject();
		result = testSubject.findSite(merchandiseCode);
		assertEquals(null, result);

	}
	
	///Kiểm thử hộp đen : merchandiseCode tồn tại
		@Test
		public void test4() throws Exception {
			MySQLSiteSupplyDB testSubject;
			String merchandiseCode = "MH001";
			List<SiteSupplyModel> result;
			List<SiteSupplyModel> expectedList ;
			List<SiteSupplyModel>         inputList ;
			expectedList = new ArrayList<SiteSupplyModel>();
			inputList = new ArrayList<SiteSupplyModel>();
			SiteSupplyModel site1 = new SiteSupplyModel("S001", "MH001", 100, 5, 10);
		    SiteSupplyModel site2 = new SiteSupplyModel("S002", "MH001", 50, 7, 12);
		    expectedList.add(site1);
		    expectedList.add(site2);
		    
		    inputList.add(site2);
		    inputList.add(site1);
		    testSubject = new MySQLSiteSupplyDB() {
	            @Override
	            public List<SiteSupplyModel> getSiteSupply(String code) {
	                return inputList;
	            }
	        };
	      assertEquals(expectedList, testSubject.findSite(merchandiseCode) );

        }
	
	
	/// Kiểm thử hộp trắng
	@Test
	public void test5() throws Exception {
		List<SiteSupplyModel> expectedList ;
		List<SiteSupplyModel>         inputList ;
		expectedList = new ArrayList<SiteSupplyModel>();
		inputList = new ArrayList<SiteSupplyModel>();
		
		// sitecode , merchandisecode, instock , ship, air
		SiteSupplyModel site1 = new SiteSupplyModel("S001", "MH001", 100, 7, 10);
	    SiteSupplyModel site2 = new SiteSupplyModel("S002", "MH001", 50,  5, 12);
	    expectedList.add(site2);
	    expectedList.add(site1);
	    
	    inputList.add(site1);
	    inputList.add(site2);
		MySQLSiteSupplyDB testSubject;
		String merchandiseCode = "MH001";
	    testSubject = new MySQLSiteSupplyDB() {
	            @Override
	            public List<SiteSupplyModel> getSiteSupply(String code) {
	                return inputList;
	            }
	        };
	      assertEquals(expectedList, testSubject.findSite(merchandiseCode) );
	    }
	
	/// Kiểm thử hộp trắng
		@Test
		public void test6() throws Exception {
			List<SiteSupplyModel> expectedList ;
			List<SiteSupplyModel>         inputList ;
			expectedList = new ArrayList<SiteSupplyModel>();
			inputList = new ArrayList<SiteSupplyModel>();
			
			// sitecode , merchandisecode, instock , ship, air
			SiteSupplyModel site1 = new SiteSupplyModel("S001", "MH001", 100, 5, 10);
		    SiteSupplyModel site2 = new SiteSupplyModel("S002", "MH001", 50,  7, 12);
		    expectedList.add(site1);
		    expectedList.add(site2);
		    
		    inputList.add(site1);
		    inputList.add(site2);
			MySQLSiteSupplyDB testSubject;
			String merchandiseCode = "MH001";
		    testSubject = new MySQLSiteSupplyDB() {
		            @Override
		            public List<SiteSupplyModel> getSiteSupply(String code) {
		                return inputList;
		            }
		        };
		      assertEquals(expectedList, testSubject.findSite(merchandiseCode) );
		    }
		
		/// Kiểm thử hộp trắng
		@Test
		public void test7() throws Exception {
			List<SiteSupplyModel> expectedList ;
			List<SiteSupplyModel>         inputList ;
			expectedList = new ArrayList<SiteSupplyModel>();
			inputList = new ArrayList<SiteSupplyModel>();
			
			// sitecode , merchandisecode, instock , ship, air
			SiteSupplyModel site1 = new SiteSupplyModel("S001", "MH001", 100, 5, 12);
		    SiteSupplyModel site2 = new SiteSupplyModel("S002", "MH001", 100, 5, 10);
		    expectedList.add(site2);
		    expectedList.add(site1);
		    
		    inputList.add(site1);
		    inputList.add(site2);
			MySQLSiteSupplyDB testSubject;
			String merchandiseCode = "MH001";
		    testSubject = new MySQLSiteSupplyDB() {
		            @Override
		            public List<SiteSupplyModel> getSiteSupply(String code) {
		                return inputList;
		            }
		        };
		      assertEquals(expectedList, testSubject.findSite(merchandiseCode) );
		    }
		
		/// Kiểm thử hộp trắng
		@Test
		public void test8() throws Exception {
			List<SiteSupplyModel> expectedList ;
			List<SiteSupplyModel>         inputList ;
			expectedList = new ArrayList<SiteSupplyModel>();
			inputList = new ArrayList<SiteSupplyModel>();
			
			// sitecode , merchandisecode, instock , ship, air
			SiteSupplyModel site1 = new SiteSupplyModel("S001", "MH001", 100, 5, 10);
		    SiteSupplyModel site2 = new SiteSupplyModel("S002", "MH001", 50,  5, 12);
		    expectedList.add(site1);
		    expectedList.add(site2);
		    
		    inputList.add(site1);
		    inputList.add(site2);
			MySQLSiteSupplyDB testSubject;
			String merchandiseCode = "MH001";
		    testSubject = new MySQLSiteSupplyDB() {
		            @Override
		            public List<SiteSupplyModel> getSiteSupply(String code) {
		                return inputList;
		            }
		        };
		      assertEquals(expectedList, testSubject.findSite(merchandiseCode) );		
		}
		
		@Test
		public void test9() throws Exception {
			List<SiteSupplyModel> expectedList ;
			List<SiteSupplyModel>         inputList ;
			expectedList = new ArrayList<SiteSupplyModel>();
			inputList = new ArrayList<SiteSupplyModel>();
			
			// sitecode , merchandisecode, instock , ship, air
			SiteSupplyModel site1 = new SiteSupplyModel("S001", "MH001", 50, 5, 10);
		    SiteSupplyModel site2 = new SiteSupplyModel("S002", "MH001", 100,  5, 10);
		    expectedList.add(site2);
		    expectedList.add(site1);
		    
		    inputList.add(site1);
		    inputList.add(site2);
			MySQLSiteSupplyDB testSubject;
			String merchandiseCode = "MH001";
		    testSubject = new MySQLSiteSupplyDB() {
		            @Override
		            public List<SiteSupplyModel> getSiteSupply(String code) {
		                return inputList;
		            }
		        };
		      assertEquals(expectedList, testSubject.findSite(merchandiseCode) );		
		}
		
		@Test
		public void test10() throws Exception {
			List<SiteSupplyModel> expectedList ;
			List<SiteSupplyModel>         inputList ;
			expectedList = new ArrayList<SiteSupplyModel>();
			inputList = new ArrayList<SiteSupplyModel>();
			
			// sitecode , merchandisecode, instock , ship, air
			SiteSupplyModel site1 = new SiteSupplyModel("S001", "MH001", 100, 5, 10);
		    SiteSupplyModel site2 = new SiteSupplyModel("S002", "MH001", 50,  5, 10);
		    expectedList.add(site1);
		    expectedList.add(site2);
		    
		    inputList.add(site1);
		    inputList.add(site2);
			MySQLSiteSupplyDB testSubject;
			String merchandiseCode = "MH001";
		    testSubject = new MySQLSiteSupplyDB() {
		            @Override
		            public List<SiteSupplyModel> getSiteSupply(String code) {
		                return inputList;
		            }
		        };
		      assertEquals(expectedList, testSubject.findSite(merchandiseCode) );		
		}
		
		@Test
		public void test11() throws Exception {
			List<SiteSupplyModel> expectedList ;
			List<SiteSupplyModel>         inputList ;
			expectedList = new ArrayList<SiteSupplyModel>();
			inputList = new ArrayList<SiteSupplyModel>();
			
			// sitecode , merchandisecode, instock , ship, air
			SiteSupplyModel site1 = new SiteSupplyModel("S001", "MH001", 100, 5, 10);
		    SiteSupplyModel site2 = new SiteSupplyModel("S002", "MH001", 100, 5, 10);
		    expectedList.add(site1);
		    expectedList.add(site2);
		    
		    inputList.add(site1);
		    inputList.add(site2);
			MySQLSiteSupplyDB testSubject;
			String merchandiseCode = "MH001";
		    testSubject = new MySQLSiteSupplyDB() {
		            @Override
		            public List<SiteSupplyModel> getSiteSupply(String code) {
		                return inputList;
		            }
		        };
		      assertEquals(expectedList, testSubject.findSite(merchandiseCode) );		
		}
	  	
}