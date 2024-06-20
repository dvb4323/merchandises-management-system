
package application.site.dbsubsystem.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import application.MySqlDB;
import application.site.dbsubsystem.IProcessOrderDB;
import application.site.model.ProcessOrderModel;

public class MySQLProcessOrder extends MySqlDB implements IProcessOrderDB {

    @Override
    public List<ProcessOrderModel> getProcessOrderByID(int orderID) {
        List<ProcessOrderModel> list = new ArrayList<ProcessOrderModel>();
        String sql = "SELECT * FROM ProcessOrder a JOIN processorderItem b ON a.processorderID = b.processorderID \r\n"
        		+ "							       JOIN Merchandise c ON b.merchandiseCode = c.merchandiseCode \r\n"
        		+ " where a.processOrderID = ?   ;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
               
                String merchandiseCode = rs.getString("merchandiseCode");
                String merchandiseName = rs.getString("merchandiseName");
                int quantity = rs.getInt("quantity");
                String unit = rs.getString("unit");
                String mean = rs.getString("mean");
                String orderStatus = rs.getString("status");

                ProcessOrderModel a = new ProcessOrderModel(orderID, merchandiseCode, merchandiseName, quantity, unit, mean, orderStatus);
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void updateProcessOrderStatus(int orderID ,String merchandiseCode, String newOrderStatus) {
        String sql = "UPDATE ProcessOrderItem SET status = ? WHERE processOrderID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newOrderStatus);
//            st.setString(2, merchandiseCode);
            st.setInt(2, orderID);

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Cập nhật trạng thái đơn hàng thành công.");
            } else {
                System.out.println("Không tìm thấy hàng hóa với mã: " + merchandiseCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MySQLProcessOrder db = new MySQLProcessOrder();
        
        // Order ID and merchandise code to test
        int orderID = 124;
        String merchandiseCode = "MH20210855";
        
        // Print status before update
        System.out.println("Trước khi cập nhật:");
        List<ProcessOrderModel> listBeforeUpdate = db.getProcessOrderByID(orderID);
        for (ProcessOrderModel a : listBeforeUpdate) {
            System.out.println("Mã hàng hóa: " + a.getMerchandiseCode());
            System.out.println("Tên hàng hóa: " + a.getMerchandiseName());
            System.out.println("Trạng thái đơn hàng: " + a.getOrderStatus());
        }

        // Update order status
//        db.updateProcessOrderStatus(merchandiseCode, "Đã vận chuyển");
//
//        // Print status after update
//        System.out.println("\nSau khi cập nhật:");
//        List<ProcessOrderModel> listAfterUpdate = db.getProcessOrderByID(orderID);
//        for (ProcessOrderModel a : listAfterUpdate) {
//            System.out.println("Mã hàng hóa: " + a.getMerchandiseCode());
//            System.out.println("Tên hàng hóa: " + a.getMerchandiseName());
//            System.out.println("Trạng thái đơn hàng: " + a.getOrderStatus());
//        }
    }
}

