package application.sales.page.createOrder;
import java.io.IOException;

import application.APageForm;
import application.sales.page.ASalePageForm;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader ;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class CreateOrderPageForm extends ASalePageForm {
	    @FXML
	    private VBox backToPrevButton;

	    @FXML
	    private VBox confirmButton;

	    @FXML
	    private Label date;

	    @FXML
	    private Label delete;

	    @FXML
	    private Label edit;

	    @FXML
	    private Label idItem;

	    @FXML
	    private VBox listItem;

	    @FXML
	    private Label nameItem;

	    @FXML
	    private Button okButton;

	    @FXML
	    private AnchorPane popup;

	    @FXML
	    private Label quantity;

	    @FXML
	    private VBox root;

	    @FXML
	    private Label stt;

	    @FXML
	    private Label unit;

	    @FXML
	    private VBox vpage;
	  


	    public Parent getRoot() {
	    	try {
	    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/sales/view/DetailOrderItem.fxml"));
	    	loader.setController(this);
	    	
		    root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	return root ;
	    }
	    public VBox getListItem() {
			return listItem;
		}
	    public AnchorPane getPopup() {
			return popup;
		}
	    
	    public void addActionBackToPrevPage(EventHandler<MouseEvent> listener) {
	    	backToPrevButton.addEventFilter(MouseEvent.MOUSE_CLICKED, listener);
	    }
	    public void addActionConfirmOrder(EventHandler<MouseEvent> eventHanler) {
	    	confirmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHanler);
	    }

	    
}
