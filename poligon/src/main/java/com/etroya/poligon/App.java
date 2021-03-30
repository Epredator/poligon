package com.etroya.poligon;

import com.etroya.poligon.domain.*;

import java.math.BigDecimal;
//import java.time.LocalDate;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static com.etroya.poligon.domain.Condition.HOT;
import static com.etroya.poligon.domain.ProductType.*;
import static com.etroya.poligon.domain.Rating.*;

public class App {
    public static void main(String[] args) {
        Drink p1 = new Drink(102,"Tea", BigDecimal.valueOf(1.99), FOUR_STAR);

//        ProductAbstract p2 = new Drink(101, LocalDate.now().plusDays(2), "Coffe", BigDecimal.valueOf(0.11), FOUR_STAR);
        ProductAbstract p3 = new Food(102, LocalDate.now().plusDays(2), "Cake", BigDecimal.valueOf(3.99), FIVE_STAR);
        p3.getBestBefore();
        Product p4 = new Product();
//        Product p5 = p3.applyRating(THREE_STAR);
//        Drink drink = new Drink();
//        Product product = ProductFactory.createProduct(FOOD);

        Product p = new Product("Books");
        Person jonny = new Person("jonny");
        Product tea = new Product("Tea", HOT);
        jonny.consume(tea.serve(HOT));
//        drink.serve();

        GenericSample<Product> sample = new GenericSample<>();
        sample.setValue(new Product("Cola", BigDecimal.valueOf(2.11)));

        Product[] products = {
                new Product("Tea"),
                new Product("Coffee"),
                new Product("Cake"),
        };

        Arrays.sort(products, new ProductNameSorter());

//        System.out.println(p1.getId() + " " + p1.getName() + " " + p1.getPrice() + " " + p1.getDiscount(p1.getPrice())  + " " + p1.getRating().getStars());
//        System.out.println(p2.getId() + " " + p2.getName() + " " + p2.getPrice() + " " + p2.getDiscount(p2.getPrice()) + " " + p2.getRating().getStars());
//        System.out.println(p3.getId() + " " + p3.getName() + " " + p3.getPrice() + " " + p3.getDiscount(p3.getPrice()) + " " + p3.getRating().getStars());
//        System.out.println(p4.getId() + " " + p4.getName() + " " + p4.getPrice() + " " + p4.getDiscount(p3.getPrice()) + " " + p4.getRating().getStars());
//        System.out.println(p5.getId() + " " + p5.getName() + " " + p5.getPrice() + " " + p5.getDiscount(p3.getPrice()) + " " + p5.getRating().getStars());
        System.out.println(p3);
    }
}
