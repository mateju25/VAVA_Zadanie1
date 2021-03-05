package gui.controllers;

import app.*;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;

import java.util.Optional;

public class SampleController {
    protected static InvoiceSystem localSys = null;

    protected void initialize(ListView array) {
        array.setCellFactory(param -> new ListCell<Object>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setText(null);
                } else if (item instanceof ViewInList) {
                    setText(((ViewInList) item).getInfo());
                }
            }
        });

    }

    protected static String saveAs(String message) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Ulož ako...");
        dialog.setContentText(message);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            if (result.get().length() != 0)
                return result.get();
        }
        return null;
    }
}
