package application.oversea.dbsubsystem.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import application.MySqlDB;
import application.oversea.dbsubsystem.IListToBeOrderedDB;
import application.oversea.model.ItemToBeOrderedModel;
import application.oversea.model.ListToBeOrderedModel;

public class MySQLListToBeOrderedDB extends MySqlDB implements IListToBeOrderedDB {
	
	@Override
	public List<ListToBeOrderedModel> getListToBeOrdered(String listToBeOrderedID, String status) {	
		List<ListToBeOrderedModel> list = new ArrayList<ListToBeOrderedModel>();
		try {
			   String sql= ("select * from ListToBeOrdered ");
			   PreparedStatement st;
			   if(listToBeOrderedID != null && status == null) {
				   sql += " where listToBeOrderedID = ? " ;
				   st = connection.prepareStatement(sql); 
				   st.setString(1,listToBeOrderedID);
				     
			   } else if(listToBeOrderedID == null && status != null) {
				   sql += " where status = ? " ;
				   st = connection.prepareStatement(sql);   
				   st.setString(1,status);
				  
			   } else if (listToBeOrderedID != null && status != null) {
				   sql += " where listToBeOrderedID = ?  and status = ? " ;
				   st = connection.prepareStatement(sql); 
				   st.setString(1,listToBeOrderedID);
				   st.setString(2,status);   
			   } else st = connection.prepareStatement(sql);
		       ResultSet rs = st.executeQuery();
		       while (rs.next()) {	
		    	    String orderID             = rs.getString("listToBeOrderedID");	
		    	    String createdDate		   = rs.getString("createdDate") ;
		            String status1              = rs.getString("status");								
		            int numberOfItem	       = rs.getInt("numberOfItem");
		            ListToBeOrderedModel a    = new ListToBeOrderedModel(orderID,createdDate,status1,numberOfItem ) ;
		            list.add(a);
		        }
		     }
		 catch(SQLException e) {
			   System.out.println(e);
		 } 
		return list;
	}

	@Override
	public List<ItemToBeOrderedModel> getListToBeOrderedItem(String id) {
		List<ItemToBeOrderedModel> list = new ArrayList<ItemToBeOrderedModel>();
		try {
			   String sql= ("select * from ListToBeOrderedItem "
			   		+ " where listToBeOrderedID = ? ;");		   
			   PreparedStatement st = connection.prepareStatement(sql);
			   st.setString(1,id);
		       ResultSet rs = st.executeQuery();
		       while (rs.next()) {	
		    	    String orderID             = rs.getString("listToBeOrderedID");	
		    	    String merchandiseCode	   = rs.getString("merchandiseCode") ;
		    	    String merchandiseName     = getMerchandiseName(merchandiseCode);  
		            int quantity	           = rs.getInt("quantity");
		            String unit				   = getMerchandiseUnit(merchandiseCode);
		            String desiredDeliveryDate = rs.getString("desiredDeliveryDate");
		            int notProcessQuantity     = rs.getInt("notProcessQuantity");
		          
		            ItemToBeOrderedModel a = new ItemToBeOrderedModel(orderID,merchandiseCode,merchandiseName,
		            									quantity,unit,desiredDeliveryDate,notProcessQuantity) ;
		            list.add(a);
		        }
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		  } 
		return list;
	}
	
	
	
	private String getMerchandiseName(String id) {
		String name ="" ;
		try {
			 String sql   = ("select * from Merchandise WHERE merchandiseCode = ? ");	
			 PreparedStatement	st = connection.prepareStatement(sql);
			 st.setString(1,id);
			 ResultSet rs = st.executeQuery(); 
			 if(rs.next()) {
				 name = rs.getString("merchandiseName") ;
			 }
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return name;
	}

	private String getMerchandiseUnit(String id) {
		String unit ="" ;
		try {
			 String sql   = ("select * from Merchandise WHERE merchandiseCode = ? ");	
			 PreparedStatement	st = connection.prepareStatement(sql);
			 st.setString(1,id);
			 ResultSet rs = st.executeQuery(); 
			 if(rs.next()) {
				 unit = rs.getString("unit") ;
			 }
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return unit;
	}

	@Override
	public String getListToBeOrderedStatus(String orderId) {
		String status ="" ;
		try {
			 String sql   = ("select * from ListToBeOrdered  WHERE listToBeOrderedID = ? ");	
			 PreparedStatement	st = connection.prepareStatement(sql);
			 st.setString(1,orderId);
			 ResultSet rs = st.executeQuery(); 
			 if(rs.next()) {
				 status = rs.getString("status") ;
			 }
			 System.out.println(status);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return status;
	 }
	
	
	public static void main(String[] args) {
		 MySQLListToBeOrderedDB db = new MySQLListToBeOrderedDB();
		 List<ListToBeOrderedModel> data = db.getListToBeOrdered("TBO001",null);
		 for(ListToBeOrderedModel a : data) {
			 System.out.println(a.getListToBeOrderedID());
			 System.out.println(a.getNumberOfItem());
		 }
	}
   }
