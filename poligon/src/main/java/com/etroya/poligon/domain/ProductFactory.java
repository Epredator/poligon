package com.etroya.poligon.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductFactory {
    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore){
        return new Food(id, name, price, rating, bestBefore);
    }
//    public static Product createProduct(ProductType productType) {
//        switch (productType) {
//            case FOOD:
//                return new Food();
//            case DRINK:
//                return new Drink();
//
//        }
//        return null;
//
//    }
}
