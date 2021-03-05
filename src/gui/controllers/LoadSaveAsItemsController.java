package gui.controllers;

import app.Customer;
import app.Invoice;
import app.InvoiceSystem;
import app.SaveAsItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class LoadSaveAsItemsController extends SampleController {
    @FXML
    public Button cancelButton;
    @FXML
    public ListView listOfItems;

    private SaveAsItem chosedItem = null;

    @FXML
    public void initialize() {
        super.initialize(listOfItems);
    }

    @FXML
    public void use_item(ActionEvent actionEvent) {
        chosedItem = (SaveAsItem) listOfItems.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) listOfItems.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public SaveAsItem getChosedItem() {
        return chosedItem;
    }

    public void loadCustomerController(InvoiceSystem sys, Class neededClass) {
        localSys = sys;
        listOfItems.getItems().clear();
        if (neededClass == Customer.class)
            listOfItems.getItems().addAll(sys.getListOfCustomers());
        if (neededClass == Invoice.class)
            listOfItems.getItems().addAll(sys.getListOfInvoice());
    }
}
