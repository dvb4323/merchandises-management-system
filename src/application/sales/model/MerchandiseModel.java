package application.sales.model;

public class MerchandiseModel {
	
   private String merchandiseCode;
   private String merchandiseName;
   private String unit;
   
   public MerchandiseModel(  String merchandiseCode, String merchandiseName, String unit) {
	 
     this.merchandiseCode = merchandiseCode;
     this.merchandiseName = merchandiseName;
     this.unit = unit;
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
  
}
