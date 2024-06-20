package application.site.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;

import application.site.dbsubsystem.IOrderListDB;
import application.site.dbsubsystem.IProcessOrderDB;
import application.site.dbsubsystem.mysql.MySQLOrderList;
import application.site.dbsubsystem.mysql.MySQLProcessOrder;
import application.site.model.ProcessOrderModel;
import application.site.model.ViewOrderListModel;

public class ProcessOrderService {
	private IOrderListDB orderListDB;
	private IProcessOrderDB processOrderDB ;
	private List<ProcessOrderModel> listItem ;
	private ViewOrderListModel order ;
	public ProcessOrderService( ){ 
		this.orderListDB    = new MySQLOrderList() ;
		this.processOrderDB = new MySQLProcessOrder();
	}
	
	public JSONObject getAllItemInOrder(int orderId){
		JSONObject res = new JSONObject();	
		JSONArray jsonArray = new JSONArray();
		listItem = processOrderDB.getProcessOrderByID(orderId);
		for(ProcessOrderModel order : listItem ){
			  JSONObject jsonObject = new JSONObject(order);
		 	  jsonArray.put(jsonObject);
		 }
		 res.put("listItem", jsonArray);
		 return res;
	}
	public JSONObject getInforOrder(int orderId) {
		List<ViewOrderListModel> list = orderListDB.getOrderList() ;
		Optional<ViewOrderListModel> optionalModel = list.stream()
                .filter(model -> (orderId == model.getOrderId()))
                .findFirst();
		 if (optionalModel.isPresent()) {
	            order = optionalModel.get();
	            JSONObject res = new JSONObject(order);
	            return res ;
	        } else {
	            return null ;
	        }	
	}
	
	public void updateDataWhenAccept(int orderID ) {
		orderListDB.updateOrderListStatus(orderID, "Đang xử lý");
		for(ProcessOrderModel a : listItem) {
		     processOrderDB.updateProcessOrderStatus(orderID , a.getMerchandiseCode(), "Chờ vận chuyển");
		}
	}
	
	public void updateDataWhenReject(int orderID ) {
		orderListDB.updateOrderListStatus(orderID, "Đã hủy");
		for(ProcessOrderModel a : listItem) {
		     processOrderDB.updateProcessOrderStatus(orderID , a.getMerchandiseCode(), "Đã hủy");
		}
	}
	
	public void updateTransportStatus(int orderID , String merchandiseCode ) {
		processOrderDB.updateProcessOrderStatus(orderID , merchandiseCode, "Đã vận chuyển");
		listItem = processOrderDB.getProcessOrderByID(orderID);
		boolean check = listItem.stream()
                    .allMatch(model -> ! "Chờ vận chuyển".equals(model.getOrderStatus()));
		if (check) {
			orderListDB.updateOrderListStatus(orderID ,"Đã xử lý");
		}
	}
}
