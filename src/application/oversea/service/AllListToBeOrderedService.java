package application.oversea.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import application.oversea.dbsubsystem.IListToBeOrderedDB;
import application.oversea.dbsubsystem.mysql.MySQLListToBeOrderedDB;
import application.oversea.model.ListToBeOrderedModel;


public class AllListToBeOrderedService {
	private IListToBeOrderedDB  db;
	private List<ListToBeOrderedModel> data ;
	
	public AllListToBeOrderedService( ){ 
		this.db   = new MySQLListToBeOrderedDB() ;
		
	}
	
	public JSONObject getAllListToBeOrdered(String listOrderID, String status) {
		JSONObject res = new JSONObject();	
		JSONArray jsonArray = new JSONArray();
		data = db.getListToBeOrdered(listOrderID,status);
		for(ListToBeOrderedModel order : data ){
			  JSONObject jsonObject = new JSONObject(order);
		 	  jsonArray.put(jsonObject);
		 }
		 res.put("listOrder", jsonArray);
		 return res;
	}
}
