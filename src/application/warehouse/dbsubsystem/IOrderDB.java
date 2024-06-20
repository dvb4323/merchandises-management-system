package application.warehouse.dbsubsystem;

import java.util.List;

import application.warehouse.model.MerchandiseOrderModel;
import application.warehouse.model.WarehouseOrderModel;
public interface IOrderDB {
	public List<WarehouseOrderModel> getAllOrder(String orderID, String orderStatus);
	public List<MerchandiseOrderModel> getAllItemInOrder(String orderID);
}
