package application.oversea.page;

import application.APageForm;
import application.app.page.user.SigninController;
import application.app.page.user.SigninForm;
import application.app.service.SigninService;
import application.oversea.dbsubsystem.mysql.MySQLOrderDB;
import application.oversea.page.allListToBeOrdered.AllListToBeOrderedController;
import application.oversea.page.allListToBeOrdered.AllListToBeOrderedPageForm;
import application.oversea.page.cancelledOrder.CancelledOrderController;
import application.oversea.page.cancelledOrder.CancelledOrderPageForm;
import application.oversea.page.orderToSite.OrderToSiteController;
import application.oversea.page.orderToSite.OrderToSitePageForm;
import application.oversea.service.AllListToBeOrderedService;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public abstract class AOverseaPageForm extends APageForm{
	
    
    @FXML
	void seeListToBeOrdered(MouseEvent event) {
    	AnchorPane r = (AnchorPane) root;
		AllListToBeOrderedController  controller = new AllListToBeOrderedController(
														new AllListToBeOrderedPageForm(), new AllListToBeOrderedService());
		controller.handleAllAction();	
		r.getChildren().clear();
		r.getChildren().add(controller.getRoot());	     
	}
	    
	@FXML
	void seeCancelledOrder(MouseEvent event) {
		AnchorPane r = (AnchorPane) root;
		CancelledOrderController  controller = new CancelledOrderController (
														new CancelledOrderPageForm(), new MySQLOrderDB());
		controller.handleAllAction();	
		r.getChildren().clear();
		r.getChildren().add(controller.getRoot());	 
	}
		
	@FXML
    void seeOrder(MouseEvent event) {
    	AnchorPane r = (AnchorPane) root;
		OrderToSiteController  controller = new OrderToSiteController (
														new OrderToSitePageForm(), new MySQLOrderDB());
		controller.handleAllAction();	
		r.getChildren().clear();
		r.getChildren().add(controller.getRoot()); 	
    }   
	
	@FXML
	 void logout(MouseEvent event) {
		AnchorPane r = (AnchorPane) root;
		SigninController controller = new SigninController(new SigninForm(), new SigninService());
     	controller.handleAllAction();
		r.getChildren().clear();
		r.getChildren().add(controller.getRoot());
	 }
}
