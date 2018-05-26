package pl.etroya.corejava.collection.shipment;

import pl.etroya.corejava.collection.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Shipment implements Iterable<Product> {
    private static final int LIGHT_VAN_MAX_WEIGHT = 20;
    private final List<Product> products = new ArrayList<>();

    public void add(Product p) {
        products.add(p);
    }

    public void prepare() {

    }

    public void replace(Product oldP, Product newP) {
    }

    @Override
    public Iterator iterator() {
        return products.iterator();
    }

    public List<Product> getHeavyVanProducts() {
        return null;
    }

    public List<Product> getLightVanProducts() {
        return null;
    }
}
