package com.etroya.poligon.domain.data;

import com.etroya.poligon.domain.ProductAbstract;
import com.etroya.poligon.domain.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Drink extends ProductAbstract {
    private LocalDate bestBefore;

    public Drink(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        super(id, name, price, rating, bestBefore);
    }

    public Drink(int id, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);
    }

    public Drink(int id, BigDecimal price, Rating newRating, LocalDate bestBefore) {
        super(id, price, newRating, bestBefore);
    }

    @Override
    public BigDecimal getDiscount() {
        LocalTime now = LocalTime.now();
        return (now.isAfter(LocalTime.of(17, 30)) && now.isBefore(LocalTime.of(18, 30)))
                ? super.getDiscount() : BigDecimal.ZERO;
    }

    @Override
    public ProductAbstract applyRating(Rating newRating) {
        return null;
    }

//    @Override
//    public Product applyRating(Rating newRating) {
//        return new Drink(getId(), getPrice(), newRating, bestBefore);
//    }


    @Override
    public String toString() {
        return "Drink{" +
                "bestBefore=" + bestBefore +
                '}';
    }
}
