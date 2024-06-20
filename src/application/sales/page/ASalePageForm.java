package application.sales.page;

import application.APageForm;
import application.app.page.user.SigninController;
import application.app.page.user.SigninForm;
import application.app.service.SigninService;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ASalePageForm extends APageForm{
	
	@FXML
	 void logout(MouseEvent event) {
		AnchorPane r = (AnchorPane) root;
		SigninController controller = new SigninController(new SigninForm(), new SigninService());
     	controller.handleAllAction();
		r.getChildren().clear();
		r.getChildren().add(controller.getRoot());
	 }
}
