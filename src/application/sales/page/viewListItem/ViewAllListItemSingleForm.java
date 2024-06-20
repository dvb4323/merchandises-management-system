package application.sales.page.viewListItem;

import javafx.event.EventHandler;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ViewAllListItemSingleForm {
	private Parent root;

    @FXML
    private Label stt;
    @FXML
    private Label idItem;
    @FXML
    private Label nameItem;
    @FXML
    private Label unit;
    @FXML
    private Label action;  
    public Parent getRoot() {
    	try {
    	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/sales/view/ViewAllListItemSingleForm.fxml"));
    	loader.setController(this);
    	
	    root = loader.load();
	    root.setVisible(false);
	    root.setManaged(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return root ;
    }
    
    public void disPlayData(String... args) {
    	stt.setText(args[0]);
    	idItem.setText(args[1]);
    	nameItem.setText(args[2]);
    	unit.setText(args[3]);	
    }
    
    public String getId() {return idItem.getText();}
    
    public void addActionAddItem(EventHandler<MouseEvent> listener) {
    	action.addEventFilter(MouseEvent.MOUSE_CLICKED, listener);
    }
}
