package application.sales.dbsubsystem.mysql;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.MySqlDB;
import application.sales.dbsubsystem.IDetailMerchandiseDB;
import application.sales.dbsubsystem.IOrderDB;
import application.sales.model.DetailMerchandiseModel;
import application.sales.model.OrderItemModel;


public class MySQLOrderDB extends MySqlDB implements IOrderDB {
	  @Override
		public List<DetailMerchandiseModel> getdetailListItem() {
	        List<DetailMerchandiseModel> list = new ArrayList<>();
	        String sql = "SELECT * FROM Merchandise  INNER JOIN detaillistitem ON listItem.merchandiseCode = detaillistitem.merchandiseCode;"; 
	        try {
	            PreparedStatement st = connection.prepareStatement(sql);
	            ResultSet rs = st.executeQuery();
	            while (rs.next()) {
	                String merchandiseCode = rs.getString("merchandiseCode");
	                String merchandiseName = rs.getString("merchandiseName");
	                int quantityOrdered = rs.getInt("quantityOrdered");
	                String unit = rs.getString("unit");
	                String deliveryDateDesired = rs.getString("deliveryDateDesired");

	                DetailMerchandiseModel detailEntity = new DetailMerchandiseModel(merchandiseCode, merchandiseName, quantityOrdered, unit, deliveryDateDesired);
	                list.add(detailEntity);
	            }
	            rs.close();
	            st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return list;
	    }

	@Override
	public void addToOrder(String merchandiseCode, int quantityOrdered, String deliveryDateDesired, int orderedNumber) {
		String sql = ("INSERT INTO listtobeordereditem (listToBeOrderedID, merchandiseCode, quantity, desiredDeliveryDate, notProcessQuantity)\r\n"
				 + "VALUES (?, ?, ?, ?, ?)");
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, "DH" + Integer.toString(orderedNumber + 1));
			stmt.setString(2, merchandiseCode);
			stmt.setInt(3, quantityOrdered);
			stmt.setString(4, deliveryDateDesired.toString());
			stmt.setInt(5, quantityOrdered);

			int rowsInserted = stmt.executeUpdate();
           if (rowsInserted > 0) {
               System.out.println("Dữ liệu đã được lưu thành công! - " + rowsInserted + " rows");
           }

		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	@Override
	public void addToListToBeOrder(int madon,String status,int numberOfItem) {
		String sql = ("INSERT INTO listToBeOrdered  (listToBeOrderedID, createdDate, status,numberOfItem)\r\n"
				 + "VALUES (?, CURDATE(), ?, ?)");
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, "DH" + Integer.toString(madon + 1));
			stmt.setString(2,status );
			stmt.setInt(3, numberOfItem);	

			int rowsInserted = stmt.executeUpdate();
          if (rowsInserted > 0) {
              System.out.println("Dữ liệu đã được lưu thành công! - " + rowsInserted + " rows");
          }

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	@Override
	public List<String> getOrderedID() {
		List<String> list = new ArrayList<String>();
		try {
			String sql = ("SELECT DISTINCT listToBeOrderedID\r\n"
					+ "FROM listtobeordereditem;");
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String orderID = rs.getString("listToBeOrderedID");
				list.add(orderID);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return list;
	}

}
