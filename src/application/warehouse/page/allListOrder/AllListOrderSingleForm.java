package application.warehouse.page.allListOrder;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class AllListOrderSingleForm {
	private Parent root ;
	@FXML
    private Label action;

    @FXML
    private Label createđate;

    @FXML
    private Label orderID;

    @FXML
    private Label siteCode;

    @FXML
    private Label status;

    @FXML
    private Label stt;
    
    public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/warehouse/view/WarehouseListOrderSingle.fxml"));
			loader.setController(this); 
			root = loader.load();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
    
    public String getOrderID() {
    	return orderID.getText();
    }
    public void disPlayData(String... strings) {
		stt.setText(strings[0]);
		orderID.setText(strings[1]);
		createđate.setText(strings[2]);
		siteCode.setText(strings[3]);
		status.setText(strings[4]);
	}
    
	 public void addActionEventViewOrder(EventHandler<MouseEvent> listener) {
	    	action.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
	 }
	 
	 
    
}
