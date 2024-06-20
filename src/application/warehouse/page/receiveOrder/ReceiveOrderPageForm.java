package application.warehouse.page.receiveOrder;

import java.io.IOException;
import application.warehouse.page.AWarehousePageForm;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ReceiveOrderPageForm extends AWarehousePageForm {

    @FXML
    private Label backToPrePageButton;
    
    @FXML
    private Label orderListButton;
    
    @FXML
    private Label merchandiseListButton;

    @FXML
    private Label numberOfItem;

    @FXML
    private Label orderID;

    @FXML
    private Label saveOrderButton;

    @FXML
    private Label siteCode;

    @FXML
    private Label status;
    
    
    
    public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/warehouse/view/WarehouseDetailOrder.fxml"));
			loader.setController(this); 
			root = loader.load();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
    
    public void displayData(String...s ) {
    	orderID.setText(s[0]);
    	siteCode.setText(s[1]);
    	numberOfItem.setText(s[2]);
    	status.setText(s[3]);
    }
    
    public void addActionBackToPrePage(EventHandler<MouseEvent> listener) {
    	backToPrePageButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
    
    public void addActionSaveOrder(EventHandler<MouseEvent> listener) {
    	saveOrderButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
    public void addActionGoToOrderList(EventHandler<MouseEvent> listener) {
    	orderListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
    
    public void addActionGoToMerchandiseList(EventHandler<MouseEvent> listener) {
    	merchandiseListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
    
}
