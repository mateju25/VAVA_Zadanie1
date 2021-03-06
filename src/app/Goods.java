package app;

public class Goods implements Cloneable {
    private String name = "";
    private String description = "";
    private double value = 0;

    public Goods(String name, String description, double value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

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

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
}
