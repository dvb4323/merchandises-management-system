package application.warehouse.page.receiveOrder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ReceiveOrderSingleForm implements Initializable{
	    private Parent root ;

	    @FXML
	    private Label expectedDate;

	    @FXML
	    private Label merchandiseName;

	    @FXML
	    private Label quantity;

	    @FXML
	    private TextField receiveQuantity;

	    @FXML
	    private CheckBox saveOrderCheckBox;

	    @FXML
	    private Label status;

	    @FXML
	    private Label stt;

	    @FXML
	    private Label unit;
	    
    public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/warehouse/view/WarehouseDetailOrderSingle.fxml"));
			loader.setController(this); 
			root = loader.load();
			receiveQuantity.setVisible(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter());
		receiveQuantity.setTextFormatter(textFormatter);
		saveOrderCheckBox.setOnAction(event -> {
           boolean isChecked =  saveOrderCheckBox.isSelected();
           receiveQuantity.setVisible(isChecked);
       });
      
	}
    
	
    
    public void disPlayData(String... strings) {
		stt.setText(strings[0]);
		merchandiseName.setText(strings[1]);
		expectedDate.setText(strings[2]);
		quantity.setText(strings[3]);
		unit.setText(strings[4]);
		status.setText(strings[5]);
	}
    
//	 public void addActionSaveOrder(EventHandler<MouseEvent> listener) {
//	    	action.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
//	 }
    
    public boolean isChecked() {
        return saveOrderCheckBox.isSelected();
    }

    public String getMerchandiseCode() {
        return merchandiseName.getText();
    }

    public int getReceivedQuantity() throws NumberFormatException {
        return Integer.parseInt(receiveQuantity.getText());
    }

}
