package zadanie1.app;

import java.util.ArrayList;
import java.util.List;

public final class InvoiceSystem {
    private static final InvoiceSystem INSTANCE = new InvoiceSystem();

    private final List<Customer> listOfCustomers = new ArrayList<>();
    private final List<Goods> listOfGoods = new ArrayList<>();
    private final List<Invoice> listOfInvoice = new ArrayList<>();
    private Invoice currInvoice = new Invoice();

    private InvoiceSystem() {}

    public static InvoiceSystem getInstance() {
        return INSTANCE;
    }

    public Invoice getCurrInvoice() {
        return currInvoice;
    }

    public void setNewInvoice() {this.currInvoice = new Invoice();}

    public List<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public List<Goods> getListOfGoods() {
        return listOfGoods;
    }

    public List<Invoice> getListOfInvoice() {
        return listOfInvoice;
    }
}
