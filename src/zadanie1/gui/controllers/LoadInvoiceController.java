package zadanie1.gui.controllers;

import zadanie1.app.Invoice;
import zadanie1.app.ViewInList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class LoadInvoiceController extends SampleController {
    @FXML
    private ListView<ViewInList> listOfItems;

    private Invoice chosedItem = null;

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
        listOfItems.getItems().addAll(localSys.getListOfInvoice());
    }

    @FXML
    public void useItem(ActionEvent actionEvent) {
        chosedItem = (Invoice) listOfItems.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) listOfItems.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) listOfItems.getScene().getWindow();
        stage.close();
    }

    public  Invoice getItem() {
        return chosedItem;
    }

}
