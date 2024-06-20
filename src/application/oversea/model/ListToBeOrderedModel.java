package application.oversea.model;

public class ListToBeOrderedModel {
	
	private String listToBeOrderedID ;
	private String createdDate ;
	private String status ;
	private int numberOfItem ;
	
	public String getListToBeOrderedID() {
		return listToBeOrderedID;
	}
	public void setListToBeOrderedID(String orderID) {
		this.listToBeOrderedID = orderID;
	}
	
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getNumberOfItem() {
		return numberOfItem;
	}
	public void setNumberOfItem(int numberOfItem) {
		this.numberOfItem = numberOfItem;
	}
	
	public ListToBeOrderedModel(String orderID, String createdDate, String status, int numberOfItem) {
		super();
		this.listToBeOrderedID = orderID;
		this.createdDate = createdDate;
		this.status = status;
		this.numberOfItem = numberOfItem;
	}		
}
