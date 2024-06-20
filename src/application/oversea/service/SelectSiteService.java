package application.oversea.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONObject;
import application.oversea.dbsubsystem.ICartDB;
import application.oversea.dbsubsystem.IListToBeOrderedDB;
import application.oversea.dbsubsystem.ISiteSupplyDB;
import application.oversea.dbsubsystem.mysql.MySQLCartDB;
import application.oversea.dbsubsystem.mysql.MySQLListToBeOrderedDB;
import application.oversea.dbsubsystem.mysql.MySQLSiteSupplyDB;
import application.oversea.model.ItemOrderToSiteModel;
import application.oversea.model.ItemToBeOrderedModel;
import application.oversea.model.SiteSupplyModel;

public class SelectSiteService {
	private ISiteSupplyDB  siteSupplyDB;
	private ICartDB cartDB ;
	private IListToBeOrderedDB listToBeOrderedDB ;
	private List<ItemOrderToSiteModel> list = new ArrayList<ItemOrderToSiteModel>();
	
	public SelectSiteService( ){ 
		this.siteSupplyDB   = new MySQLSiteSupplyDB() ;
		this.cartDB         = new MySQLCartDB() ;	
		this.listToBeOrderedDB = new MySQLListToBeOrderedDB();
	}
	
	ItemToBeOrderedModel order ;
	
	public JSONObject getOrderItemDetail(String listToBeOrderedID , String merchandiseCode) {
		List<ItemToBeOrderedModel> data =  listToBeOrderedDB.getListToBeOrderedItem(listToBeOrderedID) ; 
		Optional<ItemToBeOrderedModel> optionalModel = data.stream()
                .filter(model -> (merchandiseCode.equals(model.getMerchandiseCode()))) 
                .findFirst();
		JSONObject res = new JSONObject();	

		 if (optionalModel.isPresent()) {
	            order = optionalModel.get();
	            res = new JSONObject(order);
	            return res ;
	        }
		 return res ;
	}
	
	public JSONObject getSiteSupply( String merchandiseCode) {
		JSONObject res = new JSONObject();	
		List<SiteSupplyModel> data = siteSupplyDB.findSite(merchandiseCode);
		JSONArray jsonArray = new JSONArray();
		for(SiteSupplyModel order : data ){
			  JSONObject jsonObject = new JSONObject(order);
		 	  jsonArray.put(jsonObject);
		 }
		 res.put("listSite", jsonArray);
		 return res;
	}
	
	public boolean checkInCart(String listToBeOrderedId, String merchandiseCode,String siteCode) {
		return cartDB.checkInCart(listToBeOrderedId, merchandiseCode, siteCode);
	}
	
	public int getItemQuantityInCart(String listToBeOrderedId, String merchandiseCode,String siteCode) {
		 return cartDB.getQuantity(listToBeOrderedId, merchandiseCode, siteCode) ;
	}
	
	public String getItemTransportInCart(String listToBeOrderedId, String merchandiseCode,String siteCode) {
		return cartDB.getMean(listToBeOrderedId, merchandiseCode, siteCode);
	}
	
	public void addSelectItemToList(String listToBeOrderedID, String siteCode, 
			String merchandiseCode, int quantity ,String unit , String mean) {
		ItemOrderToSiteModel en = new ItemOrderToSiteModel(listToBeOrderedID, siteCode, 
				merchandiseCode,quantity ,unit, mean);
		list.add(en);
	}
	
	public void resetList() {
		list.clear();
	}
	
	public void updateCart(String listToBeOrderedId, String merchandiseCode) {
		for(ItemOrderToSiteModel a : list) {
			 ///Kiểm tra số lượng có hợp lệ thì cho vào giỏ 
			   if(cartDB.checkInCart(listToBeOrderedId, merchandiseCode, a.getSiteCode())) {
					 cartDB.updateCart(a);
				 }
				 else {
					 if(cartDB.addToCart(a)) System.out.println("successfully inserted");
					 else System.out.println("Failed to inserted");
				  }	
			    }
	}

}
