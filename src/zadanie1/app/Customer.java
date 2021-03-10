package zadanie1.app;

public class Customer implements ItemForChoice{
    private String name;
    private String address;
    private String number;
    private String town;
    private String postal_code;

    public Customer(String name, String address, String number, String town, String postal_code) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.town = town;
        this.postal_code = postal_code;
    }

    //region Getters and Setters
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
    //endregion
}
