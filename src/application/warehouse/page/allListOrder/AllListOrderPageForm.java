package application.warehouse.page.allListOrder;

import java.io.IOException;

import application.APageForm;
import application.warehouse.page.AWarehousePageForm;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class AllListOrderPageForm extends AWarehousePageForm {
    
    @FXML
    private VBox listItem;

    @FXML
    private ComboBox<String> marketplace;

    @FXML
    private VBox vpage;
    
    @FXML
    private Label orderListButton;
    
    @FXML
    private Label merchandiseListButton;
    
    public Parent getRoot() {
    	try {
  			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/warehouse/view/WarehouseListOrder.fxml"));
  			loader.setController(this); 
  			root = loader.load();
  		
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		return root;
  	}
    
    public void addActionGoToOrderList(EventHandler<MouseEvent> listener) {
    	orderListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
    
    public void addActionGoToMerchandiseList(EventHandler<MouseEvent> listener) {
    	merchandiseListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }

}
