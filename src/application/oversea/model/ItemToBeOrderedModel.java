package application.oversea.model;

public class ItemToBeOrderedModel {
	
	private String listToBeOrderedID ;
	private String merchandiseCode ;
	private String merchandiseName ;
	private int quantity ;
	private String unit ;
	private String desiredDeliveryDate ;
	private int notProcessQuantity ;
	
	
	public String getListToBeOrderedID() {
		return listToBeOrderedID;
	}

	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	public String getMerchandiseName() {
		return merchandiseName;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getUnit() {
		return unit;
	}

	public String getDesiredDeliveryDate() {
		return desiredDeliveryDate;
	}
	public int getNotProcessQuantity() {
		return notProcessQuantity;
	}
	
	public ItemToBeOrderedModel(String listToBeOrderedID, String merchandiseCode, String merchandiseName, int quantity,
			String unit, String desiredDeliveryDate, int notProcessQuantity) {

		this.listToBeOrderedID = listToBeOrderedID;
		this.merchandiseCode = merchandiseCode;
		this.merchandiseName = merchandiseName;
		this.quantity = quantity;
		this.unit = unit;
		this.desiredDeliveryDate = desiredDeliveryDate;
		this.notProcessQuantity = notProcessQuantity;
	}
	
	
	
}
