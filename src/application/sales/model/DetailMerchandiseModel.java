package application.sales.model;


public class DetailMerchandiseModel {
	 private String merchandiseCode;
	 private String merchandiseName;
	 private int quantityOrdered;
	 private String unit;
	
	 private String deliveryDateDesired;
	 public DetailMerchandiseModel(  String merchandiseCode, String merchandiseName, int quantityOrdered, String unit, String deliveryDateDesired  ) {
		 
	     this.merchandiseCode = merchandiseCode;
	     this.merchandiseName = merchandiseName;
	    
	     this.quantityOrdered = quantityOrdered;
	     this.unit = unit;
	     this.deliveryDateDesired = deliveryDateDesired;
	     
	 }

	   public String getMerchandiseCode() {
		   return merchandiseCode;
	   }
	   public String getMerchandiseName() {
		   return merchandiseName;
	   }
	   public String getUnit() {
		   return unit;
	   }
	   public int getQuantityOrdered() {
		   return quantityOrdered;
	   }
	   public String getDeliveryDateDesired() {
		   return deliveryDateDesired;
	   }
	   public void setQuantityOrdered(int quantityordered) {
	        this.quantityOrdered = quantityordered;
	    }
	  

	    public void setDesiredDeliveryDate(String desiredDeliveryDate) {
	        this.deliveryDateDesired = desiredDeliveryDate;
	    }
}
