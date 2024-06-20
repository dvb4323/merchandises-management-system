package application.site.model;

public class ViewOrderListModel {
	private int orderId;
	private int numberOfItem;
	private String orderListStatus;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getNumberOfItem() {
		return numberOfItem;
	}
	public void setNumberOfItem(int numberOfItem) {
		this.numberOfItem = numberOfItem;
	}
	public String getOrderListStatus() {
		return orderListStatus;
	}
	public void setOrderListStatus(String orderListStatus) {
		this.orderListStatus = orderListStatus;
	}
	
	public ViewOrderListModel(int orderId, int numberOfItem, String orderListStatus) {
	
		this.orderId = orderId;
		this.numberOfItem = numberOfItem;
		this.orderListStatus = orderListStatus;
	}

}