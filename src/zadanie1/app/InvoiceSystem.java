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

    public boolean existsCustomer(Customer customer) {
        for (Customer item: this.listOfCustomers) {
            if (item.getName().equals(customer.getName()) &&
                    item.getAddress().equals(customer.getAddress()) &&
                    item.getNumber().equals(customer.getNumber()) &&
                    item.getTown().equals(customer.getTown()) &&
                    item.getPostal_code().equals(customer.getPostal_code()))
                return true;
        }
        return false;
    }

    public boolean existsGoods(Goods good) {
        for (Goods item: this.listOfGoods) {
            if (item.getName().equals(good.getName()) &&
                    item.getDescription().equals(good.getDescription()) &&
                    item.getValue() == good.getValue() )
                return true;
        }
        return false;
    }

}
