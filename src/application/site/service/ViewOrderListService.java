package application.site.service;

import org.json.JSONArray;
import org.json.JSONObject;
import application.site.dbsubsystem.IOrderListDB;
import application.site.dbsubsystem.mysql.MySQLOrderList;
import application.site.model.ViewOrderListModel;

public class ViewOrderListService {
	
	private IOrderListDB orderListDB;
	
	public ViewOrderListService( ){ 
		this.orderListDB = new MySQLOrderList() ;
	}
	
	public JSONObject getAllOrder(){
		JSONObject res = new JSONObject();	
		JSONArray jsonArray = new JSONArray();
		for(ViewOrderListModel order : orderListDB.getOrderList()) 	{
			  JSONObject jsonObject = new JSONObject(order);
		 	  jsonArray.put(jsonObject);
		 }
		res.put("listOrder", jsonArray);
		 return res;
	}
	
	public JSONObject getListToBeOrdered(Integer orderId, String orderListStatus){
		JSONObject res = new JSONObject();	
		JSONArray jsonArray = new JSONArray();
		for(ViewOrderListModel order : orderListDB.getListToBeOrdered(orderId, orderListStatus))	{
			  JSONObject jsonObject = new JSONObject(order);
		 	  jsonArray.put(jsonObject);
		 }
		res.put("listOrder", jsonArray);
		 return res;
	}
	

	
}
