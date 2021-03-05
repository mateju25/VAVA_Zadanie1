package app;

public class Customer extends ViewInList implements SaveAsItem{
    private String name = null;
    private String address = null;
    private String number = null;
    private String town = null;
    private String postal_code = null;

    public Customer(String name, String address, String number, String town, String postal_code) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.town = town;
        this.postal_code = postal_code;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getTown() {
        return town;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getName() {
        return name;
    }
}
