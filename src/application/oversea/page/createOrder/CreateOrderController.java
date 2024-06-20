package application.oversea.page.createOrder;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import application.AController;
import application.app.page.component.AlertPopUpController;
import application.app.page.component.SuccessPopUpController;
import application.oversea.dbsubsystem.mysql.MySQLOrderDB;
import application.oversea.page.orderToSite.OrderToSiteController;
import application.oversea.page.orderToSite.OrderToSitePageForm;
import application.oversea.service.CreateOrderService;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateOrderController extends AController {

	private CreateOrderService service ;
	private CreateOrderPageForm form ;
	private List<CreateOrderSingleForm> listSinglePane = new ArrayList<CreateOrderSingleForm>();
	private AnchorPane prev ;
	private String listToBeOrderedID ;

	public CreateOrderController(CreateOrderPageForm form, CreateOrderService service ) {
		this.form    = form ;
		this.root    = form.getRoot();
		this.service = service ;
		
	}

	public void setPrevRoot(AnchorPane e) {
			prev = e ;
	  }  
	   
	public boolean fetchAndDisplayData(String listToBeOrderedID) {
		JSONObject res = service.getAllListToOrderInCart(listToBeOrderedID);
		JSONArray data = res.getJSONArray("listOrderItem") ;
		this.listToBeOrderedID = listToBeOrderedID; 
		form.setListItemToBeOrderedID(listToBeOrderedID);
        form.setNumberOfOrder(String.valueOf(data.length()));
		if(data.length()==0) return false;
		else {
			for(int i = 0; i < data.length() ;i++) {	
				JSONObject jsonObject = data.getJSONObject(i);	
				
				CreateOrderSingleForm singleForm = new CreateOrderSingleForm();
				form.getListItem().getChildren().add(singleForm.getRoot());
				singleForm.displayForm(jsonObject,service.getListItemInAnOrder(listToBeOrderedID, jsonObject.getString("siteCode")));
				listSinglePane.add(singleForm) ;
	        }	
			handleAllAction();
			form.addPaginationHandle(data.length(), 2);
		  }
		
		return true;
	}
	
	public void handleAllAction() {
		handleBackToPrevPage() ;
		handleOrder();
	}
	
	private void handleError() {
		AnchorPane i = (AnchorPane) root;
		i.getChildren().clear();
		AlertPopUpController a = new AlertPopUpController();
		a.getRoot();
		Stage s = (Stage) root.getScene().getWindow();
		a.openAsPopUp(s,3000,300,230);
	 }
	
	
	private void handleBackToPrevPage() {
		form.addActionBackToPrePage(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				prev.getChildren().remove(root);
			}
		});
	}
	
	private void handleOrder() {
		form.addActionConfirm(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				service.createOrder(listToBeOrderedID);
				SuccessPopUpController c = new SuccessPopUpController();
				c.getRoot();
				Stage parent = (Stage) root.getScene().getWindow() ;
				c.openAsPopUp(parent,3000,300,230);
				
				OrderToSiteController contr =new OrderToSiteController(new OrderToSitePageForm(), new MySQLOrderDB());
				contr.fetchAndDisplayData();
				AnchorPane i = (AnchorPane) root ;
				i.getChildren().clear();
				i.getChildren().add(contr.getRoot());	
			}	
		});
	}
	
}
