package application.oversea.page.createOrder;


import java.io.IOException;

import application.oversea.page.AOverseaPageForm;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class CreateOrderPageForm extends AOverseaPageForm {
	 
	  @FXML
	  private Label backToPrePageButton;
	  @FXML
	  private Label confirmToOrderButton;
	  @FXML
	  private Label listItemToBeOrderedID;
	  @FXML
	  private Label numberOfOrder;

	  public Parent getRoot() {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/oversea/view/ViewProcessOrder.fxml"));
				loader.setController(this); 
				root = loader.load();
			    listItem.setFillWidth(true) ;
			 
			} catch (IOException e) {
				e.printStackTrace();
			}
			return root;
	 }
	  
	public void setListItemToBeOrderedID(String s) {
		listItemToBeOrderedID.setText(s);
	}
	public void setNumberOfOrder(String s) {
		numberOfOrder.setText(s);
	}
	
	public void addActionBackToPrePage(EventHandler<MouseEvent> listener) {
	    	backToPrePageButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
	 }
	    
	public void addActionConfirm(EventHandler<MouseEvent> listener) {
		confirmToOrderButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
	 }	
}
