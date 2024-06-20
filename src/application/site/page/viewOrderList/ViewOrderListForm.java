package application.site.page.viewOrderList;


import java.io.IOException;

import application.APageForm;
import application.site.page.ASitePageForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ViewOrderListForm extends ASitePageForm {
	  
	@FXML
	private TextField search;
	@FXML
	private ComboBox<String> selectStatus;
	@FXML
	private Button search_btn;
			
	public Parent getRoot() {
    	try {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/site/view/ViewOrderList.fxml"));
    	loader.setController(this);
    	
	    root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	ObservableList<String> choices = FXCollections.observableArrayList("Tất cả","Chưa xử lý","Đang xử lý", "Đã xử lý","Đã hủy");
        selectStatus.setItems(choices);	
    	return root ;
    	
    }
    
	public ComboBox<String> getComboBox(){
		return selectStatus;
	}
	
	public void addActionSearchButton(EventHandler<MouseEvent> listener) {
    	search_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
	
	public String getSearchByListToBeOrderedID () {
		String listToBeOrderedIDSearch = search.getText() ;
		if(listToBeOrderedIDSearch.isEmpty())    return null ;
		else return listToBeOrderedIDSearch ;
	}
	
	public String getSearchByStatus () {
		String status = selectStatus.getValue() ;
		if (status != null && !status.equals("Tất cả")) return status ;
		else return null ;
	}
}
