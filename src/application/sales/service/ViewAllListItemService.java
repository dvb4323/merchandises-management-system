package application.sales.service;


import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import application.sales.dbsubsystem.IMerchandiseDB;
import application.sales.dbsubsystem.mysql.MySQLMerchandiseDB;
import application.sales.model.MerchandiseModel;


public class ViewAllListItemService {
	  
	  private IMerchandiseDB db;

	  public ViewAllListItemService (){ 
			this.db   = new MySQLMerchandiseDB() ;
			
	  }
		
	  public JSONObject getlistItem(){
			JSONObject res = new JSONObject();	
			JSONArray jsonArray = new JSONArray();
			 List<MerchandiseModel> data = db.getlistItem();
			for(MerchandiseModel order : data ){
				  JSONObject jsonObject = new JSONObject(order);
			 	  jsonArray.put(jsonObject);
			 }
			 res.put("listItem", jsonArray);
			 return res;
	  }
}
