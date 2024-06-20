package application.sales.page.inputDetailOrder;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class InputDetailOrderPageForm {
    private Parent root;

    @FXML
    private Label cancelButton;

    @FXML
    private DatePicker date;

    @FXML
    private TextField quantity;

    @FXML
    private Label saveButton;

    @FXML
    private Label errorMessageLabel;

    public Parent getRoot() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/sales/view/PopUp.fxml"));
            loader.setController(this);
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
    
    public void setDate(String dateString) {
    	LocalDate localDate = LocalDate.parse(dateString);
    	date.setValue(localDate);
    }
    
    public void setQuantity(String number) {
    	quantity.setText(number)  ;
    }

    public void addActionCancelPopUp(EventHandler<MouseEvent> listener) {
        cancelButton.addEventFilter(MouseEvent.MOUSE_CLICKED, listener);
    }

    public void addActionSaveButton(EventHandler<MouseEvent> listener) {
        saveButton.addEventFilter(MouseEvent.MOUSE_CLICKED, listener);
    }

    public TextField getQuantity() {
        return quantity;
    }

    public Label getErrorMessageLabel() {
        return errorMessageLabel;
    }
    public DatePicker getDate() {
    	return date;
    }
}
