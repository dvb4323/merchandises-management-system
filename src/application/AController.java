package application;

import javafx.scene.Parent;


public abstract class AController {
	protected Parent root ;
	
	public Parent getRoot() {
		return root;
	}
	
	public abstract void handleAllAction();
	
}
