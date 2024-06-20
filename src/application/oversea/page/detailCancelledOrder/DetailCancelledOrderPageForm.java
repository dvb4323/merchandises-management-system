package application.oversea.page.detailCancelledOrder;

import java.io.IOException;

import application.oversea.page.AOverseaPageForm;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class DetailCancelledOrderPageForm extends AOverseaPageForm {
	   @FXML
	    private Label backToPrePageButton;
//
//	   @FXML
//	    private Label reOderButton;

	    @FXML
	    private Label createdDate;

	    @FXML
	    private Label listToBeOrderedID;

	    @FXML
	    private Label note1;

	    @FXML
	    private Label numberOfItem;

	    @FXML
	    private Label orderID;

	    @FXML
	    private Label siteName;;
	    
	    @FXML
	    private Label addToListButton;;
	
	 public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/oversea/view/DetailCancelledOrder.fxml"));
			System.out.println(getClass().getClassLoader().getResource("application/view/Sample.fxml"));
			loader.setController(this); 
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;	
	 }
	
	public void displayData(String... s) {
		orderID.setText(s[0]);
		listToBeOrderedID.setText(s[1]);
		numberOfItem.setText(s[2]);
		siteName.setText(s[3]);
	    note1.setText(s[5]);
		createdDate.setText(s[4]);
	}
	public void addActionBackToPrePage(EventHandler<MouseEvent> listener) {
    	backToPrePageButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
    
//    public void addActionOrder(EventHandler<MouseEvent> listener) {
//    	reOderButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
//    }
    public void addActionAddToList(EventHandler<MouseEvent> listener) {
		addToListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
	}
    
    
}
