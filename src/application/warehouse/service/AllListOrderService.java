package application.warehouse.service;

import java.util.List;

import application.warehouse.dbsubsystem.IOrderDB;
import application.warehouse.dbsubsystem.myql.MySQLOrderDB;
import application.warehouse.model.WarehouseOrderModel;
import org.json.JSONArray;
import org.json.JSONObject;
public class  AllListOrderService {
	private IOrderDB db;
	private List<WarehouseOrderModel> data ;
	
	public  AllListOrderService( ){ 
		this.db   = new MySQLOrderDB() ;
		
	}
	
	public JSONObject getAllOrders(String orderID, String orderStatus) {
		JSONObject res = new JSONObject();	
		JSONArray jsonArray = new JSONArray();
		data = db.getAllOrder(orderID, orderStatus);
		for( WarehouseOrderModel order : data ){
			  JSONObject jsonObject = new JSONObject(order);
		 	  jsonArray.put(jsonObject);
		 }
		 res.put("listOrder", jsonArray);
		 return res;
	}
}
