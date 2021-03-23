package com.etroya.poligon;

import com.etroya.poligon.domain.Condition;
import com.etroya.poligon.domain.Product;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {

        Product p = new Product("Books");
        Person jonny = new Person("jonny");
        Product tea = new Product("Tea", Condition.HOT);
        jonny.consume(tea.serve());
        System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice() + " " + p.getDiscount(p.getPrice()));
    }
}
