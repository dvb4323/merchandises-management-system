package application.site.model;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProcessOrderModel {
	private int orderId; 
	private String merchandiseCode ;
	private String merchandiseName ;
	private int quantity ;
	private String unit ;
	private String mean ;
	private String orderStatus;
	
	public int GetOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public String getMerchandiseCode() {
		return merchandiseCode;
	}
	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}
	public String getMerchandiseName() {
		return merchandiseName;
	}
	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getMean() {
		return mean;
	}
	public void setMean(String mean) {
		this.mean = mean;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public ProcessOrderModel(int orderId,String merchandiseCode, String merchandiseName, int quantity, String unit, String mean,
			String orderStatus) {
		this.orderId = orderId;
		this.merchandiseCode = merchandiseCode;
		this.merchandiseName = merchandiseName;
		this.quantity = quantity;
		this.unit = unit;
		this.mean = mean;
		this.orderStatus = orderStatus;
	}
	
	
	
}
