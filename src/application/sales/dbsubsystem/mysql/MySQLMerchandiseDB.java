package application.sales.dbsubsystem.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import application.MySqlDB;
import application.sales.dbsubsystem.IMerchandiseDB;
import application.sales.model.MerchandiseModel;

public class MySQLMerchandiseDB extends MySqlDB  implements IMerchandiseDB {

	@Override
	public List<MerchandiseModel> getlistItem() {
		List<MerchandiseModel> list = new ArrayList<MerchandiseModel>();
		String sql = ("select * from Merchandise");
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
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
	public static void main(String[] args) {
		MySQLMerchandiseDB db = new MySQLMerchandiseDB();
		List<MerchandiseModel> list = db.getlistItem();
		for(MerchandiseModel a : list){
			System.out.println(a.getMerchandiseCode());
			System.out.println(a.getMerchandiseName());
			System.out.println(a.getUnit());
		}
	}

}
