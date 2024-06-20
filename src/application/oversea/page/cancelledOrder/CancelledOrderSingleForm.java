package application.oversea.page.cancelledOrder;

import java.io.IOException;

import application.ASingleForm;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CancelledOrderSingleForm extends ASingleForm {
	private Parent root ;
	@FXML
    private Label stt;
	@FXML
    private Label orderID;
	@FXML
    private Label numberOfItem;
	@FXML
    private Label createDate;
	@FXML
    private Label action;

    @FXML
    private Label siteCode;
    
    public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/oversea/view/CancelledOrderSingle.fxml"));
			loader.setController(this); 
			root = loader.load();
			root.setVisible(false);
			root.setManaged(false);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
    
    public String getOrderID() {
    	return orderID.getText();
    }
    
    public void disPlayData(String... strings) {
		stt.setText(strings[0]);
    	orderID.setText(strings[1]);
    	numberOfItem.setText(strings[2]);
    	createDate.setText(strings[3]);
    	
//     	if     (status.getText().equals("Đang xử lý")) status.setStyle ("-fx-background-color : #E6F0DC ;" +"-fx-background-radius: 100;");
//     	else if(status.getText().equals("chưa xử lý"))  status.setStyle("-fx-background-color : #ebbda5;" + "-fx-background-radius: 100;");
//		
	}
    
    public void addActionViewDetail(EventHandler<MouseEvent> listener) {
    	action.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }

}
