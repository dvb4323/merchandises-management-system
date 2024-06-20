package application.sales.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import application.sales.dbsubsystem.IDetailMerchandiseDB;
import application.sales.dbsubsystem.IOrderDB;
import application.sales.dbsubsystem.mysql.MySQLDetailMerchandiseDB;
import application.sales.dbsubsystem.mysql.MySQLOrderDB;
import application.sales.model.DetailMerchandiseModel;

public class DetailOrderItemService {

	private IDetailMerchandiseDB detailDB;
	private IOrderDB orderDB;
	
	public DetailOrderItemService (){ 
			this.detailDB  = new MySQLDetailMerchandiseDB() ;
			this.orderDB   = new MySQLOrderDB() ;
			
	}
		
	public JSONObject getdetailListItem(){
			JSONObject res = new JSONObject();	
			JSONArray jsonArray = new JSONArray();
			List<DetailMerchandiseModel>  data = detailDB.getdetailListItem();
			for(DetailMerchandiseModel order : data ){
				  JSONObject jsonObject = new JSONObject(order);
			 	  jsonArray.put(jsonObject);
			 }
			 res.put("detailItem", jsonArray);
			 return res;
	 }
	  
	 public void removeFromCart(String merchandiseCode) {
		  detailDB.removeFromCart(merchandiseCode);
	 }
	  
	 public void editItem(String id , String date , int number) {
		  detailDB.editItem(id, date, number);
	  };
	  
	 public JSONObject getDetailListItem() {
		  JSONObject res = new JSONObject();	
		  JSONArray jsonArray = new JSONArray();
		  List<DetailMerchandiseModel> list = detailDB.getdetailListItem();
		  List<String> orderedID = orderDB.getOrderedID();
		  for(DetailMerchandiseModel order : list ){
			  JSONObject jsonObject = new JSONObject(order);
		 	  jsonArray.put(jsonObject);
		  }
		  res.put("detailItem", jsonArray);
		  res.put("numberOfItem", orderedID.size());
		  return res ;
	 }
	  
	 public void createItemOrder(String merchandiseCode , int quantity, String date, int numberOfItem) {
		  orderDB.addToOrder(merchandiseCode, quantity, date, numberOfItem);
		  detailDB.removeFromCart(merchandiseCode);
	 }
	  
	 public void createOrder(int madon , String status , int numberOfItem) {
		  orderDB.addToListToBeOrder(madon,status,numberOfItem);
	 }
}
