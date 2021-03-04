package app;

import java.util.ArrayList;
import java.util.List;

public class InvoiceSystem {
    private List<Customer> listOfCustomers = null;

    public InvoiceSystem() {
        this.listOfCustomers = new ArrayList<Customer>();
    }

    public List<Customer> getListOfCustomers() {
        return listOfCustomers;
    }
}
