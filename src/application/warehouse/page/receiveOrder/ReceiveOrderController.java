package application.warehouse.page.receiveOrder;

import java.util.ArrayList;
import java.util.List;
import com.mysql.cj.xdevapi.JsonArray;

import application.app.page.component.SuccessPopUpController;
import application.warehouse.dbsubsystem.IOrderDB;
import application.warehouse.dbsubsystem.myql.MySQLImportedMerchandiseDB;
import application.warehouse.model.MerchandiseOrderModel;
import application.warehouse.model.WarehouseOrderModel;
import application.warehouse.page.allListOrder.AllListOrderController;
import application.warehouse.page.allListOrder.AllListOrderPageForm;
import application.warehouse.page.importedItems.ImportedItemsController;
import application.warehouse.page.importedItems.ImportedItemsPageForm;
import application.warehouse.service.AllListOrderService;
import application.warehouse.service.ImportedItemsService;
import application.warehouse.service.ReceiveOrderService;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
public class ReceiveOrderController {
	
	private Parent root ;
	private ReceiveOrderService service;
	private ImportedItemsService importedItemsService;
	private ReceiveOrderPageForm form;
	private List<ReceiveOrderSingleForm> listSinglePane = new ArrayList<ReceiveOrderSingleForm>();
	private AnchorPane prev ;


	
    public ReceiveOrderController (ReceiveOrderPageForm a, ReceiveOrderService service ) {
    	this.form          = a ;
    	this.service	   = service;
    	root               = form.getRoot();
    	this.importedItemsService = new ImportedItemsService();
    	handleSaveOrder();
    	handleAllAction();
    }
    
    public Parent getRoot() {
    	return root;
    }
    
    public void setPrevRoot(AnchorPane e) {
		prev = e ;
	}
	
   public void fetchAndDisplayData(String orderID) {
    	
//		data                    = db.getAllItemInOrder(order.getOrderID());
	   JSONObject inforOrderData =  service.getOrderDetail(orderID);
	   
	   form.displayData(inforOrderData.getString("orderID"),
               inforOrderData.getString("siteCode"),
               String.valueOf(inforOrderData.getInt("numberOfItem")),
               inforOrderData.getString("status"));
//	
//	    
//		for (MerchandiseOrderModel a : data) {
	   JSONObject data =  service.getAllItemInOrder(orderID);
  	    JSONArray list = data.getJSONArray("listItem");
  	    for(int i = 0; i < list.length() ;i++) {	
  			JSONObject jsonObject = list.getJSONObject(i);
     
			ReceiveOrderSingleForm controller = new ReceiveOrderSingleForm();
			form.getListItem().getChildren().add(controller.getRoot());
			listSinglePane.add(controller);
			
//			controller.disPlayData(String.valueOf(data.indexOf(a)+1),a.getMerchandiseCode(),a.getExpectedDate(),String.valueOf(a.getQuantity()),
//					a.getUnit(),a.getStatus());   
	
			controller.disPlayData(String.valueOf(i+1),jsonObject.getString("merchandiseCode"), 
					jsonObject.getString("expectedDate"),
					String.valueOf(jsonObject.getInt("quantity")),
					jsonObject.getString("unit"),
					jsonObject.getString("status"));		
		}
  	    
		  form.addPaginationHandle(list.length(), 10);
				
	}
   
   
	public void handleAllAction() {
		handleBackToPrevPage() ;
		handleGoToOrderListPage();
		handleGoToMerchandiseListPage();
	}
	
	private void handleBackToPrevPage() {
		form.addActionBackToPrePage(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				prev.getChildren().remove(root);
			}
		});
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
	
	private void handleSaveOrder() {
		form.addActionSaveOrder(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				boolean allUpdatesSuccessful = true;
				
				// Iterate over each ReceiveOrderSingleForm to update checked items
                for (ReceiveOrderSingleForm singleForm : listSinglePane) {
                    if (singleForm.isChecked()) {
                        String merchandiseCode = singleForm.getMerchandiseCode();
                        int receivedNumberOfItem;
                        
                        try {
                            receivedNumberOfItem = singleForm.getReceivedQuantity();
                        } catch (NumberFormatException e) {
                            System.err.println("Error: Invalid number format for received quantity for merchandise code " + merchandiseCode);
                            allUpdatesSuccessful = false;
                            break;
                        }

                        boolean result = importedItemsService.updateQuantity(merchandiseCode, receivedNumberOfItem);
                        if (!result) {
                            allUpdatesSuccessful = false;
                            break;
                        }
                    }
                }
				
                //Show PopUp
//				AnchorPane a = (AnchorPane) root ;
//				a.getChildren().clear();
				
				SuccessPopUpController c = new SuccessPopUpController();
//				c.getRoot();
				Stage parent = (Stage) root.getScene().getWindow();
				
				if (allUpdatesSuccessful) {
					AnchorPane a = (AnchorPane) root;
	                a.getChildren().clear();
	                
	                c.getRoot();
	                c.openAsPopUp(parent, 1500, 300, 230);
	                c.setNoTiText("Lưu hàng thành công!");

	                ImportedItemsController controller = new ImportedItemsController(new ImportedItemsPageForm(), new ImportedItemsService());
	                controller.handleAllAction();
	                a.getChildren().add(controller.getRoot());
	            } else {
	            	c.getRoot();
	                c.openAsPopUp(parent, 1500, 300, 230);
	                c.setNoTiText("Lưu hàng thất bại!");
	                // Keep the current page as is to allow the user to correct any issues
	            }
			}
		});
	}
	
}
