package zadanie1.gui.controllers;

import zadanie1.app.Customer;
import zadanie1.app.ItemForChoice;

public abstract class LoadController {
    protected ItemForChoice chosedItem = null;

    public ItemForChoice getItem() {
        return chosedItem;
    }
}
