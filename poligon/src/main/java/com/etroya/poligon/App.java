package com.etroya.poligon;

import com.etroya.poligon.domain.Condition;
import com.etroya.poligon.domain.Product;
import com.etroya.poligon.domain.Rating;

import java.math.BigDecimal;

import static com.etroya.poligon.domain.Condition.HOT;
import static com.etroya.poligon.domain.Rating.*;

public class App {
    public static void main(String[] args) {
        Product p1 = new Product("Tea", BigDecimal.valueOf(1.99));
        Product p2 = new Product("Coffe", FOUR_STAR, BigDecimal.valueOf(1.99));
        Product p3 = new Product("Cake", FIVE_STAR, BigDecimal.valueOf(3.99));
        Product p4 = new Product();
        Product p5 = p3.applyRating(THREE_STAR);

        Product p = new Product("Books");
        Person jonny = new Person("jonny");
        Product tea = new Product("Tea", HOT);
        jonny.consume(tea.serve(HOT));
        System.out.println(p1.getId() + " " + p1.getName() + " " + p1.getPrice() + " " + p1.getDiscount(p1.getPrice())  + " " + p1.getRating().getStars());
        System.out.println(p2.getId() + " " + p2.getName() + " " + p2.getPrice() + " " + p2.getDiscount(p2.getPrice()) + " " + p2.getRating().getStars());
        System.out.println(p3.getId() + " " + p3.getName() + " " + p3.getPrice() + " " + p3.getDiscount(p3.getPrice()) + " " + p3.getRating().getStars());
        System.out.println(p4.getId() + " " + p4.getName() + " " + p4.getPrice() + " " + p4.getDiscount(p3.getPrice()) + " " + p4.getRating().getStars());
        System.out.println(p5.getId() + " " + p5.getName() + " " + p5.getPrice() + " " + p5.getDiscount(p3.getPrice()) + " " + p5.getRating().getStars());
    }
}
