package pl.etroya.corejava.collection.shipment;

/*information about collections from
https://app.pluralsight.com/player?course=java-fundamentals-collections&author=richard-warburton&name=java-fundamentals-collections-m2&clip=3&mode=live
*/

import pl.etroya.corejava.collection.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionConcepts {

    public static void main(String[] args) {
        Product door = new Product("Wooden door", 35);
        Product floorPanel = new Product("Floor panel", 25);
        Product window = new Product("Glass window", 10);

        Collection<Product> products = new ArrayList<>();
        products.add(door);
        products.add(floorPanel);
        products.add(window);

        final Iterator<Product> productIterator = products.iterator();
        while (productIterator.hasNext()) {


            Product product = productIterator.next();
            if (product.getWeight() > 20) {
                System.out.println(product);
            } else {
                productIterator.remove();
            }

        }

        for (Product p : products) {
            System.out.println(p);

        }

        System.out.println(products);


    }
}
