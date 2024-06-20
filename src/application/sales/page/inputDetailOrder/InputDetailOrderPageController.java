package application.sales.page.inputDetailOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import application.APopUp;
import application.sales.dbsubsystem.mysql.MySQLCartDB;
import application.sales.dbsubsystem.mysql.MySQLDetailMerchandiseDB;
import application.sales.model.DetailMerchandiseModel;
import application.sales.model.MerchandiseModel;
import application.sales.page.component.SuccessAddToCartPopUp;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InputDetailOrderPageController extends APopUp {

    private InputDetailOrderPageForm form;
    private String idItem;

    private MySQLCartDB cartDB = new MySQLCartDB();
    private List<DetailMerchandiseModel> dataList = new ArrayList<DetailMerchandiseModel>();

    public InputDetailOrderPageController(InputDetailOrderPageForm form, String idItem) {
        this.form = form;
        this.idItem = idItem;
        root = form.getRoot();
        super.stage = new Stage();
        
        MySQLDetailMerchandiseDB cartDB = new MySQLDetailMerchandiseDB();
        dataList = cartDB.getdetailListItem() ;
        for (DetailMerchandiseModel i : dataList) {
        	if(idItem.equals(i.getMerchandiseCode())) {
        		String quantity = Integer.toString(i.getQuantityOrdered());
        		form.getQuantity().setText(quantity);
        		form.getDate().setValue(LocalDate.parse(i.getDeliveryDateDesired()));
        	}
        }
    }

    public Parent getRoot() {
        return root;
    }

    public void handleAllAction() {
        handleClosePopUp();
        handleSaveAndClose();
    }

    private void handleClosePopUp() {
        form.addActionCancelPopUp(e -> stage.close());
    }
    
//    public void handleEditInput(String dateString, String number) {
//    	form.setDate(dateString);
//    	form.setQuantity(number);
//   
//    }

    private void handleSaveAndClose() {
        form.addActionSaveButton(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    int quantity = Integer.parseInt(form.getQuantity().getText());
                    if (quantity < 0) {
                        form.getErrorMessageLabel().setText("*Số lượng phải lớn hơn hoặc bằng 0.*");
                    } else {
                    	
                    	cartDB.insertCart(idItem, quantity, form.getDate().getValue().toString());
                         
                        stage.close();
                        
                        SuccessAddToCartPopUp c = new SuccessAddToCartPopUp();
        				c.getRoot();
        				Stage parent = (Stage) root.getScene().getWindow() ;
        				c.openAsPopUp(parent,3000,300,230);
                    }
                } catch (NumberFormatException e) {
                    form.getErrorMessageLabel().setText("*Vui lòng nhập một số hợp lệ.*");
                }
            }
        });
    }
}