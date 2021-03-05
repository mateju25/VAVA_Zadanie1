package app;

public class Goods extends ViewInList implements Cloneable {
    private String name = "";
    private String description = "";
    private int value = 0;

    public Goods(String name, String description, int value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }

    public String getInfo() {
        return String.format("%-20s, %-60s, %-10s", this.name, this.description, String.valueOf(this.value));
    }
}
