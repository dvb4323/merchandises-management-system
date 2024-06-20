package application.oversea.page.orderToSite;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class OrderToSiteSingleForm  {
	private Parent root ;

    @FXML
    private Label action;

    @FXML
    private Label listToBeOrderedID;

    @FXML
    private Label numberOfItem;

    @FXML
    private Label orderID;

    @FXML
    private Label siteCode;

    @FXML
    private Label status;

    
    public Parent getRoot() {
  		try {
  			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/oversea/view/OrderToSiteSingle.fxml"));
  			loader.setController(this); 
  			root = loader.load();
			root.setVisible(false);
			root.setManaged(false);
  		
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		return root;
  	}
    
    public void displayData(String... s) {
    	orderID.setText(s[0]);
    	listToBeOrderedID.setText(s[1]);
    	siteCode.setText(s[2]);
    	numberOfItem.setText(s[3]);
    	status.setText(s[4]);
    }

}




