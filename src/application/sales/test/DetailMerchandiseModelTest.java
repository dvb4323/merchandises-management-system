package application.sales.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.Test;

import application.sales.model.DetailMerchandiseModel;
 
	public class DetailMerchandiseModelTest {
	 
	    @Test
	  public  void testValidateMerchandise_QuantityEqualToZero() {
	        DetailMerchandiseModel merchandise = new DetailMerchandiseModel("M001", "Merchandise 1", 0, "Unit", "2024-12-31");
	        String result = validateMerchandise(merchandise);
	        assertEquals(result, "Quantity Ordered must be a number greater than 0.");
	    }
	 
	    @Test
	   public void testValidateMerchandise_QuantityLessThanZero() {
	        DetailMerchandiseModel merchandise = new DetailMerchandiseModel("M001", "Merchandise 1", -5, "Unit", "2024-12-31");
	        String result = validateMerchandise(merchandise);
	        assertEquals(result, "Quantity Ordered must be a number greater than 0.");
	    }
	 
	    @Test
	  public  void testValidateMerchandise_DesiredDeliveryDateNull() {
	        DetailMerchandiseModel merchandise = new DetailMerchandiseModel("M001", "Merchandise 1", 10, "Unit", null);
	        String result = validateMerchandise(merchandise);
	        assertEquals(result, "Desired Delivery Date must be a valid date and must be in the future.");
	    }
	 
	    @Test
	    public void testValidateMerchandise_DesiredDeliveryDateInThePast() {
	        DetailMerchandiseModel merchandise = new DetailMerchandiseModel("M001", "Merchandise 1", 10, "Unit", LocalDate.now().minusDays(1).toString());
	        String result = validateMerchandise(merchandise);
	        assertEquals(result, "Desired Delivery Date must be a valid date and must be in the future.");
	    }
	 
	    @Test
	    public void testValidateMerchandise_Success() {
	        DetailMerchandiseModel merchandise = new DetailMerchandiseModel("M001", "Merchandise 1", 10, "Unit", LocalDate.now().plusDays(1).toString());
	        String result = validateMerchandise(merchandise);
	        assertNull(result);
	    }
	 
	    public String validateMerchandise(DetailMerchandiseModel merchandise) {
	        int quantityOrdered = merchandise.getQuantityOrdered();
	        if (quantityOrdered <= 0) {
	            return "Quantity Ordered must be a number greater than 0.";
	        }
	 
	        String desiredDeliveryDateStr = merchandise.getDeliveryDateDesired();
	        if (desiredDeliveryDateStr == null) {
	            return "Desired Delivery Date must be a valid date and must be in the future.";
	        } else {
	            try {
	                LocalDate deliveryDate = LocalDate.parse(desiredDeliveryDateStr);
	                if (!deliveryDate.isAfter(LocalDate.now())) {
	                    return "Desired Delivery Date must be a valid date and must be in the future.";
	                }
	            } catch (DateTimeParseException e) {
	                return "Desired Delivery Date must be a valid date and must be in the future.";
	            }
	        }
	        return null ;
	    }
	}

