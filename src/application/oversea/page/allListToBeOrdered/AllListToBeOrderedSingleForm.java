package application.oversea.page.allListToBeOrdered;

import java.io.IOException;

import application.ASingleForm;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class AllListToBeOrderedSingleForm extends ASingleForm {
	
	private Parent root ;
	@FXML
    private Label orderId;
	@FXML
    private Label date;
	@FXML
    private Label numberOfItem;
    @FXML
    private Label status;
    @FXML
    private Label action;
    
    public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/oversea/view/ViewAllListToBeOrderedSingle.fxml"));
			loader.setController(this); 
			root = loader.load();
			root.setVisible(false);
			root.setManaged(false);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
    
    public String getOrderId() {
		 return orderId.getText();
	 }

    @Override
	public void disPlayData(String... strings) {
		orderId.setText(strings[0]);
    	date.setText(strings[1]);
    	numberOfItem.setText(strings[2]);
    	status.setText(strings[3]);
    	
     	if     (status.getText().equals("Đang xử lý")) status.setStyle ("-fx-background-color : #E6F0DC ;" );
     	else if(status.getText().equals("Chưa xử lý"))  status.setStyle("-fx-background-color : #ebbda5;" );	
		
	}
  	
	 public void addActionEventViewSaleOrder(EventHandler<MouseEvent> listener) {
	    	action.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
	 }

}
