package application.oversea.page.orderToSite;

import java.io.IOException;

import application.oversea.page.AOverseaPageForm;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;

public class OrderToSitePageForm extends AOverseaPageForm {
    @FXML
    private ComboBox<String> marketplace;
    
    public Parent getRoot() {
  		try {
  			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/oversea/view/OrderToSite.fxml"));
  			loader.setController(this); 
  			root = loader.load();
  		
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		return root;
  	}

}




