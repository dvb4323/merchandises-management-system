package application.sales.model;

import java.sql.Date;

public class OrderItemModel {
	 private String merchandiseCode;
	 private String merchandiseName;
	 private int quantityOrdered;
	 private String unit;
	
	 private Date deliveryDateDesired;
	 public OrderItemModel(  String merchandiseCode, String merchandiseName, int quantityOrdered, String unit, Date deliveryDateDesired  ) {
		 
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
	   public Date getDeliveryDateDesired() {
		   return deliveryDateDesired;
	   }
}
