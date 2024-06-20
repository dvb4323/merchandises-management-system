package application.warehouse.page.importedItems;


import java.util.ArrayList;
import java.util.List;

import application.warehouse.page.allListOrder.AllListOrderController;
import application.warehouse.page.allListOrder.AllListOrderPageForm;

import org.json.JSONArray;
import org.json.JSONObject;

import application.warehouse.service.AllListOrderService;
import application.warehouse.service.ImportedItemsService;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class ImportedItemsController {
	
	private List<ImportedItemsSingleForm> listSinglePane = new ArrayList<ImportedItemsSingleForm>();
	private ImportedItemsPageForm form;
	private ImportedItemsService service;
	private Parent root ;

	
    public ImportedItemsController (ImportedItemsPageForm a, ImportedItemsService service ) {
    	this.form          = a ;
    	this.root          = form.getRoot();
    	this.service       = service;	
    	handleAllAction();
    	fetchAndDisplayData();
    	}
    
    public Parent getRoot() {
    	return root;
    }
    

  public void handleAllAction() {
		handleSearch();
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
  public void fetchAndDisplayData() {
//    	data = db.getAllItems(form.getSearchByListToBeOrderedID());
//		for (ImportedMerchandiseModel a :  data) {
	  JSONObject inforOrderData =  service.getAllItems(form.getSearchByListToBeOrderedID());
	  JSONArray list = inforOrderData.getJSONArray("listItem");
	  for(int i = 0; i < list.length() ;i++) {	
			JSONObject jsonObject = list.getJSONObject(i);
	  
			ImportedItemsSingleForm controller = new  ImportedItemsSingleForm();
			form.getListItem().getChildren().add(controller.getRoot());
			listSinglePane.add(controller);
			
//			controller.disPlayData( String.valueOf(data.indexOf(a)+1),a.getMerchandiseCode(),a.getMerchandiseName(),
//					String.valueOf(a.getQuantity())	, a.getUnit());  
			
			
			controller.disPlayData(String.valueOf(i+1),jsonObject.getString("merchandiseCode"), 
					jsonObject.getString("merchandiseName"),
					String.valueOf(jsonObject.getInt("quantity")),
					jsonObject.getString("unit"));	
		   
				
	}
		 form.addPaginationHandle(listSinglePane.size(), 10);
  }
  
  private void handleSearch() {
		form.addActionSearchButton(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					form.getListItem().getChildren().clear();
					listSinglePane.clear();
					form.resetPagination();
					fetchAndDisplayData();
				}
			});
	}
		
 
}
