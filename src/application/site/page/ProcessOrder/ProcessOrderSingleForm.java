package application.site.page.ProcessOrder;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ProcessOrderSingleForm {
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
    private Label mean;
    @FXML
    private Label orderStatus;

    private Parent root;
    
    @FXML
    private Label updateStatusButton;
	
	public Parent getRoot() {
    	try {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/site/view/ItemAcceptOrder.fxml"));
    	loader.setController(this);
    	
	    root = loader.load();
		} catch (IOException e) {

			e.printStackTrace();
		}
    	
    	return root ;
	}
	
	public void disPlayData(String... args) {
		stt.setText(args[0]);
		merchandiseCode.setText(args[1]);
		merchandiseName.setText(args[2]);
		quantity.setText(args[3]);
		unit.setText(args[4]);
		mean.setText(args[5]);
		orderStatus.setText(args[6]);
		
		if     (orderStatus.getText().equals("Chờ xác nhận")) {
			orderStatus.setStyle ("-fx-background-color : #E6F0DC ;" +"-fx-background-radius: 100;");
			updateStatusButton.setVisible(false);
		}
     	else if(orderStatus.getText().equals("Chờ vận chuyển")) {
     		orderStatus.setStyle("-fx-background-color : #ebbda5;" + "-fx-background-radius: 0;");
     	}
     	else if(orderStatus.getText().equals("Đang vận chuyển")) {
     		orderStatus.setStyle("-fx-background-color : #e7cba9;" + "-fx-background-radius: 0;");
     		updateStatusButton.setVisible(false);
     	}
     	else if(orderStatus.getText().equals("Đã vận chuyển")) {
     		orderStatus.setStyle("-fx-background-color : #fec7bc;" + "-fx-background-radius: 0;");
     		updateStatusButton.setVisible(false);
     	}
     	else if (orderStatus.getText().equals("Đã hủy")) {
     		orderStatus.setStyle("-fx-background-color : #FB8579;" + "-fx-background-radius: 0;");
     		updateStatusButton.setVisible(false);
     	}
		
		
	}
	
	 public void addActionUpdateStatus(EventHandler<MouseEvent> listener) {
		 updateStatusButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
	 }
	 
	 public String getMerchandiseCode() {
		 return merchandiseCode.getText();
	 }
}
