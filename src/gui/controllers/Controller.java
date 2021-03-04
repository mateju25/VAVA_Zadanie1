package gui.controllers;

import app.Customer;
import app.InvoiceSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
    private InvoiceSystem mainSys;

    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField town;
    @FXML
    private TextField postal_code;
    @FXML
    private TextField number;

    private void initialize() {
        mainSys = new InvoiceSystem();
    }

    @FXML
    private void save_customer(ActionEvent event)
    {
        Customer newCustomer = new Customer();
        newCustomer.setName(name.getText());
        newCustomer.setAddress(name.getText());
        newCustomer.setName(name.getText());
        newCustomer.setName(name.getText());
        newCustomer.setName(name.getText());
    }

    @FXML
    private void load_customer(ActionEvent event)
    {
        System.out.println("Halo");;
    }
}
