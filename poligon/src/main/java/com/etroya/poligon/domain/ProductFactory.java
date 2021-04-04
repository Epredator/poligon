package com.etroya.poligon.domain;

import com.etroya.poligon.domain.data.Drink;
import com.etroya.poligon.domain.data.Food;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProductFactory {

    private ProductAbstract product;
    private Review review;
    private Locale locale;
    private ResourceBundle resources;
    private DateTimeFormatter dateFormat;
    private NumberFormat moneyFormat;

    public ProductFactory(Locale locale) {
        this.locale = locale;
        resources = ResourceBundle.getBundle("com.etroya.poligon", locale);
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                .localizedBy(locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);
    }

    public ProductAbstract createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        product = new Food(id, name, price, rating, bestBefore);
        return product;
    }

    public ProductAbstract createProduct(int id, String name, BigDecimal price, Rating rating) {
        product = new Drink(id, name, price, rating);
        return product;
    }

    public ProductAbstract reviewProduct(ProductAbstract product, Rating rating, String comments) {
        review = new Review(rating, comments);
        this.product = product.applyRating(rating);
        return this.product;
    }

    public void printProductReport() {
        StringBuilder txt = new StringBuilder();
        txt.append(MessageFormat.format(resources.getString("product"),
                product.getName(),
                moneyFormat.format(product.getPrice()),
                product.getRating().getStars(),
                dateFormat.format(product.getBestBefore())
        ));
        txt.append('\n');
        if (review != null) {
            txt.append(MessageFormat.format(resources.getString("review"),
                    review.getRating().getStars(),
                    review.getComments()
            ));
        } else {
            txt.append(resources.getString("no.reviews"));
        }
        txt.append('\n');
        System.out.println(txt);
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
