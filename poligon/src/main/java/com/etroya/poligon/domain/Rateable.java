package com.etroya.poligon.domain;

@FunctionalInterface
public interface Rateable<T> {
    public static final Rating DEFAULT_RATE = Rating.NOT_RATED;

    public abstract T applyRating(Rating rating);

    public default T applyRating(int stars) {
        return null;
    }

    public default Rating getRating() {
        return DEFAULT_RATE;
    }

    public static Rating convert(int stars) {
        return (stars >= 0 && stars <= 5) ? Rating.values()[stars] : DEFAULT_RATE;

    }
}
