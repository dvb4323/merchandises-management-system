package application.sales.page.viewDetailOrder;

import java.io.IOException;

import application.APageForm;
import application.sales.page.ASalePageForm;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ViewDetailOrderPageForm extends ASalePageForm{
	@FXML
	private Label orderID;
    @FXML
    private Label backToPrePageButton;
   
    
    public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/sales/view/ViewOneListToBeOrdered.fxml"));
			loader.setController(this); 
			root = loader.load();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
    
    public void setOrderID(String s) {
    	orderID.setText(s);
    }
    
    public String getOrderID() {
    	return orderID.getText();
    }
    
    public void addActionBackToPrePage(EventHandler<MouseEvent> listener) {
    	backToPrePageButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
    
}
