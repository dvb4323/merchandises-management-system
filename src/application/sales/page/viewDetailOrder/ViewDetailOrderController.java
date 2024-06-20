package application.sales.page.viewDetailOrder;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import application.AController;
import application.oversea.service.DetailListToBeOrderedService;
import application.sales.page.viewListItem.ViewAllListItemController;
import application.sales.page.viewListItem.ViewAllListItemPageForm;
import application.sales.service.ViewAllListItemService;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ViewDetailOrderController extends  AController{
	
	private ViewDetailOrderPageForm form ;
	private DetailListToBeOrderedService service ;
	private List<ViewDetailOrderSingleForm> listSinglePaneForm = new ArrayList<ViewDetailOrderSingleForm>();
	private AnchorPane prev ;
	private String listToBeOrderedID ;
	
	public ViewDetailOrderController(ViewDetailOrderPageForm form, DetailListToBeOrderedService service) {
		this.form = form ;
		this.root = form.getRoot();
		this.service = service;	
	}
	
	public void setPrevRoot(AnchorPane e) {
		prev = e ;
	}
	
	public void fetchAndDisplayData(String listToBeOrderedID) {
		JSONObject listItemData = service.getAllListToBeOrdered(listToBeOrderedID) ;
		JSONObject orderInforData = service.getInforOrder(listToBeOrderedID);
		String status = orderInforData.getString("status");
		this.listToBeOrderedID = listToBeOrderedID; 
		form.setOrderID(listToBeOrderedID);

		JSONArray list = listItemData.getJSONArray("listItem");
		for(int i = 0; i < list.length() ;i++) {	
			JSONObject jsonObject = list.getJSONObject(i);
			ViewDetailOrderSingleForm singleform = new ViewDetailOrderSingleForm();
			form.getListItem().getChildren().add(singleform.getRoot());
			listSinglePaneForm.add(singleform) ;
			singleform.disPlayData(String.valueOf(i+1),
									jsonObject.getString("merchandiseCode"),
									jsonObject.getString("merchandiseName"),
									String.valueOf(jsonObject.getInt("quantity")),
									jsonObject.getString("unit"),
									jsonObject.getString("desiredDeliveryDate")
									);
	
        }		
	}
	
	public void handleAllAction() {
		handleBackToPrevPage();	
	}
	
	private void handleBackToPrevPage() {
		form.addActionBackToPrePage(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
//				prev.getChildren().remove(root);
				ViewAllListItemController controller = new ViewAllListItemController(new ViewAllListItemPageForm(), new ViewAllListItemService());
				
				AnchorPane i = (AnchorPane)root;
				i.getChildren().clear();
				i.getChildren().add(controller.getRoot());
				controller.handleAllAction();
				
				
			}
		});
	}
	
}
