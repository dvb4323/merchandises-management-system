package application.site.page.ProcessOrder;


import java.io.IOException;

import application.APopUp;
import application.site.dbsubsystem.IOrderListDB;
import application.site.dbsubsystem.mysql.MySQLOrderList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConfirmOrderPopUpForm extends APopUp {

	@FXML
    private Label acceptButton;
    @FXML
    private VBox cancelButton;
    @FXML
    private Label rejectButton;
   
	public Parent getRoot() {
    	try {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/site/view/PopupProcess.fxml"));
    	loader.setController(this);
	    root = loader.load();
	    stage = new Stage();
		} catch (IOException e) {
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

}
