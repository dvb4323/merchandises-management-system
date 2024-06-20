package application.app.page.user;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SigninForm {
	private Parent root ;
	
	@FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @FXML
    private Label button;

	
	public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/view/Signin.fxml"));
			loader.setController(this); 
			root = loader.load();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;	
	}
	
	public String getPassword() {
		return password.getText();
	}
	
	public String getName() {
		return name.getText() ;
	}
	
	public void addActionSigninButton(EventHandler<MouseEvent> listener) {
    	button.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
}
