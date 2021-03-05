package gui.controllers;

import app.Customer;
import app.InvoiceSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class LoadCustomerController extends SampleController {
    @FXML
    public Button cancelButton;
    @FXML
    public ListView listOfCustomers;

    private Customer chosedCustomer = null;

    @FXML
    public void initialize() {
        super.initialize(listOfCustomers);
    }

    @FXML
    public void load_customer(ActionEvent actionEvent) {
        chosedCustomer = (Customer) listOfCustomers.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) listOfCustomers.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public Customer getChosedCustomer() {
        return chosedCustomer;
    }

    public void loadCustomerController(InvoiceSystem sys) {
        localSys = sys;
        listOfCustomers.getItems().clear();
        listOfCustomers.getItems().addAll(sys.getListOfCustomers());
    }
}
