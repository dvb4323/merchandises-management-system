package application.oversea.model;

public class SiteSupplyModel {
	
	private String siteCode ;
	private String merchandiseCode ;
	private int inStockQuantity ;
	private String unit ;
	private int shipDeliveryDay ;
	private int airDeliveryDay;
	
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getMerchandiseCode() {
		return merchandiseCode;
	}
	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}
	public int getInStockQuantity() {
		return inStockQuantity;
	}
	public void setInStockQuantity(int inStockQuantity) {
		this.inStockQuantity = inStockQuantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getShipDeliveryDay() {
		return shipDeliveryDay;
	}

	public int getAirDeliveryDay() {
		return airDeliveryDay;
	}

	
	public SiteSupplyModel(String siteCode, String merchandiseCode, int inStockQuantity, String unit,
			int shipDeliveryDate, int airDeliveryDate) {

		this.siteCode = siteCode;
		this.merchandiseCode = merchandiseCode;
		this.inStockQuantity = inStockQuantity;
		this.unit = unit;
		this.shipDeliveryDay = shipDeliveryDate;
		this.airDeliveryDay = airDeliveryDate;
	}
	
	public SiteSupplyModel(String siteCode, String merchandiseCode, int inStockQuantity,
			int shipDeliveryDate, int airDeliveryDate) {

		this.siteCode = siteCode;
		this.merchandiseCode = merchandiseCode;
		this.inStockQuantity = inStockQuantity;
		this.shipDeliveryDay = shipDeliveryDate;
		this.airDeliveryDay  = airDeliveryDate;
	}
	
		
}
