package application.app.page.component;

import java.io.IOException;

import application.APopUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SuccessPopUpController extends APopUp{
	@FXML
    private Text notiText;
	public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/oversea/view/SuccessPopUp.fxml"));
			loader.setController(this); 
			root = loader.load();
		 	stage = new Stage() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		    return root;	
	 }
	
	public void setNoTiText(String s) {
		notiText.setText(s);
	}
	
}
