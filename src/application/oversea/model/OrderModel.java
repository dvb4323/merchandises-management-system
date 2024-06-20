package application.oversea.model;

public class OrderModel {
	
	private String orderID ;
	private String siteCode ;
	private int    numberOfItem ;
	private String listToBeOrderedID ;
	private String createdDate;
	private String status;
	private String note1 ;
	public String getOrderID() {
		return orderID;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public int getNumberOfItem() {
		return numberOfItem;
	}
	public String getListToBeOrderedID() {
		return listToBeOrderedID;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public String getStatus() {
		return status;
	}
	
	public String getNote() {
		return note1;
	}
	

	
	public OrderModel(String orderID, String siteCode, int numberOfItem, String listToBeOrderedID, String createdDate,
			String status) {
		this.orderID = orderID;
		this.siteCode = siteCode;
		this.numberOfItem = numberOfItem;
		this.listToBeOrderedID = listToBeOrderedID;
		this.createdDate = createdDate;
		this.status = status;

	}
	public OrderModel(String orderID, String siteCode, int numberOfItem, String listToBeOrderedID, String createdDate,
			String status,String note1) {
		this.orderID = orderID;
		this.siteCode = siteCode;
		this.numberOfItem = numberOfItem;
		this.listToBeOrderedID = listToBeOrderedID;
		this.createdDate = createdDate;
		this.status = status;
		this.note1 = note1 ;
	}

}
