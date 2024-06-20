package application.sales.page.viewListItem;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import application.AController;
import application.sales.page.createOrder.CreateOrderController;
import application.sales.page.createOrder.CreateOrderPageForm;
import application.sales.page.inputDetailOrder.InputDetailOrderPageController;
import application.sales.page.inputDetailOrder.InputDetailOrderPageForm;
import application.sales.service.DetailOrderItemService;
import application.sales.service.ViewAllListItemService;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewAllListItemController extends  AController{

    private ViewAllListItemService service ;
    private ViewAllListItemPageForm form;
    private List<ViewAllListItemSingleForm> listSingleForm = new ArrayList<>();

    public ViewAllListItemController(ViewAllListItemPageForm form, ViewAllListItemService service) {
        this.form = form;
        this.service = service;
        root = form.getRoot();
       
    }

    private void fetchAndDisplayData() {

    	 JSONObject inforOrderData =  service.getlistItem();
		 JSONArray list = inforOrderData.getJSONArray("listItem");
		 for(int i = 0; i < list.length() ;i++) {	
			JSONObject jsonObject = list.getJSONObject(i);
            ViewAllListItemSingleForm singleForm = new ViewAllListItemSingleForm();
            form.getListItem().getChildren().add(singleForm.getRoot());
            singleForm.disPlayData(String.valueOf(i+1), 
            		jsonObject.getString("merchandiseCode"),
   					jsonObject.getString("merchandiseName"),
   					jsonObject.getString("unit")
            	);
            listSingleForm.add(singleForm);

            singleForm.addActionAddItem(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                	
                    InputDetailOrderPageController controller = new InputDetailOrderPageController(new InputDetailOrderPageForm(), singleForm.getId() );
                    Stage parentStage = (Stage) root.getScene().getWindow();
                    controller.getRoot();
                    controller.handleAllAction();
                    controller.openAsPopUp(parentStage,600,400);
                    
                   
                }
            });

        }
        form.addPaginationHandle(list.length(), 12);
    }

    public void handleCreateOrderAction() {
        form.addActionCreateOrder(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	
                CreateOrderController detailContr = new CreateOrderController(new CreateOrderPageForm(), new DetailOrderItemService());
                detailContr.handleAllAction();
                AnchorPane a = (AnchorPane) root;
                a.getChildren().add(detailContr.getRoot());
                detailContr.setPrevRoot(a);
                
                
            }
        });
    }

	@Override
	public void handleAllAction() {
		 fetchAndDisplayData();
	     handleCreateOrderAction();
	}
}
