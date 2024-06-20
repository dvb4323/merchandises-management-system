package application.sales.dbsubsystem.mysql;

import application.sales.dbsubsystem.IDetailMerchandiseDB;
import application.sales.model.DetailMerchandiseModel;
import application.MySqlDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class MySQLDetailMerchandiseDB extends MySqlDB implements IDetailMerchandiseDB {

    @Override
    public List<DetailMerchandiseModel> getdetailListItem() {
        List<DetailMerchandiseModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Merchandise  INNER JOIN detaillistitem ON Merchandise.merchandiseCode = detaillistitem.merchandiseCode;"; 
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
    
    public static void main(String[] args) {
        MySQLDetailMerchandiseDB db = new MySQLDetailMerchandiseDB();
        List<DetailMerchandiseModel> list = db.getdetailListItem();
        for (DetailMerchandiseModel a : list) {
            System.out.println(a.getMerchandiseCode());
            System.out.println(a.getMerchandiseName());
            System.out.println(a.getQuantityOrdered());
            System.out.println(a.getUnit());
            System.out.println(a.getDeliveryDateDesired());
        }
    }
	@Override
	public void removeFromCart(String id) {
		String sql = ("delete from detaillistitem \r\n"
				 + "WHERE merchandiseCode = ? ;");
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, id);

			int rowsInserted = stmt.executeUpdate();
           if (rowsInserted > 0) {
               System.out.println("Dữ liệu đã được xoa thành công! - " + rowsInserted + " rows");
           }

		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	@Override
	public void editItem(String id, String date, int quantity) {
		String sql = ("update from detaillistitem \r\n"
				 + "SET quantityOrdered = ? ,deliveryDateDesired = ? \r\n"
				  + "WHERE merchandiseCode = ? ;");
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, quantity);
			stmt.setString(2, date);
			stmt.setString(3, id);
			
 
			int rowsInserted = stmt.executeUpdate();
           if (rowsInserted > 0) {
               System.out.println("Dữ liệu đã được sửa  thành công! - " + rowsInserted + " rows");
           }
 
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

	
}
