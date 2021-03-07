package zadanie1.gui.controllers;

import zadanie1.app.InvoiceSystem;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class SampleController {
    protected static InvoiceSystem localSys = null;

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
