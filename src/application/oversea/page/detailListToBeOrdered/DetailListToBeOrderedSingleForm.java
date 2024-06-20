package application.oversea.page.detailListToBeOrdered;

import java.io.IOException;

import application.ASingleForm;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class DetailListToBeOrderedSingleForm extends ASingleForm {
	private Parent root ;
	@FXML
    private Label stt;
	@FXML
    private Label merchandiseCode;
	@FXML
    private Label merchandiseName;
	@FXML
    private Label quantity;
	@FXML
    private Label unit;
	@FXML
    private Label desiredDeliveryDate;
	@FXML
    private Label notProcessQuantity;
	@FXML
    private Label action;
	    
	    public Parent getRoot() {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/oversea/view/ViewOneListToBeOrderedSingle.fxml"));
				loader.setController(this); 
				root = loader.load();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			return root;
		}   

	    public String getMerchandiseCode() {
	    	return merchandiseCode.getText();	    
	    }
	    
	    public String getMerchandiseName() {
	    	return merchandiseName.getText();	    
	    }
	    public String getNotProcessQuantity() {
	    	return notProcessQuantity.getText();	    
	    }
	    public String getDesiredDeliveryDate() {
	    	return desiredDeliveryDate.getText();	    
	    }
	    
	    public void disPlayData(String... strings)  {
	    	stt.setText(strings[0]);
	    	merchandiseCode.setText(strings[1]);
	    	merchandiseName.setText(strings[2]);
	    	quantity.setText(strings[3]);
	    	unit.setText(strings[4]);
	    	desiredDeliveryDate.setText(strings[5]);
	    	notProcessQuantity.setText(strings[6]);	
	    }
	    
	    public void addActionViewSiteSupply(EventHandler<MouseEvent> listener) {
	    	action.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
	    }
	    
	  
}
