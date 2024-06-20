package application.sales.page.viewOrder;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import application.AController;
import application.oversea.service.AllListToBeOrderedService;
import application.oversea.service.DetailListToBeOrderedService;
import application.sales.page.viewDetailOrder.ViewDetailOrderController;
import application.sales.page.viewDetailOrder.ViewDetailOrderPageForm;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ViewOrderController extends  AController{
	
	private AllListToBeOrderedService service ;
	private ViewOrderPageForm  form ;
	private List<ViewOrderSingleForm > listSinglePaneForm = new ArrayList<ViewOrderSingleForm >();

	public ViewOrderController(ViewOrderPageForm form, AllListToBeOrderedService service) {
		this.form = form ;
		this.root = form.getRoot();
		this.service = service;

	}

	public void fetchAndDisplayData() {
		JSONObject inforOrderData =  service.getAllListToBeOrdered(form.getSearchByListToBeOrderedID(),form.getSearchByStatus());
		JSONArray list = inforOrderData.getJSONArray("listOrder");
		for(int i = 0; i < list.length() ;i++) {	
		   JSONObject jsonObject = list.getJSONObject(i);
	       ViewOrderSingleForm controller = new ViewOrderSingleForm ();
	       form.getListItem().getChildren().add(controller.getRoot());
		   controller.disPlayData( jsonObject.getString("listToBeOrderedID"),
				   					jsonObject.getString("createdDate"),
				   					String.valueOf(jsonObject.getInt("numberOfItem")),
				   					jsonObject.getString("status")
				   					);
		   listSinglePaneForm.add(controller);
	     }
		
		form.addPaginationHandle(listSinglePaneForm.size(), 10);
	}
	
	public void handleAllAction() {
		fetchAndDisplayData();
		
		handleViewOneOrder();
		handleSearch();
	}
	
	private void handleViewOneOrder() {
		for(ViewOrderSingleForm  a : listSinglePaneForm) {
			a.addActionEventViewSaleOrder(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					
					ViewDetailOrderController contr = new ViewDetailOrderController(new ViewDetailOrderPageForm(), new DetailListToBeOrderedService() ) ;
					contr.fetchAndDisplayData(a.getOrderId());
					contr.handleAllAction();
					AnchorPane i = (AnchorPane) root ;
					i.getChildren().add(contr.getRoot());
					contr.setPrevRoot(i);
				}
			}) ;
		}
	}
	
	private void handleSearch() {
		form.addActionSearchButton(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					form.getListItem().getChildren().clear();
					listSinglePaneForm.clear();
					form.resetPagination();
					fetchAndDisplayData();
					handleViewOneOrder();
				}
			});
	}
	
}
