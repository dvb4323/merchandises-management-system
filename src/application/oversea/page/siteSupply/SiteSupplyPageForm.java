package application.oversea.page.siteSupply;

import java.io.IOException;

import application.oversea.page.AOverseaPageForm;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class SiteSupplyPageForm extends AOverseaPageForm {
    
    @FXML
    private Label cancelButton;

    @FXML
    private Label desiredDeliveryDate;
    @FXML
    private Label merchandiseCode;

    @FXML
    private Label merchandiseName;

    @FXML
    private Label numberOfItem;

    @FXML
    private Label saveButton;

    
    public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/oversea/view/SiteSupply.fxml"));
			loader.setController(this); 
			root = loader.load();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
    
    public String getMerchandiseCode() {
		return merchandiseCode.getText();
	}

//	public void setMerchandiseCode(String s) {
//		this.merchandiseCode.setText(s);
//	}
//	
	public void disPlayData(String ...s) {
		merchandiseCode.setText(s[0]);
		merchandiseName.setText(s[1]);
		numberOfItem.setText(s[2]+ " sản phẩm");
		desiredDeliveryDate.setText(s[3]);
	}

	public void addActionCancelPopUp(EventHandler<MouseEvent> listener) {
    	cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
	
	public void addActionSaveAndCancelPopUp(EventHandler<MouseEvent> listener) {
    	saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
	
	
	
}
