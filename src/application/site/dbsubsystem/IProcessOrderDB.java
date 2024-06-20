package application.site.dbsubsystem;

import java.util.List;

import application.site.model.ProcessOrderModel;

public interface IProcessOrderDB {
	public List<ProcessOrderModel> getProcessOrderByID(int orderId);
	public void updateProcessOrderStatus(int orderID ,String merchandiseCode, String newOrderStatus);

}
