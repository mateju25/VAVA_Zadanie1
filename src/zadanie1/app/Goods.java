package zadanie1.app;

public class Goods implements ItemForChoice {
    private String name;
    private String description;
    private double value;

    public Goods(String name, String description, double value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    //region Getters and Setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(double value) {
        this.value = value;
    }
    //endregion

}
