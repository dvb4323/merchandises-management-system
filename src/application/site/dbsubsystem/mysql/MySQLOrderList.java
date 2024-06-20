//package application.site.dbsubsystem.mysql;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.ArrayList;
//import application.MySqlDB;
//import application.site.dbsubsystem.IOrderListDB;
////import application.site.dbsubsystem.IProcessOrderDB;
////import application.site.entity.ProcessOrderEntity;
//import application.site.entity.ViewOrderListEntity;
//public class MySQLOrderList  extends MySqlDB implements IOrderListDB{
//
//	@Override
//	public List<ViewOrderListEntity> getOrderList(){
//		List<ViewOrderListEntity> list = new ArrayList<ViewOrderListEntity>();
//		String sql = ("select * from OrderListDB ;");
//		
//		try {
//			PreparedStatement st = connection.prepareStatement(sql);
//			ResultSet rs = st.executeQuery();
//			while (rs.next()) {
//				int orderId = rs.getInt("orderID");
//			    int numberOfItem = rs.getInt("numberOfItem");
//			    String orderListStatus = rs.getString("orderListStatus");
//			    ViewOrderListEntity a = new ViewOrderListEntity(orderId ,numberOfItem ,orderListStatus);
//			    list.add(a);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	return list ;
//	
//	}
//	
//	@Override
//    public void updateOrderListStatus(int orderId, String newOrderListStatus) {
//        String sql = "UPDATE OrderListDB SET orderListStatus = ?  WHERE  orderId = ?;";
//
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, newOrderListStatus);
//            st.setInt(2, orderId);
//
//            int rowsUpdated = st.executeUpdate();
//
//            if (rowsUpdated > 0) {
//                System.out.println("Cập nhật trạng thái đơn hàng thành công.");
//            } else {
//                System.out.println("Không tìm thấy hàng hóa với mã: " + orderId);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//	
//	public static void main(String[] args) {
////		MySQLOrderList db = new MySQLOrderList();
////		List<ViewOrderListEntity> list = db.getOrderList();
////		for(ViewOrderListEntity a : list) {
////			System.out.println(a.getOrderId());
////			System.out.println(a.getNumberOfItem());
////		}
//		
//		
//	        MySQLOrderList db = new MySQLOrderList();
//
//	        // Trước khi cập nhật
//	        System.out.println("Trước khi cập nhật:");
//	        List<ViewOrderListEntity> list = db.getOrderList();
//	        for (ViewOrderListEntity a : list) {
//	            System.out.println("Order ID: " + a.getOrderId());
//	            System.out.println("Number of Items: " + a.getNumberOfItem());
//	            System.out.println("Order List Status: " + a.getOrderListStatus());
//	        }
//
//	        // Cập nhật trạng thái đơn hàng
//	        int orderIdToUpdate = 1; // Thay đổi ID đơn hàng mà bạn muốn cập nhật
//	        String newStatus = "Đang xử lí"; // Trạng thái mới
//	        db.updateOrderListStatus(orderIdToUpdate, newStatus);
//
//	        // Sau khi cập nhật
//	        System.out.println("\nSau khi cập nhật:");
//	        list = db.getOrderList();
//	        for (ViewOrderListEntity a : list) {
//	            System.out.println("Order ID: " + a.getOrderId());
//	            System.out.println("Number of Items: " + a.getNumberOfItem());
//	            System.out.println("Order List Status: " + a.getOrderListStatus());
//	        }
//	    }
//	
//}
//   
//}

package application.site.dbsubsystem.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import application.MySqlDB;
import application.site.dbsubsystem.IOrderListDB;
import application.site.model.ViewOrderListModel;

public class MySQLOrderList extends MySqlDB implements IOrderListDB {

    @Override
    public List<ViewOrderListModel> getOrderList() {
        List<ViewOrderListModel> list = new ArrayList<ViewOrderListModel>();
        String sql = "SELECT * FROM ProcessOrder;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("processOrderID");
                int numberOfItem = rs.getInt("numberOfItem");
                String orderListStatus = rs.getString("statusSite");
                ViewOrderListModel a = new ViewOrderListModel(orderId, numberOfItem, orderListStatus);
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void updateOrderListStatus(int orderId, String newOrderListStatus) {
    	String sql = new String();
    	try {
        if        (newOrderListStatus.equals("Đã hủy")) {
        	sql = "UPDATE ProcessOrder SET statusSite = ? , statusOverSea = \"Đã hủy\" WHERE processOrderID = ? ;";
        } else if (newOrderListStatus.equals("Đang xử lý")) {
        	sql = "UPDATE ProcessOrder SET statusSite = ? , statusOverSea = \"Đã xác nhận\" WHERE processOrderID = ? ;";
        } else {
        	sql = "UPDATE ProcessOrder SET statusSite = ?  WHERE processOrderID = ? ;";
        }
        
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, newOrderListStatus);
        st.setInt(2, orderId);

        int rowsUpdated = st.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Cập nhật trạng thái đơn hàng thành công.");
        } else {
            System.out.println("Không tìm thấy đơn hàng với mã: " + orderId);
        }    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
// // Thêm phương thức tìm kiếm theo orderListStatus
//    public List<ViewOrderListModel> getOrderListByStatus(String orderListStatus) {
//        List<ViewOrderListModel> list = new ArrayList<ViewOrderListModel>();
//        String sql = "SELECT * FROM OrderListDB WHERE orderListStatus = ?;";
//
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, orderListStatus);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                int orderId = rs.getInt("orderID");
//                int numberOfItem = rs.getInt("numberOfItem");
//                String status = rs.getString("orderListStatus");
//                ViewOrderListModel a = new ViewOrderListModel(orderId, numberOfItem, status);
//                list.add(a);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }

    
 // Thêm phương thức tìm kiếm theo orderListStatus và orderID
    public List<ViewOrderListModel> getListToBeOrdered(Integer orderId, String orderListStatus) {
        List<ViewOrderListModel> list = new ArrayList<>();
 
        try {
        	StringBuilder sql = new StringBuilder("SELECT * FROM ProcessOrder WHERE 1=1 ");
            List<Object> parameters = new ArrayList<>();
            
            if (orderId != null && orderListStatus != null) {
                sql.append(" AND processOrderID = ? AND statusSite = ?");
                parameters.add(orderId);
                parameters.add(orderListStatus);
            } else if (orderId != null && orderListStatus == null) {
                sql.append(" AND processOrderID = ?");
                parameters.add(orderId);
            } else if (orderListStatus != null && orderId == null) {
                sql.append(" AND statusSite  = ?");
                parameters.add(orderListStatus);
            }
 
            PreparedStatement st = connection.prepareStatement(sql.toString());
 
            for (int i = 0; i < parameters.size(); i++) {
                if (parameters.get(i) instanceof Integer) {
                    st.setInt(i + 1, (Integer) parameters.get(i));
                } else if (parameters.get(i) instanceof String) {
                    st.setString(i + 1, (String) parameters.get(i));
                }
            }
 
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int fetchedOrderId = rs.getInt("ProcessOrderID");
                int numberOfItem = rs.getInt("numberOfItem");
                String status = rs.getString("statusSite");
                ViewOrderListModel a = new ViewOrderListModel(fetchedOrderId, numberOfItem, status);
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return list;
    }
    
    public static void main(String[] args) {
        MySQLOrderList db = new MySQLOrderList();
//
//        // Trước khi cập nhật
//        System.out.println("Trước khi cập nhật:");
//        List<ViewOrderListEntity> list = db.getOrderList();
//        for (ViewOrderListEntity a : list) {
//            System.out.println("Order ID: " + a.getOrderId());
//            System.out.println("Number of Items: " + a.getNumberOfItem());
//            System.out.println("Order List Status: " + a.getOrderListStatus());
//        }
//
//        // Cập nhật trạng thái đơn hàng
//        int orderId = 123; // Thay đổi ID đơn hàng mà bạn muốn cập nhật
//        String newOrderListStatus = "Đang xử lí"; // Trạng thái mới
//        db.updateOrderListStatus(orderId, newOrderListStatus);
//
//        // Sau khi cập nhật
//        System.out.println("\nSau khi cập nhật:");
//        list = db.getOrderList();
//        for (ViewOrderListEntity a : list) {
//            System.out.println("Order ID: " + a.getOrderId());
//            System.out.println("Number of Items: " + a.getNumberOfItem());
//            System.out.println("Order List Status: " + a.getOrderListStatus());
//        }
        
        // Tìm kiếm theo status
        
//        String statusToSearch = "Đang xử lí"; // Trạng thái cần tìm kiếm
//        List<ViewOrderListModel> searchResults = db.getOrderListByStatus(statusToSearch);
// 
//        System.out.println("\nTìm kiếm theo trạng thái '" + statusToSearch + "':");
//        for (ViewOrderListModel a : searchResults) {
//            System.out.println("Order ID: " + a.getOrderId());
//            System.out.println("Number of Items: " + a.getNumberOfItem());
//            System.out.println("Order List Status: " + a.getOrderListStatus());
//        }
    
        
//Tìm kiếm theo orderIB và status
        int orderIDToSearch = 124;
        String statusToSearch = null; // Trạng thái cần tìm kiếm
        List<ViewOrderListModel> searchResults = db.getListToBeOrdered(orderIDToSearch,statusToSearch);
 
        System.out.println("\nTìm kiếm theo ID = " + orderIDToSearch + " và trạng thái '" + statusToSearch + "':");
        for (ViewOrderListModel a : searchResults) {
            System.out.println("Order ID: " + a.getOrderId());
            System.out.println("Number of Items: " + a.getNumberOfItem());
            System.out.println("Order List Status: " + a.getOrderListStatus());
        }
    }
}
 