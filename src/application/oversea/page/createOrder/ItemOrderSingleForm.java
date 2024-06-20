package application.oversea.page.createOrder;

import java.io.IOException;
import application.ASingleForm;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;


public class ItemOrderSingleForm extends ASingleForm {
	private Parent root ;
	@FXML
    private Label stt;
	@FXML
    private Label itemId;
    @FXML
    private Label itemName;
    @FXML
    private Label quantity;
    @FXML
    private Label unit;
    @FXML
    private Label transport;
    
    public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/oversea/view/ItemOrderToSiteSinglePane.fxml"));
			loader.setController(this); 
			root = loader.load();
			root.setVisible(false);
			root.setManaged(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
   
	@Override
	public void disPlayData(String... strings) {
		stt.setText(strings[0]);
		itemId.setText(strings[1]);
		itemName.setText(strings[2]);
		quantity.setText(strings[3]);
		unit.setText(strings[4]);
		transport.setText(strings[5]);	
	}   

}
