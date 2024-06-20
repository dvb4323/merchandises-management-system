package application.app.page.user;

import application.AController;
import application.app.service.SigninService;
import application.oversea.page.allListToBeOrdered.AllListToBeOrderedController;
import application.oversea.page.allListToBeOrdered.AllListToBeOrderedPageForm;
import application.oversea.service.AllListToBeOrderedService;
import application.sales.page.viewListItem.ViewAllListItemController;
import application.sales.page.viewListItem.ViewAllListItemPageForm;
import application.sales.service.ViewAllListItemService;
import application.site.page.viewOrderList.ViewOrderListController;
import application.site.page.viewOrderList.ViewOrderListForm;
import application.site.service.ViewOrderListService;
import application.warehouse.page.allListOrder.AllListOrderController;
import application.warehouse.page.allListOrder.AllListOrderPageForm;
import application.warehouse.service.AllListOrderService;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SigninController extends AController{
	
	private SigninForm form ;
	private SigninService service ;
	
	public SigninController (SigninForm form, SigninService service) {
		this.form = form ;
		this.root = form.getRoot();
		this.service = service;
	}
	
	@Override
	public void handleAllAction() {
		handleSignin();
	}

	
	private void handleSignin() {
		form.addActionSigninButton(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					String name = form.getName() ;
					String password = form.getPassword();
					int checkLogin = service.checkLogin(name, password);
					AController controller = null ;
					if(checkLogin==1)         controller = new ViewAllListItemController(new ViewAllListItemPageForm(), new ViewAllListItemService());
					else if (checkLogin == 2) controller = new AllListToBeOrderedController(new AllListToBeOrderedPageForm(),new AllListToBeOrderedService());
					else if (checkLogin == 3) controller = new ViewOrderListController(new ViewOrderListForm(),new ViewOrderListService());
					else if (checkLogin == 4) controller = new AllListOrderController( new AllListOrderPageForm() , new AllListOrderService());
					
					controller.handleAllAction();
					AnchorPane i = (AnchorPane) root ;
					i.getChildren().clear();
					i.getChildren().add(controller.getRoot());
				}
			});
	}
}
