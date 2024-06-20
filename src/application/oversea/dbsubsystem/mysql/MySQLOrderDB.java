package application.oversea.dbsubsystem.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import application.MySqlDB;
import application.oversea.dbsubsystem.IOrderDB;
import application.oversea.model.ItemOrderToSiteModel;
import application.oversea.model.ItemToBeOrderedModel;
import application.oversea.model.ListOrderToSiteModel;
import application.oversea.model.OrderModel;

public class MySQLOrderDB extends MySqlDB implements IOrderDB {	
	
	@Override
	public boolean createOrder( List<ListOrderToSiteModel> list) {
		try {
			for(ListOrderToSiteModel order : list ) {
				String sql1 = "INSERT INTO ProcessOrder (listToBeOrderedID,siteCode,numberOfItem,createDate,statusOverSea,statusSite) VALUES"
						+ " (?,?,?,CURDATE(),?,?) ;" ;
				PreparedStatement st = connection.prepareStatement(sql1);
				st.setString(1,order.getListToBeOrderedID());
				st.setString(2,order.getSiteCode());
				st.setInt(3, order.getNumberOfItem());
				st.setString(4,"Chờ xác nhận" ); 
				st.setString(5,"Chưa xử lý" ); 
				st.executeUpdate();	
				

			  }
		  }
		  catch(SQLException e) {
			   System.out.println(e);
			   return false;
		  }
		  return true ;
	}
	

	@Override
	public boolean createOrderItem( List<ItemOrderToSiteModel> itemList ) {

		for(ItemOrderToSiteModel item : itemList) {
			
			try {
				String sql2 = "Select processOrderID from ProcessOrder where listToBeOrderedID = ? AND siteCode = ? ";
				PreparedStatement st1  = connection.prepareStatement(sql2);
				st1.setString(1,item.getListToBeOrderedID());
				st1.setString(2,item.getSiteCode());
				ResultSet rs = st1.executeQuery();
				int processID = 0;
				if(rs.next()) processID = rs.getInt("processOrderID");
				String sql = "INSERT INTO ProcessOrderItem (processOrderID,merchandiseCode,quantity,mean,status) VALUES"
						+ " (?,?,?,?,?) ;" ;
				PreparedStatement st = connection.prepareStatement(sql);
				st.setInt(1,processID);
				st.setString(2,item.getMerchandiseCode());
				st.setInt(3, item.getQuantity());
				st.setString(4, item.getMean());
				st.setString(5, "Chờ xác nhận");
				st.executeUpdate();	
				return true ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public boolean updateQuantity(String listToBeOrderedID) {
		try {
				 String sql= ("UPDATE ListToBeOrderedItem il "
				 		+ " SET il.notProcessQuantity =  "
				 		+ "    CASE "
				 		+ "        WHEN ( "
				 		+ "            SELECT SUM(quantity) as total_quantity "
				 		+ "            FROM Cart po "
				 		+ "            WHERE po.listToBeOrderedID = ? AND po.merchandiseCode = il.merchandiseCode "
				 		+ "        ) > il.notProcessQuantity "
				 		+ "        THEN 0 "
				 		+ "        ELSE il.notProcessQuantity - ( "
				 		+ "            SELECT SUM(quantity) as total_quantity  "
				 		+ "            FROM Cart po "
				 		+ "            WHERE po.listToBeOrderedID = ? AND po.merchandiseCode = il.merchandiseCode "
				 		+ "        ) "
				 		+ "    END "
				 		+ " WHERE il.listToBeOrderedID = ?;"
				 		);		   
				   PreparedStatement st = connection.prepareStatement(sql);
				   st.setString(1,listToBeOrderedID);
				   st.setString(2,listToBeOrderedID);
				   st.setString(3,listToBeOrderedID);
				   st.executeUpdate();	 
		   }
		  catch(SQLException e) {
			   System.out.println(e);
			   return false ;
		  } 	
		return true;
	}

	@Override
	public boolean updateOrderStatus(String listToBeOrderedID) {
		try {
			   String sql= ("SELECT SUM(quantity) AS totalQuantity "
			   		+ " FROM ListToBeOrderedItem "
			   		+ " WHERE listToBeOrderedID = ? ; ");
			   PreparedStatement st = connection.prepareStatement(sql);
			   st.setString(1,listToBeOrderedID);
			   ResultSet rs = st.executeQuery();
			   int totalQuantity = -1;
			   if (rs.next())  totalQuantity = rs.getInt("totalQuantity");
			   if(totalQuantity==0) {
				   String updateSql = "UPDATE ListToBeOrdered SET status = 'Đã xử lý' WHERE listToBeOrderedID = ? ";
				   st = connection.prepareStatement(updateSql);
				   st.setString(1,listToBeOrderedID);
				   st.executeUpdate();	
				   System.out.println("Chuyển thành Đã xử lý");
			   } 
			   else if(totalQuantity>0) {
				   String updateSql2 = "UPDATE ListToBeOrdered SET status = 'Đang xử lý' WHERE listToBeOrderedID = ? ";
				   st = connection.prepareStatement(updateSql2);
				   st.setString(1,listToBeOrderedID);
				   System.out.println("CHuyển thành Đang  xử lý");
				   st.executeUpdate();	
			   } else {
				   System.out.println("Something wrong when update status");
				   return false ;
			   }
		}  
		  catch(SQLException e) {
			   System.out.println(e);
			   return false;
		  } 	
		return true ;
	}
	
/////////////////////////    THAI TUAN NAM

	@Override
	public List<OrderModel> getAllOrder(String status) {
		List<OrderModel> orderList = new ArrayList<>();
		try {
			PreparedStatement st ;
			ResultSet rs ;
			if(status == null ) {
				String sql1 = "SELECT * FROM ProcessOrder ";
				st = connection.prepareStatement(sql1);
				rs = st.executeQuery();
			}
			else {
				String sql2 = "SELECT * FROM ProcessOrder WHERE statusOverSea = ? ;";
				st = connection.prepareStatement(sql2);
				st.setString(1, status);
				rs = st.executeQuery();
			}
			
			while (rs.next()) {
				String orderID = rs.getString("processOrderID");
				String siteCode = rs.getString("siteCode");
				String listToBeOrderedID = rs.getString("listToBeOrderedID");
				int numberOfItem = rs.getInt("numberOfItem");
				String createDate = rs.getString("createDate");
				String status1 = rs.getString("statusOverSea") ;
				OrderModel order = new OrderModel(orderID, siteCode, numberOfItem,listToBeOrderedID, createDate,
						status1);
				orderList.add(order);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return orderList;
	}

	@Override
	public List<ItemOrderToSiteModel> getAllItem(String orderId) {
		List<ItemOrderToSiteModel> itemOrderedList = new ArrayList<>();
		try {
			String sql = "SELECT POI.merchandiseCode, M.merchandiseName, POI.quantity, M.unit, POI.mean ,PO.siteCode,  PO.listToBeOrderedID  \r\n"
					+ "FROM ProcessOrderItem POI\r\n"
					+ "JOIN Merchandise M ON POI.merchandiseCode = M.merchandiseCode\r\n"
					+ "JOIN ProcessOrder PO ON POI.processOrderID = PO.processOrderID\r\n"
					+ "WHERE PO.processOrderID = ?;\r\n" + "";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, orderId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String listToBeOrderedID = rs.getString("listToBeOrderedID");
				String merchandiseCode = rs.getString("merchandiseCode");
				String merchandiseName = rs.getString("merchandiseName");
				int quantity = rs.getInt("quantity");
				String unit = rs.getString("unit");
				String mean = rs.getString("mean");
				String siteCode = rs.getString("siteCode");
//				String listToBeOrderedID, String siteCode, String merchandiseCode,String merchandiseName ,int quantity,
//				String unit, String mean
				ItemOrderToSiteModel order = new ItemOrderToSiteModel(listToBeOrderedID, siteCode, merchandiseCode, merchandiseName,quantity,
						unit, mean);
				itemOrderedList.add(order);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return itemOrderedList;
	}

	@Override
	public OrderModel printOrderDetail(String orderId) {
		OrderModel order = null;
		try {
			String sql = "SELECT * FROM ProcessOrder PO WHERE PO.processOrderID = ? ;";
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1,Integer.valueOf(orderId) );
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String listToBeOrderedID = rs.getString("listToBeOrderedID");
				int numberOfItem = rs.getInt("numberOfItem");
				String siteCode = rs.getString("siteCode");
				String status = rs.getString("statusOverSea");
				String createDate = rs.getString("createDate");
				String note1 = rs.getString("note");
				order = new OrderModel(orderId, siteCode, numberOfItem,listToBeOrderedID, createDate,status,note1);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return order;

	}
	
	@Override
	public String reOrder (List<ItemOrderToSiteModel> data, String listToBeOrdered) {
		String newReOrderListToBeOrderedID = null;
		try {
			
			String sql = "select COUNT(*) from ListToBeOrdered where track IS NOT NULL";
			PreparedStatement st1 = connection.prepareStatement(sql);
			ResultSet rs = st1.executeQuery();
			if (rs.next()) {
			    int count = rs.getInt(1);
			    newReOrderListToBeOrderedID = "RO" + String.valueOf(count + 1)  ;
			}
			
			String sql1 = "INSERT INTO ListToBeOrdered (listToBeOrderedID, createdDate , numberOfItem,track,status ) VALUES"
					+ " (?,CURDATE(),?,?,?) ;" ;
			PreparedStatement st2 = connection.prepareStatement(sql1);
			st2.setString(1,newReOrderListToBeOrderedID);
			
			st2.setInt(2,data.size() ); 
			st2.setString(3,listToBeOrdered); 
			st2.setString(4,"Đơn đặt lại"); 
			st2.executeUpdate();	
			
			for(ItemOrderToSiteModel order : data ) {
				
				String sql2 = "INSERT INTO ListToBeOrderedItem (listToBeOrderedID, merchandiseCode ,quantity, desiredDeliveryDate,notProcessQuantity ) VALUES"
						+ " (?,?,?,?,?) ;" ;
				PreparedStatement st3 = connection.prepareStatement(sql2);
				st3.setString(1,newReOrderListToBeOrderedID);
				st3.setString(2,order.getMerchandiseCode());
				st3.setInt(3, order.getQuantity());
				String date = getDesiredDeliveryDate(order.getListToBeOrderedID(),order.getMerchandiseCode());
				System.out.println("date is " + order.getListToBeOrderedID());
				System.out.println("date is " + order.getMean());
				System.out.println("date is " + date);
				st3.setString(4,date ); 
				st3.setInt(5,order.getQuantity()); 
				st3.executeUpdate();	
			  }
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		  }
		  return newReOrderListToBeOrderedID ;
	}
	
	public String getDesiredDeliveryDate(String listToBeOrderedID , String merchandiseCode) {
		String desiredDeliveryDate = new String();
		try {
			   String sql= ("SELECT * from ListToBeOrderedItem where merchandiseCode = ? AND listToBeOrderedID = ? ");
			   PreparedStatement st = connection.prepareStatement(sql);
			   st.setString(1,merchandiseCode);
			   st.setString(2,listToBeOrderedID);
			   ResultSet rs = st.executeQuery();
			   
			   if (rs.next())  desiredDeliveryDate = rs.getString("desiredDeliveryDate");
			   
		}  
		  catch(SQLException e) {
			   System.out.println(e);
	
		  } 	
		
		return desiredDeliveryDate ;
	}
	
	public static void main(String[] args) {
		MySQLOrderDB order = new MySQLOrderDB();
		OrderModel a = order.printOrderDetail("1");
		System.out.println(a.getCreatedDate());
	}

}
