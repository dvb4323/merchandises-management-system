package application.sales.dbsubsystem;

import java.sql.Date;
import java.util.List;

import application.sales.model.DetailMerchandiseModel;

public interface IOrderDB {
	 public List<DetailMerchandiseModel> getdetailListItem();
	 public List<String> getOrderedID();
	 public void addToOrder(String merchandiseCode , int quantityOrdered, String deliveryDateDesired, int orderedNumber);
	 public void addToListToBeOrder(int madon,String status,int numberOfItem);
}
