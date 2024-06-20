package application.site.page.ProcessOrder;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import application.AController;
import application.app.page.component.ConfirmPopUpController;
import application.site.page.viewOrderList.ViewOrderListController;
import application.site.page.viewOrderList.ViewOrderListForm;
import application.site.service.ProcessOrderService;
import application.site.service.ViewOrderListService;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProcessOrderController extends AController{

	private ProcessOrderForm form;
	private List<ProcessOrderSingleForm> listSingleForm = new ArrayList<ProcessOrderSingleForm>();
	private ProcessOrderService service;
	private int orderID ;
	private AnchorPane prev ;
	
	public ProcessOrderController(ProcessOrderForm form, ProcessOrderService service) {
		this.form = form;
		root = form.getRoot();
		this.service = service ;
	}

	public void setPrev(AnchorPane e) {
		this.prev = e;
	}
	

	public void fetchAndDisplayData(int orderId) {
		this.orderID = orderId;
		JSONObject inforOrderData =  service.getInforOrder(orderId);
		JSONObject listItemData   =  service.getAllItemInOrder(orderId);	
		form.setOrderID(String.valueOf(orderId));
		form.setOrderStatus(inforOrderData.getString("orderListStatus"));
		form.setProcessButton(inforOrderData.getString("orderListStatus"));
		
		JSONArray list = listItemData.getJSONArray("listItem");
		for(int i = 0; i < list.length() ;i++) {	
			JSONObject jsonObject = list.getJSONObject(i);
			ProcessOrderSingleForm singleForm = new ProcessOrderSingleForm();
			form.getListItem().getChildren().add(singleForm.getRoot());
			
			singleForm.disPlayData(String.valueOf(i+1),
									jsonObject.getString("merchandiseCode"),
									jsonObject.getString("merchandiseName"),
									String.valueOf(jsonObject.getInt("quantity")),
									jsonObject.getString("unit"),
									jsonObject.getString("mean"),
									jsonObject.getString("orderStatus") );
			
			listSingleForm.add(singleForm);
		    }
		form.addPaginationHandle(list.length(), 10);
      }
	
	public void handleAllAction() {
		handleBackToPrevPage();
		handleProcessOrder();
		handleUpdateStatus();
	}
	
	public void handleBackToPrevPage() {
		form.addActionbackToPrevPage( new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				AnchorPane i = (AnchorPane) root;
				i.getChildren().clear();
				ViewOrderListController controller = new ViewOrderListController(new ViewOrderListForm(),new ViewOrderListService());
		    	controller.handleAllAction();

		    	i.getChildren().add(controller.getRoot());
				
			}
		});
	}
	
    private void handleProcessOrder() {
      form.addActionProcessOrder(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					
					Stage parentStage = (Stage) root.getScene().getWindow();
					ConfirmPopUpController popUpProcess = new ConfirmPopUpController();
					popUpProcess.getRoot();
					popUpProcess.addActionCancelPopUp();
					popUpProcess.setNoTiText("Bạn muốn huỷ hay đồng ý đơn hàng");
					
					popUpProcess.addActionAcceptOrder(new EventHandler<Event>() {
						@Override
						public void handle(Event arg0) {
							    service.updateDataWhenAccept(orderID);
							    popUpProcess.closePopUp();
				                form.getListItem().getChildren().clear();
				                fetchAndDisplayData(orderID);
				                System.out.println("this code work");
				                ProcessOrderController controller = new ProcessOrderController(new ProcessOrderForm(), new ProcessOrderService());
								AnchorPane i = (AnchorPane) root ;
								i.getChildren().clear();
								i.getChildren().add(controller.getRoot());
								controller.fetchAndDisplayData(orderID);
								controller.handleAllAction();
								controller.setPrev(prev);
						}
		                });
					
					popUpProcess.addActionRejectOrder(new EventHandler<Event>() {
						@Override
						public void handle(Event arg0) {
							  	service.updateDataWhenReject(orderID);
							    popUpProcess.closePopUp();
				                form.getListItem().getChildren().clear();
				                fetchAndDisplayData(orderID);

						}
		                });
					
					popUpProcess.openAsPopUp(parentStage,350,200);
				}
			});
    
   }
    
    	private void handleUpdateStatus() {
    		for(ProcessOrderSingleForm a : listSingleForm) {
    			a.addActionUpdateStatus(new EventHandler<MouseEvent>() {
    				@Override
    				public void handle(MouseEvent arg0) {
    					Stage parentStage = (Stage) root.getScene().getWindow();
    					ConfirmPopUpController popUpProcess = new ConfirmPopUpController();
    					popUpProcess.getRoot();
    					popUpProcess.addActionCancelPopUp();
    					popUpProcess.setNoTiText("Mặt hàng này đã vận chuyển?");
    					popUpProcess.addActionAcceptOrder(new EventHandler<Event>() {
    						@Override
    						public void handle(Event arg0) {
    							    service.updateTransportStatus(orderID, a.getMerchandiseCode());
    							    popUpProcess.closePopUp();
    				                form.getListItem().getChildren().clear();
    				                form.resetPagination();
    				                fetchAndDisplayData(orderID);	
    				                ProcessOrderController controller = new ProcessOrderController(new ProcessOrderForm(), new ProcessOrderService());
    								AnchorPane i = (AnchorPane) root ;
    								i.getChildren().clear();
    								i.getChildren().add(controller.getRoot());
    								controller.fetchAndDisplayData(orderID);
    								controller.handleAllAction();
    								controller.setPrev(prev);
    						}
    		                });	
    					
    					popUpProcess.addActionRejectOrder(new EventHandler<Event>() {
    						@Override
    						public void handle(Event arg0) {
    							 popUpProcess.closePopUp(); 
    						}
    		                });
    					
    					popUpProcess.openAsPopUp(parentStage,350,200);
    				}
    			}) ;
    		}
    	}
    
}
		
	

