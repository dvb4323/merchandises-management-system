package application.warehouse.page.allListOrder;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import application.AController;
import application.warehouse.page.importedItems.ImportedItemsController;
import application.warehouse.page.importedItems.ImportedItemsPageForm;
import application.warehouse.page.receiveOrder.ReceiveOrderController;
import application.warehouse.page.receiveOrder.ReceiveOrderPageForm;
import application.warehouse.service.AllListOrderService;
import application.warehouse.service.ImportedItemsService;
import application.warehouse.service.ReceiveOrderService;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AllListOrderController extends AController{
	private AllListOrderService service;
	private List<AllListOrderSingleForm> listSinglePane = new ArrayList<AllListOrderSingleForm>();
	private AllListOrderPageForm form;

	
    public AllListOrderController (AllListOrderPageForm a, AllListOrderService service) {
    	this.form          = a ;
    	root          = form.getRoot();
    	this.service       = service;
    	fetchAndDisplayData();
    	 handleViewOneOrder();
    	 handleAllAction();
    }
    
  public void fetchAndDisplayData() {
//    	data = db.getAllOrder(null, null);
	  JSONObject inforOrderData =  service.getAllOrders(null, null);
	  JSONArray list = inforOrderData.getJSONArray("listOrder");
	  for(int i = 0; i < list.length() ;i++) {	
			JSONObject jsonObject = list.getJSONObject(i);
			AllListOrderSingleForm controller = new  AllListOrderSingleForm();
			form.getListItem().getChildren().add(controller.getRoot());
			listSinglePane.add(controller);
			
//			controller.disPlayData( String.valueOf(data.indexOf(a)+1),a.getOrderID(),a.getCreatedDate(),
//					a.getSiteCode()	, a.getStatus());   
			controller.disPlayData(String.valueOf(i+1),jsonObject.getString("orderID"), 
										jsonObject.getString("createdDate"),
										jsonObject.getString("siteCode"),
										jsonObject.getString("status"));			
	}
	  form.addPaginationHandle(listSinglePane.size(), 10);
  }
  
  public void handleAllAction() {
		handleGoToOrderListPage();
		handleGoToMerchandiseListPage();
	}
  
  private void handleGoToOrderListPage() {
		form.addActionGoToOrderList(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				AnchorPane a = (AnchorPane) root ;
				a.getChildren().clear();
				AllListOrderController controller = new AllListOrderController(new AllListOrderPageForm(), new AllListOrderService());
				controller.handleAllAction();
				
				a.getChildren().add(controller.getRoot());
			}
		});
	}
	
	private void handleGoToMerchandiseListPage() {
		form.addActionGoToMerchandiseList(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				AnchorPane a = (AnchorPane) root ;
				a.getChildren().clear();
				ImportedItemsController controller = new ImportedItemsController(new ImportedItemsPageForm(), new ImportedItemsService());
				controller.handleAllAction();
				
				a.getChildren().add(controller.getRoot());
			}
		});
	}
		
  private void handleViewOneOrder() {
		for(AllListOrderSingleForm a : listSinglePane) {
			a.addActionEventViewOrder(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					ReceiveOrderController contr = new ReceiveOrderController
							(new ReceiveOrderPageForm(),new ReceiveOrderService()) ;
				
					contr.fetchAndDisplayData(a.getOrderID());
					contr.handleAllAction();
					AnchorPane i = (AnchorPane) root ;
					i.getChildren().add(contr.getRoot());
					contr.setPrevRoot(i);
				}
			}) ;
		}
	}
}
