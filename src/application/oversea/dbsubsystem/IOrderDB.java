package application.oversea.dbsubsystem;

import java.util.List;

import application.oversea.model.ItemOrderToSiteModel;
import application.oversea.model.ItemToBeOrderedModel;
import application.oversea.model.ListOrderToSiteModel;
import application.oversea.model.OrderModel;

public interface IOrderDB {	
	///
	public List<OrderModel> getAllOrder(String status);

	public List<ItemOrderToSiteModel> getAllItem(String orderId);

	public OrderModel printOrderDetail(String orderId);
	public String reOrder (List<ItemOrderToSiteModel> data, String listToBeOrdered);
    
	/////
	public boolean createOrderItem(List<ItemOrderToSiteModel> itemList) ;
	public boolean updateQuantity(String listToBeOrderedID);
	public boolean createOrder( List<ListOrderToSiteModel> a);	
	public boolean updateOrderStatus(String listToBeOrderedID);

	
}
