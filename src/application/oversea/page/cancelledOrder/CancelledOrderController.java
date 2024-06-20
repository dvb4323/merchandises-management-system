package application.oversea.page.cancelledOrder;

import java.util.ArrayList;
import java.util.List;

import application.oversea.dbsubsystem.IOrderDB;
import application.oversea.dbsubsystem.mysql.MySQLOrderDB;
import application.oversea.model.OrderModel;
import application.oversea.page.detailCancelledOrder.DetailCancelledOrderPageController;
import application.oversea.page.detailCancelledOrder.DetailCancelledOrderPageForm;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class CancelledOrderController {

	private IOrderDB db;
//	private List<EntityListToBeOrdered> data ;
	private Parent root;
	private CancelledOrderPageForm form;
	private List<CancelledOrderSingleForm> listSinglePaneForm = new ArrayList<CancelledOrderSingleForm>();
	private List<OrderModel> data = new ArrayList<OrderModel>();

	public CancelledOrderController(CancelledOrderPageForm form, IOrderDB db) {
		this.form = form;
		this.root = form.getRoot();
		this.db = db;

		
//		handlePagination();
	}

	public Parent getRoot() {
		return root;
	}
	
	public void handleAllAction() {
		fetchAndDisplayData();
		handleViewOneOrder();
	}
	public void fetchAndDisplayData() {
		data = db.getAllOrder("Đã hủy");
		for (OrderModel a : data) {
			CancelledOrderSingleForm controller = new CancelledOrderSingleForm();
			form.getListItem().getChildren().add(controller.getRoot());
			controller.disPlayData(String.valueOf(data.indexOf(a)+1), a.getOrderID(), String.valueOf(a.getNumberOfItem()),
					a.getCreatedDate(), a.getSiteCode());

			listSinglePaneForm.add(controller);
		}
		form.addPaginationHandle(listSinglePaneForm.size(), 10);
	}

	public void handleViewOneOrder() {
		for (CancelledOrderSingleForm a : listSinglePaneForm) {
			a.addActionViewDetail(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					DetailCancelledOrderPageForm form = new DetailCancelledOrderPageForm();
					IOrderDB db = new MySQLOrderDB();
					DetailCancelledOrderPageController contr = new DetailCancelledOrderPageController(form, db);
					contr.fetchAndDisplayData(a.getOrderID());
					contr.handleAllAction();
//					contr.handleUpdateNoteAndBack(a.getOrderID());
					AnchorPane i = (AnchorPane) root;
					i.getChildren().add(contr.getRoot());
					contr.setPrevRoot(i);

				}
			});
		}
	}
}

