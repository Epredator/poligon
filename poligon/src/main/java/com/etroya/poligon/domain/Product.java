package com.etroya.poligon.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private static int maxId = 0;
    private final int id;
    private final String name;
    private final BigDecimal price;

    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);

    {
        id = ++maxId;
    }

    public Product(String name) {
        this.name = name;
        this.price = BigDecimal.ZERO;
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDiscount(final BigDecimal price) {
        return price.multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
    }
}
