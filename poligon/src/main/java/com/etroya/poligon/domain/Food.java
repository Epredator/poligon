package com.etroya.poligon.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Food extends ProductAbstract{

    private LocalDate bestBefore;

    public Food(){

    }

    public Food(int id, BigDecimal price, Rating newRating, LocalDate bestBefore) {
        super(id, price, newRating, bestBefore);
    }

    public Food(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        super(id, name, price, rating, bestBefore);
    }

    @Override
    public Product applyRating(Rating newRating) {
        return new Food(getId(), getPrice(), newRating, bestBefore);
    }

    public Food(int id, LocalDate bestBefore, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);
        this.bestBefore = bestBefore;
    }

    //    any other code
    public void serve(){
//        put food on a plate
    }

    @Override
    public String toString() {
        return "Food{" +
                "bestBefore=" + bestBefore +
                '}';
    }
}
