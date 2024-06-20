package application.warehouse.dbsubsystem.myql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.MySqlDB;
import application.warehouse.dbsubsystem.IImportedMerchandiseDB;
import application.warehouse.model.ImportedMerchandiseModel;


public class MySQLImportedMerchandiseDB extends MySqlDB implements IImportedMerchandiseDB {
	
	@Override
	public List<ImportedMerchandiseModel> getAllItems(String merchandiseCodes) {
		List<ImportedMerchandiseModel> list = new ArrayList<ImportedMerchandiseModel>();
		
		try {
		String sql= ("select * from importeditems ");
		   
		   
		   PreparedStatement st;
		   if(merchandiseCodes != null) {
			   sql += " where MerchandiseCode = ? " ;
			   st = connection.prepareStatement(sql);
			   
			   st.setString(1, merchandiseCodes);
			     
		   } else st = connection.prepareStatement(sql);
			
		   ResultSet rs = st.executeQuery();
	       
			while (rs.next()) {	
	    	    String merchandiseCode             = rs.getString("MerchandiseCode");	
	    	    String merchandiseName 			= rs.getString("MerchandiseName");
	    	    
	    	    int quantity	       		= rs.getInt("Quantity");
	    	    String unit   				= rs.getString("Unit");
	    	    
	            ImportedMerchandiseModel a    = new ImportedMerchandiseModel(merchandiseCode, merchandiseName, quantity, unit) ;
	            list.add(a);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean updateOrderStatus(String orderCode) {
	    String checkItemStatusSQL = "SELECT OrderStatus FROM itemwarehouseorders WHERE OrderID = ?";
	    String updateOrderStatusSQL = "UPDATE warehouseorders SET OrderStatus = ? WHERE OrderID = ?";

	    try (PreparedStatement checkStmt = connection.prepareStatement(checkItemStatusSQL)) {
	        checkStmt.setString(1, orderCode);
	        try (ResultSet rs = checkStmt.executeQuery()) {
	            boolean allDelivered = true;
	            boolean anyDelivered = false;

	            while (rs.next()) {
	                String itemStatus = rs.getString("OrderStatus");
	                if (!itemStatus.equals("Đã giao")) {
	                    allDelivered = false;
	                } else {
	                    anyDelivered = true;
	                }
	            }

	            String newOrderStatus;
	            if (allDelivered) {
	                newOrderStatus = "Đã giao";
	            } else if (anyDelivered) {
	                newOrderStatus = "Giao một phần";
	            } else {
	                newOrderStatus = "Đang xử lý";
	            }

	            try (PreparedStatement updateStmt = connection.prepareStatement(updateOrderStatusSQL)) {
	                updateStmt.setString(1, newOrderStatus);
	                updateStmt.setString(2, orderCode);
	                int rowsUpdated = updateStmt.executeUpdate();
	                return rowsUpdated > 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean updateOrderStatusIfZero(String merchandiseCode) {
	    String checkQuantitySQL = "SELECT Quantity FROM itemwarehouseorders WHERE MerchandiseCode = ?";
	    String updateStatusSQL = "UPDATE itemwarehouseorders SET OrderStatus = 'Đã giao' WHERE MerchandiseCode = ? AND Quantity = 0";

	    try (PreparedStatement checkStmt = connection.prepareStatement(checkQuantitySQL)) {
	        checkStmt.setString(1, merchandiseCode);
	        try (ResultSet rs = checkStmt.executeQuery()) {
	            if (rs.next()) {
	                int currentQuantity = rs.getInt("Quantity");
	                if (currentQuantity == 0) {
	                    try (PreparedStatement updateStmt = connection.prepareStatement(updateStatusSQL)) {
	                        updateStmt.setString(1, merchandiseCode);
	                        int rowsUpdated = updateStmt.executeUpdate();
	                        return rowsUpdated > 0;
	                    }
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	@Override
	public boolean updateQuantity(String merchandiseCode, int receivedNumberOfItem) {
		// Validate merchandiseCode
	    if (merchandiseCode == null || merchandiseCode.isEmpty()) {
	        System.err.println("Error: Merchandise code is null or empty.");
	        return false;
	    }

	    // Validate receivedNumberOfItem
	    if (receivedNumberOfItem <= 0) {
	        System.err.println("Error: Received number of items must be greater than zero.");
	        return false;
	    }
	    String checkWarehouseOrderSQL = "SELECT Quantity FROM itemwarehouseorders WHERE MerchandiseCode = ?";
	    String checkImportedItemsSQL = "SELECT Quantity FROM importeditems WHERE MerchandiseCode = ?";
	    String getOrderIDSQL = "SELECT OrderID FROM itemwarehouseorders WHERE MerchandiseCode = ?";
	    String updateImportedItemsSQL = "UPDATE importeditems SET Quantity = Quantity + ? WHERE MerchandiseCode = ?";
	    String updateWarehouseOrderSQL = "UPDATE itemwarehouseorders SET Quantity = Quantity - ? WHERE MerchandiseCode = ?";
	    try {
	    	// Check the current quantity in itemwarehouseorders
	        int currentWarehouseQuantity;
	        String orderCode = null;
	        try (PreparedStatement checkStmt = connection.prepareStatement(checkWarehouseOrderSQL)) {
	            checkStmt.setString(1, merchandiseCode);
	            try (ResultSet rs = checkStmt.executeQuery()) {
	                if (rs.next()) {
	                    currentWarehouseQuantity = rs.getInt("Quantity");
	                } else {
	                    System.err.println("Error: Merchandise code not found in warehouse orders.");
	                    return false;
	                }
	            }
	        }

	        // Check if receivedNumberOfItem is greater than currentWarehouseQuantity
	        if (receivedNumberOfItem > currentWarehouseQuantity) {
	            System.err.println("Error: Received number of items is greater than the current quantity in warehouse orders.");
	            return false;
	        }
	        
	        boolean itemExists;
	        try (PreparedStatement checkStmt = connection.prepareStatement(checkImportedItemsSQL)) {
	            checkStmt.setString(1, merchandiseCode);
	            try (ResultSet rs = checkStmt.executeQuery()) {
	                itemExists = rs.next();
	            }
	        }

	        // If the item does not exist, return false
	        if (!itemExists) {
	            System.err.println("Error: Merchandise code not found in imported items.");
	            return false;
	        }
	        
	        // Update imported items
	        try (PreparedStatement st1 = connection.prepareStatement(updateImportedItemsSQL)) {
	            st1.setInt(1, receivedNumberOfItem);
	            st1.setString(2, merchandiseCode);
	            int rowsUpdated1 = st1.executeUpdate();
	            if (rowsUpdated1 <= 0) {
	                // If no rows were updated, return false
	                return false;
	            }
	        }

	        // Update warehouse order items
	        try (PreparedStatement st2 = connection.prepareStatement(updateWarehouseOrderSQL)) {
	            st2.setInt(1, receivedNumberOfItem);
	            st2.setString(2, merchandiseCode);
	            int rowsUpdated2 = st2.executeUpdate();
	            if (rowsUpdated2 <= 0) {
	                // If no rows were updated, return false
	                return false;
	            }
	        }
	        
	     // Check and update order status if quantity becomes zero
	        updateOrderStatusIfZero(merchandiseCode);
	        
	        // Update the status of the entire order
	        try (PreparedStatement checkStmt = connection.prepareStatement(getOrderIDSQL)) {
	            checkStmt.setString(1, merchandiseCode);
	            try (ResultSet rs = checkStmt.executeQuery()) {
	            	while (rs.next()) {
	                    orderCode = rs.getString("OrderID");
	                    System.out.println(orderCode);
	                }
	            }
	        }
	        updateOrderStatus(orderCode);
	        
	        // If both updates were successful, return true
	        return true;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean getColumnName(String merchandiseCode) {
		
	    String getOrderIDSQL = "SELECT OrderID FROM itemwarehouseorders WHERE MerchandiseCode = ?";
	    
	    try {
	    	String orderCode = null;
	        
	        try (PreparedStatement checkStmt = connection.prepareStatement(getOrderIDSQL)) {
	            checkStmt.setString(1, merchandiseCode);
	            try (ResultSet rs = checkStmt.executeQuery()) {
	            	while (rs.next()) {
	                    orderCode = rs.getString("OrderID");
	                    System.out.println(orderCode);
	                }
	            }
	        }
//	        updateOrderStatus(orderCode);
	        
	        return orderCode != null;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static void main(String[] args) {
		MySQLImportedMerchandiseDB db = new MySQLImportedMerchandiseDB();
		db.getColumnName("MH001");
	}	
}
