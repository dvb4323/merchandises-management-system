package application.sales.page.component;

import java.io.IOException;

import application.APopUp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class SuccessAddToCartPopUp extends APopUp {
	
	public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/sales/view/Noti.fxml"));
			loader.setController(this); 
			root = loader.load();
		 	stage = new Stage() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		    return root;	
	 }
}
