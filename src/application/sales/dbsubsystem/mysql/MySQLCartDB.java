package application.sales.dbsubsystem.mysql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.MySqlDB;
import application.sales.dbsubsystem.ICartDB;
import application.sales.model.MerchandiseModel;

public class MySQLCartDB extends MySqlDB implements ICartDB{

	@Override
	public List<MerchandiseModel> getlistItem() {
		List<MerchandiseModel> list = new ArrayList<MerchandiseModel>();
		try {
			String sql = ("SELECT * FROM Merchandise  INNER JOIN detaillistitem ON listItem.merchandiseCode = detaillistitem.merchandiseCode;");
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String merchandiseCode = rs.getString("merchandiseCode");
				String merchandiseName = rs.getString("merchandiseName");
				String unit = rs.getString("unit");
				MerchandiseModel a = new MerchandiseModel(merchandiseCode,merchandiseName, unit);
				list.add(a);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}	
	
	public void insertCart(String id, int quantity, String date) {
		String sql = ("INSERT INTO detaillistitem (merchandiseCode, quantityOrdered, deliveryDateDesired)\r\n"
				 + "VALUES (?, ?, ?)");
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setInt(2, quantity);
			stmt.setString(3, date);

			int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Dữ liệu đã được lưu thành công! - " + rowsInserted + " rows");
            }

		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
}
	
	
	

