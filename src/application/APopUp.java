package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public abstract class APopUp {
	 protected Parent root ;
	 protected Stage stage ;
	 
	 public void openAsPopUp(Stage parentStage , int width, int height) {	
		 stage.initModality(Modality.APPLICATION_MODAL);
		 stage.initStyle(StageStyle.TRANSPARENT);  
		 Scene popupScene = new Scene(root, 1010, 600);
		 stage.setScene(popupScene);
	     double parentX = parentStage.getX();
	     double parentY = parentStage.getY();
	     double parentWidth = parentStage.getWidth();
	     double parentHeight = parentStage.getHeight();
	     double popupX = parentX + (parentWidth - width) / 2;
	     double popupY = parentY + (parentHeight - height) / 2;
	     stage.setX(popupX);
	     stage.setY(popupY);
	     stage.setWidth(width);
	     stage.setHeight(height);
	     parentStage.setOpacity(0.7);
	     stage.initOwner(parentStage);
	     stage.showAndWait();
	     parentStage.setOpacity(1.0);   
	}
	 
	 public void openAsPopUp(Stage parentStage,int durationInMillis, int width, int height ) {	
		 stage.initOwner(parentStage);
	     stage.initModality(Modality.WINDOW_MODAL);
	     stage.initStyle(StageStyle.UTILITY);
		 Scene popupScene = new Scene(root, width, height);
		 stage.setScene(popupScene);
		
	     stage.show();
	     root.styleProperty().bind(parentStage.opacityProperty().multiply(0.5).asString("-fx-background-color: rgba(255, 255, 255, %f)"));
	     parentStage.setOpacity(0.5);
	     Timeline timeline = new Timeline(new KeyFrame(
	             Duration.millis(durationInMillis),
	             event -> stage.close()
	         ));
	         timeline.setCycleCount(1);
	         timeline.play();  
	         parentStage.setOpacity(1);
	}
	 
	 
}
