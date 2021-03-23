package com.etroya.poligon.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private static int maxId = 0;
    private final int id;
    private final String name;
    private String caution;
    private Condition condition;
    private BigDecimal price;

    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);

    {
        id = ++maxId;
    }

    public Product(String name, Condition hot) {
        this.name = name;
        this.price = BigDecimal.ZERO;
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, Condition condition, BigDecimal price){
        this.name = name;
        this.condition = condition;
        this.price = price;
    }

    public Product(String name) {
        this.name = name;
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

    public Product serve(){
        switch (condition){
            case HOT:
                this.addCaution("Warning hot!");
                break;
            case WARM:
                this.addCaution("Warning warm!");
                break;
            case COLD:
                this.addCaution("Warning cold!");
        }
        return this;
    }

    private void addCaution(String s) {
        System.out.println(s);
    }
}
