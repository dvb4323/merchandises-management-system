package application.oversea.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import application.oversea.dbsubsystem.ICartDB;
import application.oversea.dbsubsystem.IListToBeOrderedDB;
import application.oversea.dbsubsystem.IOrderDB;
import application.oversea.dbsubsystem.mysql.MySQLCartDB;
import application.oversea.dbsubsystem.mysql.MySQLListToBeOrderedDB;
import application.oversea.dbsubsystem.mysql.MySQLOrderDB;
import application.oversea.model.ItemOrderToSiteModel;
import application.oversea.model.ListOrderToSiteModel;
import application.oversea.model.ListToBeOrderedModel;

public class CreateOrderService {
	private IOrderDB orderDB ;
	private ICartDB  cartDB;
	private List<ListOrderToSiteModel> data ;
	
	public CreateOrderService( ){ 
		this.orderDB   = new MySQLOrderDB() ;
		this.cartDB    = new MySQLCartDB() ;	
	}
	
	public JSONObject getAllListToOrderInCart(String listToBeOrderedID) {
		JSONObject res = new JSONObject();	
		JSONArray jsonArray = new JSONArray();
		data = cartDB.getOrderFromCart(listToBeOrderedID) ;
		for(ListOrderToSiteModel order : data ){
			  JSONObject jsonObject = new JSONObject(order);
		 	  jsonArray.put(jsonObject);
		 }
		 res.put("listOrderItem", jsonArray);
		 return res;
	}
	
	public JSONArray getListItemInAnOrder(String listToBeOrderedID, String siteCode) {
		JSONArray jsonArray = new JSONArray();
		List<ItemOrderToSiteModel> list =  cartDB.getListItemInAnOrder(listToBeOrderedID, siteCode);
		for(ItemOrderToSiteModel order : list ){
			  JSONObject jsonObject = new JSONObject(order);
		 	  jsonArray.put(jsonObject);
		 }
		 return jsonArray;
	}
	
	public void createOrder(String listToBeOrderedID ) {
		orderDB.createOrder( data);
		for(ListOrderToSiteModel i : data) {
			List<ItemOrderToSiteModel> list = cartDB.getListItemInAnOrder(listToBeOrderedID, i.getSiteCode());
			System.out.println(list);
			orderDB.createOrderItem(list);
		}
		orderDB.updateQuantity(listToBeOrderedID);
		cartDB.resetCart(listToBeOrderedID);
		orderDB.updateOrderStatus(listToBeOrderedID);	
	}
}
