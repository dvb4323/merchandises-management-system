package application.oversea.page.siteSupply;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import application.APopUp;
import application.app.page.component.AlertPopUpController;
import application.oversea.service.SelectSiteService;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SiteSupplyController extends APopUp {
	
	private SelectSiteService service ;
	private List<SiteSupplySingleForm> listSinglePane = new ArrayList<SiteSupplySingleForm>();
	private SiteSupplyPageForm form;
	private String listToBeOrderedID ;
	private String merchandiseCode ;
	private int totalQuantitySelect = 0;
	private int notProcessQuantity = 0;
	
    public SiteSupplyController (SiteSupplyPageForm a, SelectSiteService service ) {
    	this.form           = a ;
    	super.root          = form.getRoot();
    	this.service        = service ;
    	stage               = new Stage() ;
    }
    
    public Parent getRoot() {
    	return root;
    }
	
    public boolean fetchAndDisplayData(String listToBeOrderedId, String merchandiseCode) {
    	
    	this.listToBeOrderedID  = listToBeOrderedId ;
    	this.merchandiseCode    = merchandiseCode;
		JSONObject data         = service.getOrderItemDetail(listToBeOrderedId, merchandiseCode);
		this.notProcessQuantity = data.getInt("notProcessQuantity") ;
		form.disPlayData(merchandiseCode,data.getString("merchandiseName"),String.valueOf(this.notProcessQuantity),
						 data.getString("desiredDeliveryDate"));
		JSONArray listSiteSupplyData = service.getSiteSupply(merchandiseCode).getJSONArray("listSite");
		
		if(listSiteSupplyData.length()==0) {
			return false ;
		} else {
			
			for(int i = 0; i < listSiteSupplyData.length() ;i++) {	
				JSONObject jsonObject = listSiteSupplyData.getJSONObject(i);
	        	SiteSupplySingleForm controller = new SiteSupplySingleForm();
				form.getListItem().getChildren().add(controller.getRoot());
				listSinglePane.add(controller);
				String siteCode = jsonObject.getString("siteCode") ;
				if(service.checkInCart(listToBeOrderedId, merchandiseCode,siteCode )) {
					System.out.println("yet in cart");
					controller.getOrderCheckBox().setSelected(true);
					controller.getQuantityTextField().setVisible(true);
					controller.getComboBox().setVisible(true);
					controller.getQuantityTextField().setText(String.valueOf(service.getItemQuantityInCart(listToBeOrderedId, merchandiseCode, siteCode)));
					controller.getComboBox().setValue(service.getItemTransportInCart(listToBeOrderedId, merchandiseCode, siteCode));
					
				} else System.out.println("not in cart");
				controller.disPlayData(siteCode ,
						siteCode,String.valueOf(jsonObject.getInt("inStockQuantity"))  ,
						jsonObject.getString("unit") ,
						String.valueOf(jsonObject.getInt("shipDeliveryDay")) ,
						String.valueOf(jsonObject.getInt("airDeliveryDay")) ,
						String.valueOf(i+1)
						) ;
						   
			     }
			  form.addPaginationHandle(listSiteSupplyData.length(), 10);
			  
		}		
		     return true;
	}
   
	public void handleAllAction() {
		handleClosePopUp();
		handleSaveAndCancelPopUp();
	}
	
	private void handleClosePopUp() {
		form.addActionCancelPopUp(e-> stage.close());
	}
	
	private void handleSaveAndCancelPopUp() {
		form.addActionSaveAndCancelPopUp(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
					if(!checkFormatData()) {
						String noti = "Cần điền đầy đủ thông tin";
						handleError(noti);
					} 
					else if( totalQuantitySelect > notProcessQuantity ){
						String noti = "Đặt quá số lượng cần xử lý" ;
						handleError(noti);
					}
					else {
						service.updateCart(listToBeOrderedID, merchandiseCode);

					   stage.close();	
				     }
			}
	    });
	}
	
	private void handleError(String noti) {
		Stage parentStage = (Stage) root.getScene().getWindow();
		AlertPopUpController contr = new AlertPopUpController();
		contr.getRoot();
		contr.setNoTiText(noti);
		contr.openAsPopUp( parentStage, 3000,300,230);
	}
	private boolean checkFormatData(){

		for(SiteSupplySingleForm a : listSinglePane) {
			if( !a.getQuantity().isEmpty() &&  ( a.getMean() != null) ) {
				int quantity = Integer.parseInt(a.getQuantity());
				totalQuantitySelect += quantity;
				service.addSelectItemToList(listToBeOrderedID, a.getSiteCode(), merchandiseCode, quantity, a.getUnit(), a.getMean());
			} 
			else if (a.getOrderCheckBox().isSelected()&&(a.getQuantity().isEmpty() || a.getMean() == null))  {
				System.out.println("Wrong data");
				service.resetList();
				return false ;
			}
		}
		return true;	
	}
}
