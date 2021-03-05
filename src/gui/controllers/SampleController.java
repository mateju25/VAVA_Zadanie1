package gui.controllers;

import app.Customer;
import app.Goods;
import app.Invoice;
import app.InvoiceSystem;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class SampleController {
    protected static InvoiceSystem localSys = null;

    protected void initialize(ListView array) {
        array.setCellFactory(param -> new ListCell<Object>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setText(null);
                } else if (item instanceof Customer) {
                    setText(((Customer) item).getInfo());
                } else if (item instanceof Goods) {
                    setText(((Goods) item).getInfo());
                } else if (item instanceof Invoice) {
                    setText(((Invoice) item).getInfo());
                } else if (item instanceof Invoice.GoodsAndCount) {
                    setText(((Invoice.GoodsAndCount) item).getInfo());
                }
            }
        });

    }
}
