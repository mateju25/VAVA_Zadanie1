package zadanie1.gui.controllers;

import zadanie1.app.Invoice;
import zadanie1.app.InvoiceSystem;
import zadanie1.app.ViewInList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class LoadInvoiceController extends LoadController{
    @FXML
    private ListView<ViewInList> listOfItems;

    @FXML
    public void initialize() {
        listOfItems.setCellFactory(param -> new ListCell<ViewInList>() {
            @Override
            protected void updateItem(ViewInList item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setText(null);
                } else {
                    setText(item.getInfo());
                }
            }
        });

        listOfItems.getItems().clear();
        listOfItems.getItems().addAll(InvoiceSystem.getInstance().getListOfInvoice());
    }

    @FXML
    public void useItem() {
        chosedItem = (Invoice) listOfItems.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) listOfItems.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancel() {
        Stage stage = (Stage) listOfItems.getScene().getWindow();
        stage.close();
    }
}
