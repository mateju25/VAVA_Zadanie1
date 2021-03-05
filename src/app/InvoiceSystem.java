package app;

import java.util.ArrayList;
import java.util.List;

public class InvoiceSystem {
    private List<Customer> listOfCustomers = null;
    private List<Goods> listOfGoods = null;
    private List<Invoice> listOfInvoice = null;
    private Invoice currInvoice = null;

    public InvoiceSystem() {
        this.listOfCustomers = new ArrayList<Customer>();
        this.listOfGoods = new ArrayList<Goods>();
        this.listOfInvoice = new ArrayList<Invoice>();
        this.currInvoice = new Invoice();
    }

    public Invoice getCurrInvoice() {
        return currInvoice;
    }

    public void setNewInvoice() {this.currInvoice = new Invoice();}

    public void setCurrInvoice(Invoice currInvoice) {
        this.currInvoice = currInvoice;
    }

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
