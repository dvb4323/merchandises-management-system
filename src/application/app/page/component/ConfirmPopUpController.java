package application.app.page.component;

import java.io.IOException;

import application.APopUp;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ConfirmPopUpController extends APopUp{
	@FXML
    private Label acceptButton;

    @FXML
    private VBox cancelButton;

    @FXML
    private Label rejectButton;
    @FXML
    private Label notiText;
  
	public Parent getRoot() {
    	try {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/site/view/PopupProcess.fxml"));
    	loader.setController(this);
	    root = loader.load();
	    stage = new Stage();
	    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return root ;	
    }

	public void addActionAcceptOrder(EventHandler<Event> eventHandler) {
		acceptButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	
	public void addActionRejectOrder(EventHandler<Event> eventHandler) {
		rejectButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
	}
	
	public void addActionCancelPopUp() {
		cancelButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				stage.close();
				
			}
		} ) ;
	}
	public void closePopUp() {
		stage.close();
	}
	public void setNoTiText(String s) {
		notiText.setText(s);
	}
}
