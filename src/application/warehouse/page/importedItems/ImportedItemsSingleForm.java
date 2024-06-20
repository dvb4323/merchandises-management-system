package application.warehouse.page.importedItems;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class ImportedItemsSingleForm {
	private Parent root ;
	@FXML
    private Label stt;

    @FXML
    private Label merchandiseCode;

    @FXML
    private Label merchandiseName;

    @FXML
    private Label quantity;
    
    @FXML
    private Label unit;

    
    public Parent getRoot() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/warehouse/view/WarehouseImportedItemsSingle.fxml"));
			loader.setController(this); 
			root = loader.load();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
    public void disPlayData(String... strings) {
		stt.setText(strings[0]);
		merchandiseCode.setText(strings[1]);
		merchandiseName.setText(strings[2]);
		quantity.setText(strings[3]);
		unit.setText(strings[4]);
	}
    
}
