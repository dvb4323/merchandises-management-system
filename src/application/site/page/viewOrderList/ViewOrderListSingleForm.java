package application.site.page.viewOrderList;


import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class ViewOrderListSingleForm {
	@FXML
    private Label stt;
	@FXML
    private Label orderId;
	@FXML
	private Label numberOfItem;
	@FXML
  	private Label orderListStatus;  
	@FXML
	private Label action;

	 
	private Parent root;
	
	public Parent getRoot() {
    	try {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/site/view/ViewOrderListSingleForm.fxml"));
    	loader.setController(this);
    	
    	
	    root = loader.load();
	    root.setVisible(false);
	    root.setManaged(false);
	    
		} catch (IOException e) {

			e.printStackTrace();
		}
    	
    	return root ;
	}
	
	public void disPlayData(String... args) {
		stt.setText(args[0]);
		orderId.setText(args[1]);
		numberOfItem.setText(args[2]);
		orderListStatus.setText(args[3]);

		if     (orderListStatus.getText().equals("Đang xử lí")) {
			orderListStatus.setStyle ("-fx-background-color : #F4DCD6 ;" +"-fx-background-radius: 0;");
		}
     	else if(orderListStatus.getText().equals("Chưa xử lí"))
     		orderListStatus.setStyle("-fx-background-color : #FCF0CF;" + "-fx-background-radius: 0;");
     	else if (orderListStatus.getText().equals("Đã huỷ")) {
     		orderListStatus.setStyle("-fx-background-color : #E17E76;" + "-fx-background-radius: 0;");
//     		action.setVisible(false);
     	}
     	else if (orderListStatus.getText().equals("Đã xử lí")) {
         		orderListStatus.setStyle("-fx-background-color :#84B4C8 ;" + "-fx-background-radius:0;");
 //    		action.setVisible(false);
//     		action.setManaged(false);
     	}	
	}
	
	public void addActionviewDetail(EventHandler<MouseEvent> listener) {
		action.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
	}
	
	public String getOrderId() {
		return orderId.getText();
	}

	public String getOrderStatus() {
		return orderListStatus.getText();
	}
	
}
