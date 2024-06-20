package application;

import application.app.page.user.SigninController;
import application.app.page.user.SigninForm;
import application.app.service.SigninService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
    	AnchorPane root = new AnchorPane();	

     	SigninController controller = new SigninController(new SigninForm(), new SigninService());
     	controller.handleAllAction();
    	 
   	    root.getChildren().add(controller.getRoot());
    	
		Scene scene = new Scene(root,1150,700); 
	 	primaryStage.setScene(scene);
	 	primaryStage.show();
    } 

    public static void main(String[] args) {
        launch(args);
    }   
}