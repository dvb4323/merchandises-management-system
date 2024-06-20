package application.sales.page.createOrder;

import java.io.IOException;
import java.sql.Date;

import application.sales.model.DetailMerchandiseModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CreateOrderSingleForm {
	    @FXML
	    private Label date;

	    @FXML
	    private Label deleteItem;

	    @FXML
	    private Label editItem;

	    @FXML
	    private Label idItem;

	    @FXML
	    private Label itemName;

	    @FXML
	    private Label quantity;

	    @FXML
	    private Label stt;

	    @FXML
	    private Label unit;
    
	    private Parent root;
	 
	    private DetailMerchandiseModel data;  
	    public DetailMerchandiseModel getData() {
	        return data;
	    }

	    public void setData(DetailMerchandiseModel data) {
	        this.data = data;
	    }
	
	    public Parent getRoot() {
	    	try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/sales/view/DetailOrderItemSingleForm.fxml"));
	    	loader.setController(this);
	    	
		    root = loader.load();
		    root.setVisible(false);
		    root.setManaged(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	return root ;
	    }
	
	    public String getId() {return idItem.getText();}
	 
	    public void setStt(int t) {
		 stt.setText(String.valueOf(t));
	    }
	    
	    public int getStt() {
	    	return Integer.parseInt(stt.getText());
	    }
	    
	    public String getDate() {
	    	return date.getText();
	    }
	    
	    public String getQuantity() {
	    	return quantity.getText();
	    }
	
	    public String getMerchandiseCode() {
		return idItem.getText();
	}
	 
   public void disPlayData(String valueOf, String merchandiseCode, String merchandiseName, String quantityOrdered,String unit, String date) {
	    	stt.setText(valueOf);
		    idItem.setText(merchandiseCode);
		    itemName.setText(merchandiseName);
		    quantity.setText(quantityOrdered);
		    this.unit.setText(unit);
		    this.date.setText(date);
	}
	    
	public void updateItemInfo(String quantity,  String date) {
	        this.quantity.setText(quantity);
	        this.date.setText(date.toString());
	}

	 
	 public void addActionDeleteItem(EventHandler<MouseEvent> listener) {
		 deleteItem.addEventFilter(MouseEvent.MOUSE_CLICKED, listener);
	}

	 public void addActionEditItem(EventHandler<MouseEvent> listener) {
		 editItem.addEventFilter(MouseEvent.MOUSE_CLICKED, listener);
	 }

	
	 
}
