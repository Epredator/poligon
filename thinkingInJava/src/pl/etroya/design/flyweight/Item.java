package pl.etroya.design.flyweight;

public class Item { //instances of Item will be the Flyweights
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
