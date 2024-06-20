package application.sales.page.viewOrder;

import java.io.IOException;

import application.APageForm;
import application.sales.page.ASalePageForm;
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

public class ViewOrderPageForm extends ASalePageForm{
	@FXML
    private TextField search;
	@FXML
    private ComboBox<String> selectStatus;
	@FXML
    private Button search_btn;
		
	public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/sales/view/ViewOrderListItem.fxml"));
			System.out.println(getClass().getClassLoader().getResource("application/view/Sample.fxml"));
			loader.setController(this); 
			root = loader.load();
			ObservableList<String> options = FXCollections.observableArrayList("Đang xử lý","Chưa xử lý","Đã xử lý","Tất cả");
			selectStatus.setItems(options);
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
	
	public String getSearchByStatus () {
		String status = selectStatus.getValue() ;
		if (status != null && !status.equals("Tất cả")) return status ;
		else return null ;
	}
	
	public void addActionSearchButton(EventHandler<MouseEvent> listener) {
    	search_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, listener);
    }
}
