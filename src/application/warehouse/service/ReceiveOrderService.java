package application.warehouse.service;

import java.util.ArrayList;
import java.util.List;

import application.warehouse.dbsubsystem.IOrderDB;
import application.warehouse.dbsubsystem.myql.MySQLOrderDB;
import application.warehouse.model.MerchandiseOrderModel;
import application.warehouse.model.WarehouseOrderModel;
import org.json.JSONArray;
import org.json.JSONObject;
public class ReceiveOrderService {
	private List<MerchandiseOrderModel> data = new ArrayList<MerchandiseOrderModel>();
	private IOrderDB  db;
	
	public  ReceiveOrderService(){ 
		this.db   = new MySQLOrderDB() ;
		
	}
	
	public JSONObject getAllItemInOrder(String orderID) {
		JSONObject res = new JSONObject();	
		JSONArray jsonArray = new JSONArray();
		data = db.getAllItemInOrder(orderID);
		for( MerchandiseOrderModel order : data ){
			  JSONObject jsonObject = new JSONObject(order);
		 	  jsonArray.put(jsonObject);
		 }
		 res.put("listItem", jsonArray);
		 return res;
	}
	
	public JSONObject getOrderDetail(String orderID) {
		List<WarehouseOrderModel> data2 = db.getAllOrder(orderID,null);
		JSONObject jsonObject = new JSONObject(data2.get(0));

		 return jsonObject;
	}
	
}
