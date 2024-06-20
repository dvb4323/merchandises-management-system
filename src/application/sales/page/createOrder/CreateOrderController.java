package application.sales.page.createOrder;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import application.AController;
import application.app.page.component.SuccessPopUpController;
import application.oversea.service.AllListToBeOrderedService;
import application.sales.dbsubsystem.IDetailMerchandiseDB;
import application.sales.dbsubsystem.IOrderDB;
import application.sales.model.DetailMerchandiseModel;
import application.sales.page.inputDetailOrder.InputDetailOrderPageController;
import application.sales.page.inputDetailOrder.InputDetailOrderPageForm;
import application.sales.page.viewListItem.ViewAllListItemController;
import application.sales.page.viewListItem.ViewAllListItemPageForm;
import application.sales.page.viewOrder.ViewOrderController;
import application.sales.page.viewOrder.ViewOrderPageForm;
import application.sales.service.DetailOrderItemService;
import application.sales.service.ViewAllListItemService;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateOrderController extends  AController{
    
	private CreateOrderPageForm form ;
	private CreateOrderController controller ;
	private DetailOrderItemService service ;
	private AnchorPane prev ;
	List<CreateOrderSingleForm> singleList = new ArrayList<CreateOrderSingleForm>();
	private String idItem;

	
	public CreateOrderController(CreateOrderPageForm form, DetailOrderItemService service) {
		this.form = form;
		this.service = service;
		root = form.getRoot();
		
	}
	
	public void setPrevRoot(AnchorPane e) {
		prev = e ;
	}
	
	public void handleAllAction() {
		fetchAndDisplayData();
		handleBackToPrevPage();
		handleActionOnItem();
		handleActionConfirmOrder();
	}
	
	private void fetchAndDisplayData() {
    	
    	form.getListItem().getChildren().clear();
    	JSONObject inforOrderData =  service.getdetailListItem();
		JSONArray list = inforOrderData.getJSONArray("detailItem");
		for(int i = 0; i < list.length() ;i++) {	
			JSONObject jsonObject = list.getJSONObject(i);
    		CreateOrderSingleForm singleForm = new CreateOrderSingleForm();
    		form.getListItem().getChildren().add(singleForm.getRoot());
    		singleForm.disPlayData(String.valueOf(i+1),
    				jsonObject.getString("merchandiseCode"),
   					jsonObject.getString("merchandiseName"),
   					String.valueOf(jsonObject.getInt("quantityOrdered")) ,
   					jsonObject.getString("unit"),
   					jsonObject.getString("deliveryDateDesired")
    			);
    		singleList.add(singleForm);
    		
    	}
    	System.out.println(list.length());
     	form.addPaginationHandle(list.length(), 11);
     	
	}
	
	
	private void handleActionOnItem() {
		for(CreateOrderSingleForm i : singleList) {
			i.addActionDeleteItem( new EventHandler<MouseEvent>() {
        		@Override
        		public void handle(MouseEvent arg0) {
        			int index = singleList.indexOf(i);
        			System.out.println(index);
        			form.getListItem().getChildren().remove(index);
        			singleList.remove(index);
        			for(int i = index ; i < singleList.size(); i++) {
        				singleList.get(i).setStt(i+1);
        			}
        			service.removeFromCart(i.getMerchandiseCode());

        		}	
        	}) ;
			
			i.addActionEditItem(new EventHandler<MouseEvent>() {
        		@Override
        		public void handle(MouseEvent arg0) {
        			InputDetailOrderPageController controller = new InputDetailOrderPageController(new InputDetailOrderPageForm(), i.getId() );
        			String dateString = i.getDate();  
        			String number = i.getQuantity();
//        			controller.handleEditInput(dateString, number);
        			
        			service.editItem(i.getId(), dateString, Integer.parseInt(number));
        			i.updateItemInfo(number, dateString);
        			VBox a = (VBox) root;
        			Stage parentStage = (Stage) root.getScene().getWindow();
        			controller.handleAllAction();
        			controller.openAsPopUp(parentStage,600,400);
        			
        		}	
       	});

	  }
	}
	
	private void handleActionConfirmOrder() {
		form.addActionConfirmOrder(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent arg0) {
    			SuccessPopUpController c = new SuccessPopUpController();
				c.getRoot();
				Stage parent = (Stage) root.getScene().getWindow() ;
				c.openAsPopUp(parent,3000,300,230);
				
				JSONObject inforOrderData =  service.getDetailListItem();
				JSONArray list = inforOrderData.getJSONArray("detailItem");
				int numberOfItem = inforOrderData.getInt("numberOfItem");
				service.createOrder(numberOfItem, "Chưa xử lý", list.length());
				for(int i = 0; i < list.length() ;i++) {	
					JSONObject jsonObject = list.getJSONObject(i);
					service.createItemOrder(jsonObject.getString("merchandiseCode"),
		   					jsonObject.getInt("quantityOrdered") ,
		   					jsonObject.getString("deliveryDateDesired"),
		   					numberOfItem) ;
				}
				VBox i = (VBox) root ;
				i.getChildren().clear();
				ViewOrderController controller = new ViewOrderController(new ViewOrderPageForm(), new AllListToBeOrderedService());
				i.getChildren().add(controller.getRoot());
				controller.handleAllAction();
    		}		
    	});
	}
	
	private void handleBackToPrevPage() {
		form.addActionBackToPrevPage(new EventHandler<MouseEvent>() {
    		@Override
    		public void handle(MouseEvent arg0) {
    			prev.getChildren().clear();
    			ViewAllListItemController controller = new ViewAllListItemController(new ViewAllListItemPageForm(), new ViewAllListItemService());
    			prev.getChildren().add(controller.getRoot());
    			controller.handleAllAction();
    		}		
    	});
	}
}
