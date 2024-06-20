package application.warehouse.dbsubsystem.myql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.MySqlDB;
import application.warehouse.dbsubsystem.IOrderDB;
import application.warehouse.model.MerchandiseOrderModel;
import application.warehouse.model.WarehouseOrderModel;

public class MySQLOrderDB extends MySqlDB implements IOrderDB{

	@Override
	public List<WarehouseOrderModel> getAllOrder(String orderID, String orderStatus) {
		List<WarehouseOrderModel> list = new ArrayList<WarehouseOrderModel>();
		
//		EntityWarehouseOrder a = new EntityWarehouseOrder("order001","abc",12,"anvav","avvv","svsvsv");
//		EntityWarehouseOrder b = new EntityWarehouseOrder("order001","abc",12,"anvav","avvv","svsvsv");
//		EntityWarehouseOrder c = new EntityWarehouseOrder("order001","abc",12,"anvav","avvv","svsvsv");
//		list.add(a);
//		list.add(b);
//		list.add(c);
		try {
			   String sql= ("select * from warehouseorders ");
			   PreparedStatement st;
			   if(orderID != null && orderStatus == null) {
				   sql += " where OrderID = ? " ;
				   st = connection.prepareStatement(sql);
				   st.setString(1,orderID);
				     
			   } else if(orderID == null && orderStatus != null) {
				   sql += " where OrderStatus = ? " ;
				   st = connection.prepareStatement(sql);   
				   st.setString(1,orderStatus);
				  
			   } else if (orderID != null && orderStatus != null) {
				   sql += " where OrderID = ?  and OrderStatus = ? " ;
				   st = connection.prepareStatement(sql);
				   st.setString(1,orderID);
				   st.setString(2,orderStatus);   
			   } else st = connection.prepareStatement(sql);
		       ResultSet rs = st.executeQuery();
		       while (rs.next()) {	
		    	    String orderedID             = rs.getString("OrderID");	
		    	    String siteCode 			= rs.getString("SiteCode");
		    	    int numberOfItem	       = rs.getInt("NumberOfItems");
		    	    String listToBeOrderedID   = rs.getString("ListToBeOrderedID");
		    	    String status1              = rs.getString("OrderStatus");								
		            String createdDate		   = rs.getString("CreatedDate") ;
		            WarehouseOrderModel a    = new WarehouseOrderModel(orderedID, siteCode, numberOfItem, listToBeOrderedID, status1, createdDate) ;
		            list.add(a);
		        }
		     }
		 catch(SQLException e) {
			   System.out.println(e);
		 }
		return list;
	}

	@Override
	public List<MerchandiseOrderModel> getAllItemInOrder(String orderID) {
		List<MerchandiseOrderModel> list = new ArrayList<MerchandiseOrderModel>();
//		EntityItemWarehouseOrder a = new EntityItemWarehouseOrder("order001","abc","a",12,"anvav","avvv","svsvsv");
//		EntityItemWarehouseOrder b = new EntityItemWarehouseOrder("order001","abc","a",12,"anvav","avvv","svsvsv");
//		EntityItemWarehouseOrder c = new EntityItemWarehouseOrder("order001","abc","a",12,"anvav","avvv","svsvsv");
//		list.add(a);
//		list.add(b);
//		list.add(c);
		String sql= ("select * from itemwarehouseorders WHERE OrderID = ?");
		
		try {
			PreparedStatement	st = connection.prepareStatement(sql);
			st.setString(1, orderID);
			ResultSet rs = st.executeQuery(); 
			
			while (rs.next()) {	
	    	    String orderedID             = rs.getString("OrderID");	
	    	    String siteCode 			= rs.getString("SiteCode");
	    	    String merchandiseCode 		= rs.getString("MerchandiseCode");
	    	    int quantity	       		= rs.getInt("Quantity");
	    	    String unit   				= rs.getString("Unit");
	    	    String expectedDate           = rs.getString("ExpectedDate");								
	            String status = rs.getString("OrderStatus");
	            MerchandiseOrderModel a    = new MerchandiseOrderModel(orderedID, siteCode, merchandiseCode, quantity, unit, expectedDate, status) ;
	            list.add(a);
	        }
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	      
		return list;
	}

}
