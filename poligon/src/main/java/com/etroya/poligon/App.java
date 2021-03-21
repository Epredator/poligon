package com.etroya.poligon;

import com.etroya.poligon.domain.Product;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {

        Product p = new Product();
//        p.setId(101);
//        p.setName("Tea");
        p.setPrice(BigDecimal.valueOf(1.99));


        System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice() + " " + p.getDiscount(p.getPrice()));
    }
}
