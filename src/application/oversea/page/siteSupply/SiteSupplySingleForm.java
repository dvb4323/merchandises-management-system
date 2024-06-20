package application.oversea.page.siteSupply;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class SiteSupplySingleForm implements Initializable {
	private Parent root ;
	@FXML
	private Label stt;
	@FXML
    private Label siteID;
	@FXML
    private Label siteName;
	@FXML
    private Label stock;
	@FXML
	private Label unit;
	@FXML
    private Label shipArriveDate;
	@FXML
    private Label airArriveDate;
	@FXML
    private CheckBox orderCheckBox;
	@FXML
    private TextField quantity;
    @FXML
    private ComboBox<String> mean;

	public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/oversea/view/SiteSupplySinglePane.fxml"));
			loader.setController(this); 
			root = loader.load();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter());
		quantity.setTextFormatter(textFormatter);
		orderCheckBox.setOnAction(event -> {
            boolean isChecked =  orderCheckBox.isSelected();
            quantity.setVisible(isChecked);
            mean.setVisible(isChecked);
        });
        
        ObservableList<String> choices = FXCollections.observableArrayList("Tàu","Hàng Không");
        mean.setItems(choices);	
	}

    public CheckBox getOrderCheckBox() {
		return orderCheckBox;
	}
	
	public TextField getQuantityTextField( ) {
		return quantity ;
	}
	
	public ComboBox<String> getComboBox (){
		return mean;
	}
	
	public String getUnit() {
		return unit.getText();
	}
	
	
	public String getQuantity() {
		return quantity.getText();
	}
	
	public String getMean() {
		return mean.getValue();
	}
	
	public String getSiteCode() {
		return siteID.getText();
	}
	
	public void disPlayData(String... strings) {
		siteID.setText(strings[0]);
		siteName.setText(strings[1]);
		stock.setText(strings[2]);
		unit.setText(strings[3]);
		shipArriveDate.setText(strings[4]);
		airArriveDate.setText(strings[5]);		
		stt.setText(strings[6]);
	}
	
}
