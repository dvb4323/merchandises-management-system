package application.site.dbsubsystem;

import java.util.List;

import application.site.model.ViewOrderListModel;

public interface IOrderListDB {
	public List<ViewOrderListModel> getOrderList();
    public void updateOrderListStatus(int orderId, String newOrderListStatus);
    public List<ViewOrderListModel> getListToBeOrdered(Integer orderId, String orderListStatus);
}
