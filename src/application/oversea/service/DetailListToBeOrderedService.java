package application.oversea.service;

import java.util.List;
import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONObject;
import application.oversea.dbsubsystem.IListToBeOrderedDB;
import application.oversea.dbsubsystem.mysql.MySQLListToBeOrderedDB;
import application.oversea.model.ItemToBeOrderedModel;
import application.oversea.model.ListToBeOrderedModel;

public class DetailListToBeOrderedService {
	private IListToBeOrderedDB  db ;
	private List<ItemToBeOrderedModel> data ;

	public DetailListToBeOrderedService( ){ 
		this.db   = new MySQLListToBeOrderedDB() ;
		
	}
	
	public JSONObject getAllListToBeOrdered(String listToBeOrderedID) {
		JSONObject res = new JSONObject();	
		JSONArray jsonArray = new JSONArray();
		data = db.getListToBeOrderedItem(listToBeOrderedID);
		for(ItemToBeOrderedModel order : data ){
			  JSONObject jsonObject = new JSONObject(order);
		 	  jsonArray.put(jsonObject);
		 }
		 res.put("listItem", jsonArray);
		 return res;
	}
	
	public JSONObject getInforOrder(String listToBeOrderedID) {
		System.out.println(listToBeOrderedID);
		List<ListToBeOrderedModel> list = db.getListToBeOrdered(listToBeOrderedID, null) ;
		Optional<ListToBeOrderedModel> optionalModel = list.stream()
                .filter(model -> (listToBeOrderedID.equals(model.getListToBeOrderedID())))
                .findFirst();
		ListToBeOrderedModel order ;
		 if (optionalModel.isPresent()) {
	            order = optionalModel.get();
	            JSONObject res = new JSONObject(order);
	            return res ;
	        } else {
	            return null ;
	        }	
	}
}
