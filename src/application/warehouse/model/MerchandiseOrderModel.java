package application.warehouse.model;

public class MerchandiseOrderModel {

	private String orderID ;
	private String siteCode ;
	private String merchandiseCode ;
	private int    quantity ;
	private String unit ;
	private String expectedDate ;
	private String status ;
	public String getOrderID() {
		return orderID;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public String getMerchandiseCode() {
		return merchandiseCode;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getUnit() {
		return unit;
	}
	public String getExpectedDate() {
		return expectedDate;
	}
	public String getStatus() {
		return status;
	}
	public MerchandiseOrderModel(String orderID, String siteCode, String merchandiseCode, int quantity, String unit,
			String expectedDate, String status) {
		super();
		this.orderID = orderID;
		this.siteCode = siteCode;
		this.merchandiseCode = merchandiseCode;
		this.quantity = quantity;
		this.unit = unit;
		this.expectedDate = expectedDate;
		this.status = status;
	}

	
	
}
