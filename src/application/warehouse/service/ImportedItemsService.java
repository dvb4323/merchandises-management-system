package application.warehouse.service;

import java.util.List;

import application.warehouse.dbsubsystem.IImportedMerchandiseDB;
import application.warehouse.dbsubsystem.myql.MySQLImportedMerchandiseDB;
import application.warehouse.dbsubsystem.myql.MySQLOrderDB;
import application.warehouse.model.ImportedMerchandiseModel;
import application.warehouse.model.MerchandiseOrderModel;
import org.json.JSONArray;
import org.json.JSONObject;
public class ImportedItemsService {
	private List<ImportedMerchandiseModel> data ;
	private IImportedMerchandiseDB db;
	
	public  ImportedItemsService(){ 
		this.db   = new MySQLImportedMerchandiseDB() ;
		
	}
	
	public JSONObject getAllItems(String merchandiseCode) {
		JSONObject res = new JSONObject();	
		JSONArray jsonArray = new JSONArray();
		data = db.getAllItems(merchandiseCode);
		for( ImportedMerchandiseModel order : data ){
			  JSONObject jsonObject = new JSONObject(order);
		 	  jsonArray.put(jsonObject);
		 }
		 res.put("listItem", jsonArray);
		 return res;
	}
	public boolean updateQuantity(String merchandiseCode, int receivedNumberOfItem) {
        return db.updateQuantity(merchandiseCode, receivedNumberOfItem);
    }
	
}
