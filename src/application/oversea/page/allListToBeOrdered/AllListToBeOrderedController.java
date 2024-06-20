package application.oversea.page.allListToBeOrdered;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import application.AController;
import application.oversea.page.detailListToBeOrdered.DetailListToBeOrderedController;
import application.oversea.page.detailListToBeOrdered.DetailListToBeOrderedPageForm;
import application.oversea.service.AllListToBeOrderedService;
import application.oversea.service.DetailListToBeOrderedService;

public class AllListToBeOrderedController extends AController {
	
	private AllListToBeOrderedService service ;
	private AllListToBeOrderedPageForm form ;
	private List<AllListToBeOrderedSingleForm> listSinglePaneForm = new ArrayList<AllListToBeOrderedSingleForm>();

	public AllListToBeOrderedController(AllListToBeOrderedPageForm form, AllListToBeOrderedService service) {
		this.form = form ;
		this.root = form.getRoot();
		this.service = service;

	}
	
	public void fetchAndDisplayData() {
		JSONObject inforOrderData =  service.getAllListToBeOrdered(form.getSearchByListToBeOrderedID(),form.getSearchByStatus());
		JSONArray list = inforOrderData.getJSONArray("listOrder");
		for(int i = 0; i < list.length() ;i++) {	
		   JSONObject jsonObject = list.getJSONObject(i);
	       AllListToBeOrderedSingleForm controller = new AllListToBeOrderedSingleForm();
	       form.getListItem().getChildren().add(controller.getRoot());
	       System.out.println(jsonObject.toString());
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
		for(AllListToBeOrderedSingleForm a : listSinglePaneForm) {
			a.addActionEventViewSaleOrder(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					DetailListToBeOrderedPageForm form = new DetailListToBeOrderedPageForm();
					DetailListToBeOrderedService service = new DetailListToBeOrderedService();
					DetailListToBeOrderedController contr = new DetailListToBeOrderedController(form,service ) ;
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

    
