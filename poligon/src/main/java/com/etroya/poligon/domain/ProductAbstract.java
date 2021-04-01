package com.etroya.poligon.domain;

import com.etroya.poligon.domain.data.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class ProductAbstract {

    private static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);
    private int id;
    private String name;
    private BigDecimal price;
    private Rating rating;
    private LocalDate bestBefore;

    public ProductAbstract() {
        this(0, "no_name", BigDecimal.ZERO);
    }

    public ProductAbstract(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public ProductAbstract(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;

    }

    public ProductAbstract(int id, BigDecimal price, Rating rating, LocalDate bestBefore) {
        this.id = id;
        this.price = price;
        this.rating = rating;
        this.bestBefore = bestBefore;

    }

    public ProductAbstract(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.bestBefore = bestBefore;
    }

    public BigDecimal getDiscount() {
        return BigDecimal.valueOf(0, 99);
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

    public Rating getRating() {
        return rating;
    }

    public LocalDate getBestBefore() {
        return LocalDate.now();
    }

    public abstract Product applyRating(Rating newRating);
}
