package application.warehouse.model;

public class ImportedMerchandiseModel {
	private String merchandiseCode;
	private String merchandiseName;
	private int quantity;
	private String unit;
	
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
	
	public ImportedMerchandiseModel(String merchandiseCode, String merchandiseName, int quantity, String unit) {
		this.merchandiseCode = merchandiseCode;
		this.merchandiseName = merchandiseName;
		this.quantity = quantity;
		this.unit = unit;
	}
}
