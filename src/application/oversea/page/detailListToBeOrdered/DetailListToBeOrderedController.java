package application.oversea.page.detailListToBeOrdered;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import application.AController;
import application.app.page.component.AlertPopUpController;
import application.oversea.page.createOrder.CreateOrderController;
import application.oversea.page.createOrder.CreateOrderPageForm;
import application.oversea.page.siteSupply.SiteSupplyController;
import application.oversea.page.siteSupply.SiteSupplyPageForm;
import application.oversea.service.CreateOrderService;
import application.oversea.service.DetailListToBeOrderedService;
import application.oversea.service.SelectSiteService;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DetailListToBeOrderedController extends  AController {
	
	private DetailListToBeOrderedPageForm form ;
	private DetailListToBeOrderedService service ;
	private List<DetailListToBeOrderedSingleForm> listSinglePaneForm = new ArrayList<DetailListToBeOrderedSingleForm>();
	private AnchorPane prev ;
	private String listToBeOrderedID ;
	
	public DetailListToBeOrderedController(DetailListToBeOrderedPageForm form, DetailListToBeOrderedService service) {
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
		form.setStatus(status);

		JSONArray list = listItemData.getJSONArray("listItem");
		for(int i = 0; i < list.length() ;i++) {	
			JSONObject jsonObject = list.getJSONObject(i);
			DetailListToBeOrderedSingleForm singleform = new DetailListToBeOrderedSingleForm();
			form.getListItem().getChildren().add(singleform.getRoot());
			listSinglePaneForm.add(singleform) ;
			singleform.disPlayData(String.valueOf(i+1),
									jsonObject.getString("merchandiseCode"),
									jsonObject.getString("merchandiseName"),
									String.valueOf(jsonObject.getInt("quantity")),
									jsonObject.getString("unit"),
									jsonObject.getString("desiredDeliveryDate"),
									String.valueOf(jsonObject.getInt("notProcessQuantity"))
									);
	
        }		
	}
	
	public void handleAllAction() {
		handleViewSiteSupply();
		handleBackToPrevPage();
		handleOrder();	
	}
	
	private void handleBackToPrevPage() {
		form.addActionBackToPrePage(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				prev.getChildren().remove(root);
			}
		});
	}
	
	private void handleOrder() {
		form.addActionOrder(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				Stage parentStage = (Stage) root.getScene().getWindow();
				AnchorPane a = (AnchorPane) root ;
				CreateOrderController controller = new CreateOrderController(new CreateOrderPageForm(),
																						 new CreateOrderService() );
				Parent childRoot = controller.getRoot();
				
				boolean check = controller.fetchAndDisplayData(listToBeOrderedID);
				if(check) {
					a.getChildren().add(childRoot);
					controller.setPrevRoot(a);
				}
				else {
					 AlertPopUpController contr = new AlertPopUpController(); 
					contr.getRoot();
					contr.setNoTiText("Không có đơn hàng nào trong giỏ");
					contr.openAsPopUp(parentStage,3000, 300, 230);
					
				}
				
			}
		});
	}

	private void handleViewSiteSupply() {
		for(DetailListToBeOrderedSingleForm a : listSinglePaneForm) {
			a.addActionViewSiteSupply(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					Stage parentStage = (Stage) root.getScene().getWindow();
					
					SiteSupplyController controller = new SiteSupplyController(new SiteSupplyPageForm(), 
																			   new SelectSiteService());
					
				    boolean check = controller.fetchAndDisplayData(form.getOrderID(), a.getMerchandiseCode());
					if(!check) {
						AlertPopUpController a = new AlertPopUpController(); 
						a.getRoot();
						a.setNoTiText("Không có site nào cung ứng sản phầm này");
						a.openAsPopUp(parentStage,3000, 300, 230);
					}
				    else {
						controller.handleAllAction();
						controller.openAsPopUp(parentStage,1010,600);
					}
				    	
				}
			}) ;
		}
	}
	
}
