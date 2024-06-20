package application.oversea.page.cancelledOrder;



import java.io.IOException;

import application.oversea.page.AOverseaPageForm;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;


public class CancelledOrderPageForm extends AOverseaPageForm {

    @FXML
    private TextField search;

    public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/oversea/view/CancelledOrder.fxml"));
			System.out.println(getClass().getClassLoader().getResource("application/view/Sample.fxml"));
			loader.setController(this); 
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;	
	}
    
}
