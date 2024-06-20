package application.oversea.page.orderToSite;
import java.util.ArrayList;
import java.util.List;

import application.AController;
import application.oversea.dbsubsystem.IOrderDB;
import application.oversea.model.OrderModel;
import javafx.scene.Parent;

public class OrderToSiteController extends  AController{
	private List<OrderModel> data ;
	private IOrderDB db;
	private List<OrderToSiteSingleForm> listSinglePane = new ArrayList<OrderToSiteSingleForm>();
	private OrderToSitePageForm form;

    public OrderToSiteController (OrderToSitePageForm form, IOrderDB  db ) {
    	this.form          = form ;
    	this.root          = form.getRoot();
    	this.db            = db;	
    }
    
    public void handleAllAction() {
    	fetchAndDisplayData();
    }
    
    public void fetchAndDisplayData() {
    	data = db.getAllOrder(null);
		for (OrderModel a :  data) {
			OrderToSiteSingleForm singleform = new  OrderToSiteSingleForm();
			form.getListItem().getChildren().add(singleform.getRoot());
			listSinglePane.add(singleform);
			singleform.displayData( a.getOrderID(),a.getListToBeOrderedID(),a.getSiteCode(),
								String.valueOf(a.getNumberOfItem())	,a.getStatus());   
		   
				
	    }
		 form.addPaginationHandle(data.size(), 10);
    }
		
//     private void handleViewOneOrder() {
//		for(AllListOrderSingleForm a : listSinglePane) {
//			a.addActionEventViewOrder(new EventHandler<MouseEvent>() {
//				@Override
//				public void handle(MouseEvent arg0) {
//					ReceiveOrderPageController contr = new ReceiveOrderPageController
//							(new ReceiveOrderPageForm(),new MySQLWarehouseOrderDB()) ;
//				
//					contr.fetchAndDisplayData(data.get(listSinglePane.indexOf(a)));
//					contr.handleAllAction();
//					AnchorPane i = (AnchorPane) root ;
//					i.getChildren().add(contr.getRoot());
//					contr.setPrevRoot(i);
//				}
//			}) ;
//		}
//	}
}




