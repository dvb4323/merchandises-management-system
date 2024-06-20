package application.site.page.ProcessOrder;

import java.io.IOException;

import application.APageForm;
import application.site.page.ASitePageForm;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ProcessOrderForm extends ASitePageForm{
	
	private Parent root;
	@FXML
	private Label orderId;
	@FXML
	private Label orderListStatus;
	@FXML
	private VBox listItem;
    @FXML
    private Label pageReturn;
    @FXML
    private Label processButton;
	
	public Parent getRoot() {
    	try {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/site/view/AcceptOrder.fxml"));
    	loader.setController(this);
	    root = loader.load();
	    processButton.setVisible(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return root ;
    	
    }
	public void setOrderID(String s) {
		orderId.setText(s);
	}
	
	public void setOrderStatus(String s) {
		orderListStatus.setText(s);
	}

	public VBox getListItem() {
		return listItem;
	}
	public void setProcessButton(String orderStatus) {
		if(orderStatus.equals("Chưa xử lý")) processButton.setVisible(true);
		else  processButton.setVisible(false);
	}
	
	public void addActionProcessOrder(EventHandler<MouseEvent> listener) {
		processButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
	}
	
	public void addActionbackToPrevPage(EventHandler<MouseEvent> listener) {
		pageReturn.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
	}
}
