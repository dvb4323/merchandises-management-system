package application.warehouse.page.importedItems;

import java.io.IOException;

import application.APageForm;
import application.warehouse.page.AWarehousePageForm;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ImportedItemsPageForm extends AWarehousePageForm {
	@FXML
    private Label orderListButton;
    
    @FXML
    private Label merchandiseListButton;
    
    @FXML
    private VBox listItem;

    @FXML
    private ComboBox<String> marketplace;

    @FXML
    private VBox vpage;

    @FXML
    private TextField search;

    @FXML
    private Button search_btn;
    
    public Parent getRoot() {
  		try {
  			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/warehouse/view/WarehouseImportedItems.fxml"));
  			loader.setController(this); 
  			root = loader.load();
  		
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		return root;
  	}
    
    public String getSearchByListToBeOrderedID () {
		String listToBeOrderedIDSearch = search.getText() ;
		if(listToBeOrderedIDSearch.isEmpty())    return null ;
		else return listToBeOrderedIDSearch;
	}
    
    public void addActionSearchButton(EventHandler<MouseEvent> listener) {
    	search_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
    public void addActionGoToOrderList(EventHandler<MouseEvent> listener) {
    	orderListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
    
    public void addActionGoToMerchandiseList(EventHandler<MouseEvent> listener) {
    	merchandiseListButton.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }


}
