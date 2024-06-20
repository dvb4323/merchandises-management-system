package application.oversea.model;

public class ItemOrderToSiteModel {
	
	private String listToBeOrderedID ;
	private String siteCode ;
	private String merchandiseCode ;
	private String merchandiseName ;
	private int    quantity ;
	private String unit ;
	private String mean ;
		
	public String getListToBeOrderedID() {
		return listToBeOrderedID;
	}
	public void setListToBeOrderedID(String listToBeOrderedID) {
		this.listToBeOrderedID = listToBeOrderedID;
	}
	
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
	
	
	public ItemOrderToSiteModel(String listToBeOrderedID, String siteCode, String merchandiseCode, int quantity,
			String unit, String mean) {
		this.listToBeOrderedID = listToBeOrderedID;
		this.siteCode = siteCode;
		this.merchandiseCode = merchandiseCode;
		this.quantity = quantity;
		this.unit = unit;
		this.mean = mean;
	}
	
	
	/// THAI TUAN NAM
	public ItemOrderToSiteModel(String listToBeOrderedID, String siteCode, String merchandiseCode,String merchandiseName ,int quantity,
			String unit, String mean) {
		this.listToBeOrderedID = listToBeOrderedID;
		this.siteCode = siteCode;
		this.merchandiseCode = merchandiseCode;
		this.merchandiseName = merchandiseName;
		this.quantity = quantity;
		this.unit = unit;
		this.mean = mean;
   }
	
	
	

}
