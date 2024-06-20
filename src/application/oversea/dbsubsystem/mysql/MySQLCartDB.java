package application.oversea.dbsubsystem.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.MySqlDB;
import application.oversea.dbsubsystem.ICartDB;
import application.oversea.model.ItemOrderToSiteModel;
import application.oversea.model.ListOrderToSiteModel;

public class MySQLCartDB  extends MySqlDB implements ICartDB {
	
	@Override
	public boolean addToCart(ItemOrderToSiteModel item) {
		
		try {
			   String sql= ("INSERT INTO Cart (listToBeOrderedID,siteCode,merchandiseCode,quantity,mean) VALUES"
			   		+ "(?,?,?,?,?)");
			   PreparedStatement st = connection.prepareStatement(sql);
			   st.setString(1,item.getListToBeOrderedID());
			   st.setString(2,item.getSiteCode());
			   st.setString(3,item.getMerchandiseCode());
			   st.setInt(4, item.getQuantity());
			   st.setString(5, item.getMean());
			   st.executeUpdate();	     
		   }
		  catch(SQLException e) {
			   System.out.println(e);
			   return false;
		  } 	
		return true ;
	}
	
	@Override
	public boolean checkInCart(String listToBeOrderedID, String merchandiseCode, String siteCode) {
		try {
			   String sql= ("select * FROM Cart where listToBeOrderedID = ? AND "
			   		+ "siteCode = ? AND merchandiseCode = ? ;");		   
			   PreparedStatement st = connection.prepareStatement(sql);
			   st.setString(1,listToBeOrderedID);
			   st.setString(2,siteCode);
			   st.setString(3,merchandiseCode);
		       ResultSet rs = st.executeQuery();
		       int count = rs.getRow();
		     if(rs.next()) return true ;
		     System.out.println(count);
		     System.out.println(listToBeOrderedID);
		     System.out.println(merchandiseCode);
		     System.out.println(siteCode);
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		  } 
		return false;
	}
	
	@Override
	public boolean updateCart(ItemOrderToSiteModel item) {
		try {
			   String sql= ("UPDATE Cart "
			   		+ " SET quantity = ?, mean = ? "
			   		+ " WHERE listToBeOrderedID = ? AND siteCode = ? AND merchandiseCode = ? ");
			   PreparedStatement st = connection.prepareStatement(sql);
			   st.setInt(1,item.getQuantity());
			   st.setString(2,item.getMean());
			   st.setString(3,item.getListToBeOrderedID());
			   st.setString(4, item.getSiteCode());
			   st.setString(5, item.getMerchandiseCode());
			   System.out.println(sql);
			   st.executeUpdate();	     
		   }
		  catch(SQLException e) {
			   System.out.println(e);
			   return false;
		  } 	
		return true ;
	}
	
	@Override
	public boolean resetCart(String listToBeOrderedID) {
		try {
			   String sql= ("DELETE FROM Cart "
			   		+ "WHERE listToBeOrderedID = ?; ");		   
			   PreparedStatement st = connection.prepareStatement(sql);
			   st.setString(1,listToBeOrderedID);
			   int t = st.executeUpdate();
		       if(t> 0) {
		    	   System.out.println("delete success");
		    	   return true ;
		       } else {
		    	   System.out.println("fail  delete success");
		    	   System.out.println(t);
		    	   return false;
		       }
		       
		   }
			  catch(SQLException e) {
				   System.out.println(e);
		  } 
		return false;
	}
	
	@Override
	public int getQuantity(String listToBeOrderedID, String merchandiseCode, String siteCode) {
		int quantity = 0 ;
		try {
			   String sql= ("select quantity FROM Cart where listToBeOrderedID = ? AND "
			   		+ "siteCode = ? AND merchandiseCode = ? ;");		   
			   PreparedStatement st = connection.prepareStatement(sql);
			   st.setString(1,listToBeOrderedID);
			   st.setString(2,siteCode);
			   st.setString(3,merchandiseCode);
		       ResultSet rs = st.executeQuery();
		      
		       if (rs.next())  quantity = rs.getInt("quantity");
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		  } 
		return quantity;
	}

	@Override
	public String getMean(String listToBeOrderedID, String merchandiseCode, String siteCode) {
		 String s = "" ;
			try {
				   String sql= ("select mean FROM Cart where listToBeOrderedID = ? AND "
				   		+ "siteCode = ? AND merchandiseCode = ? ;");		   
				   PreparedStatement st = connection.prepareStatement(sql);
				   st.setString(1,listToBeOrderedID);
				   st.setString(2,siteCode);
				   st.setString(3,merchandiseCode);
			       ResultSet rs = st.executeQuery();
			      
			       if (rs.next())  s = rs.getString("mean");
			   }
			  catch(SQLException e) {
				   System.out.println(e);
			  } 
			return s;
	}
	
	
	@Override
	public List<ListOrderToSiteModel> getOrderFromCart(String listToBeOrderedID) {
		List<ListOrderToSiteModel> res = new ArrayList<ListOrderToSiteModel>();
		Map<String, List<ItemOrderToSiteModel>> ordersBySite = getAllOrderAndItsItem(listToBeOrderedID);
		
		for (Map.Entry<String, List<ItemOrderToSiteModel>> entry : ordersBySite.entrySet()) {
			String siteCode = entry.getKey();
			List<ItemOrderToSiteModel> orderItems = entry.getValue();
			ListOrderToSiteModel a = new ListOrderToSiteModel(listToBeOrderedID,siteCode,getSiteName(siteCode)
					,orderItems.size());
			res.add(a);
		    }
		    return res ;
	}
	
	
	@Override
	public List<ItemOrderToSiteModel> getListItemInAnOrder(String listToBeOrderedID, String siteCode) {
		Map<String, List<ItemOrderToSiteModel>> ordersBySite = getAllOrderAndItsItem(listToBeOrderedID);
		List<ItemOrderToSiteModel> list = ordersBySite.get(siteCode);
		return list;
	}
	
	private Map<String, List<ItemOrderToSiteModel>> getAllOrderAndItsItem(String listToBeOrderedID){
		Map<String, List<ItemOrderToSiteModel>> ordersBySite = new HashMap<>();
		List<ItemOrderToSiteModel> list = new ArrayList<ItemOrderToSiteModel>();
		try {
			   String sql= ("select c.* , m.merchandiseName, m.unit  FROM Cart c "
			   		+ " JOIN Merchandise m ON c.merchandiseCode = m.merchandiseCode "
			   		+ " where listToBeOrderedID = ? ; ");		   
			   PreparedStatement st = connection.prepareStatement(sql);
			   st.setString(1,listToBeOrderedID);
		       ResultSet rs = st.executeQuery();
		       
		       while (rs.next()) {	
		    	    String orderID             = rs.getString("listToBeOrderedID");	
		    	    String siteCode	           = rs.getString("siteCode") ;
		    	    String merchandiseCode     = rs.getString("merchandiseCode");	
		    	    String merchandiseName     = rs.getString("merchandiseName");
//		            String merchandiseName     = getMerchandiseName(merchandiseCode);								
		            int quantity	           = rs.getInt("quantity");
//		            String unit 			   = getMerchandiseUnit(merchandiseCode);
		            String unit                = rs.getString("unit");
		            String mean                = rs.getString("mean");
		            ItemOrderToSiteModel a = new ItemOrderToSiteModel(
		            		listToBeOrderedID,siteCode,merchandiseCode,merchandiseName,quantity,unit,mean) ;
		            list.add(a);
		        }
		       
				for (ItemOrderToSiteModel order : list) {
				    String siteCode = order.getSiteCode();
				    if (!ordersBySite.containsKey(siteCode)) {
				        ordersBySite.put(siteCode, new ArrayList<>());
				    }
				    ordersBySite.get(siteCode).add(order);
				}
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		 }
		
		return ordersBySite;
	}
	
	private String getSiteName(String siteCode) {
		String name ="" ;
		try {
			 String sql   = ("select * from Site WHERE siteCode = ? ");	
			 PreparedStatement	st = connection.prepareStatement(sql);
			 st.setString(1,siteCode);
			 ResultSet rs = st.executeQuery(); 
			 if(rs.next()) {
				 name = rs.getString("siteName") ;
			 }
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return name;
	}

}
