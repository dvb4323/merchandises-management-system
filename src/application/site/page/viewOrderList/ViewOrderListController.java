package application.site.page.viewOrderList;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import javafx.event.EventHandler;
import java.util.ArrayList;
import application.site.page.ProcessOrder.ProcessOrderController;
import application.site.page.ProcessOrder.ProcessOrderForm;
import application.site.service.ProcessOrderService;
import application.site.service.ViewOrderListService;
import application.AController;

public class ViewOrderListController extends AController {

	private ViewOrderListForm form;
	private List<ViewOrderListSingleForm> listSingleForm = new ArrayList<ViewOrderListSingleForm>();
	private ViewOrderListService service ;
	
	public ViewOrderListController(ViewOrderListForm form, ViewOrderListService service) {
		this.form =form;
		root = form.getRoot();
		this.service = service ;
	}
	
	public void handleAllAction() {
		fetchAndDisplayData();
		handleViewDetail();
		handleSearch();
	}

	
	private void fetchAndDisplayData() {
		JSONObject data = new JSONObject();
		if(form.getSearchByStatus() != null && form.getSearchByListToBeOrderedID() != null) {
			if(form.getSearchByStatus().equals("Tất cả")) {
				data = service.getListToBeOrdered(Integer.parseInt(form.getSearchByListToBeOrderedID()) , null);
				System.out.println(Integer.parseInt(form.getSearchByListToBeOrderedID()));
			} else {
				data = service.getListToBeOrdered(Integer.parseInt(form.getSearchByListToBeOrderedID())  , null);
				System.out.println(Integer.parseInt(form.getSearchByListToBeOrderedID()));
			}
		}
		
		else {
			 data = service.getListToBeOrdered(Integer.getInteger(form.getSearchByListToBeOrderedID())  , form.getSearchByStatus());
		}
	
		JSONArray list = data.getJSONArray("listOrder");
		for(int i = 0; i < list.length() ;i++) {	
			JSONObject jsonObject = list.getJSONObject(i);
			ViewOrderListSingleForm singleForm = new ViewOrderListSingleForm();
			form.getListItem().getChildren().add(singleForm.getRoot());
			singleForm.disPlayData( String.valueOf(i+1),
									String.valueOf(jsonObject.getInt("orderId")),
									String.valueOf(jsonObject.getInt("numberOfItem")),
									jsonObject.getString("orderListStatus"));
			listSingleForm.add(singleForm);
			
		}
		form.addPaginationHandle(list.length(), 10);
	}	
   
	private void handleViewDetail() {

		for(ViewOrderListSingleForm a : listSingleForm) {
			a.addActionviewDetail(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					ProcessOrderController controller = new ProcessOrderController(new ProcessOrderForm(), new ProcessOrderService());
					AnchorPane i = (AnchorPane) root ;
					i.getChildren().add(controller.getRoot());
					 int orderID = Integer.parseInt(a.getOrderId());
					controller.fetchAndDisplayData(orderID);
					controller.handleAllAction();
					AnchorPane anch = (AnchorPane) root ;
					
					controller.setPrev(anch);
				}
			}) ;
		}
	}
	
	private void handleSearch() {
		form.addActionSearchButton(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					form.getListItem().getChildren().clear();
					listSingleForm.clear();
					form.resetPagination();
					
					fetchAndDisplayData();
					handleViewDetail();
				}
			});
	}
	
}
