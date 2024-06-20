package application.sales.page.viewListItem;
import java.io.IOException;

import application.APageForm;
import application.sales.page.ASalePageForm;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader ;
import javafx.scene.layout.VBox;

public class ViewAllListItemPageForm extends ASalePageForm{
	
    @FXML
    private Label action;

    @FXML
    private Label idItem;

    @FXML
    private Label nameItem;

    @FXML
    private Label unit;
    @FXML
    private VBox createOrderButton;

    @FXML
    private Label stt;
    
    public Parent getRoot() {
    	try {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/sales/view/ViewAllListItem.fxml"));
    	loader.setController(this);
    	
	    root = loader.load();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	
    	return root ;
    }
    
	public void addActionCreateOrder(EventHandler<MouseEvent> listener) {
    	createOrderButton.addEventFilter(MouseEvent.MOUSE_CLICKED, listener);
    }
  
}
