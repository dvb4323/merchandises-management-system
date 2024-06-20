package application.oversea.page.detailListToBeOrdered;

import java.io.IOException;

import application.oversea.page.AOverseaPageForm;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class DetailListToBeOrderedPageForm extends AOverseaPageForm {
	
	@FXML
	private Label orderID;
    @FXML
    private Label backToPrePageButton;
    @FXML
    private Label orderButton;
    
    @FXML
    private Label status;
    
    public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/oversea/view/ViewOneListToBeOrdered.fxml"));
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
    
    public void setStatus(String s) {
    	status.setText(s);
    	if     (s.equals("Đang xử lý"))  status.setStyle ("-fx-background-color : #E6F0DC ;");
     	else if(s.equals("Chưa xử lý"))  status.setStyle("-fx-background-color : #ebbda5;" );
    }
    
    public void addActionBackToPrePage(EventHandler<MouseEvent> listener) {
    	backToPrePageButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
    
    public void addActionOrder(EventHandler<MouseEvent> listener) {
    	orderButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
	
}
