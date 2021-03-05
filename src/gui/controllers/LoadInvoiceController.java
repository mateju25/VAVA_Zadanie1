package gui.controllers;

import app.Invoice;
import app.InvoiceSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class LoadInvoiceController extends SampleController {
    @FXML
    public Button cancelButton;
    @FXML
    public ListView listOfInvoices;

    private Invoice chosedInvoice = null;

    @FXML
    public void initialize() {
        super.initialize(listOfInvoices);
    }

    @FXML
    public void load_customer(ActionEvent actionEvent) {
        chosedInvoice = (Invoice) listOfInvoices.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) listOfInvoices.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public Invoice getChosedInvoice() {
        return chosedInvoice;
    }

    public void loadCustomerController(InvoiceSystem sys) {
        localSys = sys;
        listOfInvoices.getItems().clear();
        listOfInvoices.getItems().addAll(sys.getListOfInvoice());
    }
}
