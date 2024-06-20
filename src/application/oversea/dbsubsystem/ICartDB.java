package application.oversea.dbsubsystem;

import java.util.List;

import application.oversea.model.ItemOrderToSiteModel;
import application.oversea.model.ListOrderToSiteModel;

public interface ICartDB {
	
	public boolean addToCart (ItemOrderToSiteModel item) ;
	public boolean checkInCart(String listToBeOrderedID, String merchandiseCode, String siteCode);
	public boolean updateCart(ItemOrderToSiteModel item);
	public boolean resetCart(String listToBeOrderedID);
	
	
	public List<ListOrderToSiteModel> getOrderFromCart(String listToBeOrderedID);
	public List<ItemOrderToSiteModel> getListItemInAnOrder(String listToBeOrderedID, String siteCode);
	public int     getQuantity(String listToBeOrderedID, String merchandiseCode, String siteCode );
	public String  getMean(String listToBeOrderedID, String merchandiseCode, String siteCode );
	
}
