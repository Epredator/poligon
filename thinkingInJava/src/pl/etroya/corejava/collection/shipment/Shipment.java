package pl.etroya.corejava.collection.shipment;

import pl.etroya.corejava.collection.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Shipment implements Iterable<Product> {
    private static final int LIGHT_VAN_MAX_WEIGHT = 20;
    private static final int PRODUCT_NOT_EXIST = -1;
    private final List<Product> products = new ArrayList<>();

    private List<Product> lightVanProducts;
    private List<Product> heayVanProducts;

    public void add(Product p) {
        products.add(p);
    }

    public void replace(Product oldP, Product newP) {
        final int index = products.indexOf(oldP);
        if (index != PRODUCT_NOT_EXIST) {
            products.set(index, newP);
        }
    }

    @Override
    public Iterator iterator() {
        return products.iterator();
    }

    public void prepare() {
        //sort list of products by weight
        products.sort(Product.BY_WEIGHT);

        // find the product index that needs the heavy van
        int splitPoint = findSplitPoint();

        //assign view of the product list for heavy and light vans
        lightVanProducts = products.subList(0, splitPoint); //represent light vans
        heayVanProducts = products.subList(splitPoint, products.size()); //represent heavy vans
    }

    private int findSplitPoint() {

        for (int i = 0; i < products.size(); i++) {
            final Product product = products.get(i);
            if (product.getWeight() > LIGHT_VAN_MAX_WEIGHT) {
                return i;
            }

        }
        return 0;
    }

    public List<Product> getLightVanProducts() {
        return lightVanProducts;
    }

    public List<Product> getHeavyVanProducts() {
        return heayVanProducts;
    }
}
