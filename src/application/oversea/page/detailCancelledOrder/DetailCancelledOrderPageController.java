package application.oversea.page.detailCancelledOrder;

import java.util.ArrayList;
import java.util.List;

import application.AController;
import application.oversea.dbsubsystem.IOrderDB;
import application.oversea.model.ItemOrderToSiteModel;
import application.oversea.model.ItemToBeOrderedModel;
import application.oversea.model.OrderModel;
import application.oversea.page.detailListToBeOrdered.DetailListToBeOrderedController;
import application.oversea.page.detailListToBeOrdered.DetailListToBeOrderedPageForm;
import application.oversea.service.DetailListToBeOrderedService;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DetailCancelledOrderPageController extends  AController{

	private AnchorPane prev;
	private IOrderDB db;
	private List<ItemOrderToSiteModel> data;
	private DetailCancelledOrderPageForm form;
	private List<DetailCancelledOrderSingleForm> listSingleForm = new ArrayList<DetailCancelledOrderSingleForm>();
	private OrderModel order ;
	private String orderID;
	private String listToBeOrderedID;
	public DetailCancelledOrderPageController(DetailCancelledOrderPageForm form, IOrderDB db) {
		this.form = form;
		this.root = form.getRoot();
		this.db = db;

	}

	public void setPrevRoot(AnchorPane e) {
		prev = e;
	}

	public void fetchAndDisplayData(String orderID) {
		data = db.getAllItem(orderID);
		if(data.size() > 0) listToBeOrderedID =  data.get(0).getListToBeOrderedID();
		displayOrderDetails(orderID);
		this.orderID = orderID;
		for (ItemOrderToSiteModel a : data) {
			DetailCancelledOrderSingleForm singleForm = new DetailCancelledOrderSingleForm();
			form.getListItem().getChildren().add(singleForm.getRoot());
			singleForm.disPlayData(String.valueOf(data.indexOf(a)+1), a.getMerchandiseCode(), a.getMerchandiseName(),
					String.valueOf(a.getQuantity()), a.getUnit(), a.getMean());
			listSingleForm.add(singleForm);
		}

		form.addPaginationHandle(data.size(), 5);
	}

	public void displayOrderDetails(String orderId) {
		 order = db.printOrderDetail(orderId);
		System.out.println(order);
		if (order != null) {
			form.displayData(order.getOrderID(), order.getListToBeOrderedID(), String.valueOf(order.getNumberOfItem()),
					order.getSiteCode(), order.getCreatedDate(),order.getNote());
			// System.out.println(order.getNote1());
		}
	}

	public void handleAllAction() {
		handleBackToPrevPage();
		handleUpdateNoteAndBack();
	}

	private void handleBackToPrevPage() {
		form.addActionBackToPrePage(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				prev.getChildren().remove(root);
			}
		});
	}
	
	public void handleUpdateNoteAndBack() {
		form.addActionAddToList(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				String id = db.reOrder(data, listToBeOrderedID);
				
				DetailListToBeOrderedPageForm form = new DetailListToBeOrderedPageForm();
				DetailListToBeOrderedService service = new DetailListToBeOrderedService();
				DetailListToBeOrderedController contr = new DetailListToBeOrderedController(form,service ) ;

				contr.fetchAndDisplayData(id);
				contr.handleAllAction();
				AnchorPane i = (AnchorPane) root ;
				i.getChildren().add(contr.getRoot());
				contr.setPrevRoot(i);
			}
 
		});
	}
}