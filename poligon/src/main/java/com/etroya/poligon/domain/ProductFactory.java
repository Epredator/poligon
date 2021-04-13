package com.etroya.poligon.domain;

import com.etroya.poligon.domain.data.Drink;
import com.etroya.poligon.domain.data.Food;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductFactory {
    private ResourceBundle config = ResourceBundle.getBundle("com.etroya.poligon.domain.data.config");
    private MessageFormat reviewFormat = new MessageFormat(config.getString("review.data.format"));
    private MessageFormat productFormat = new MessageFormat(config.getString("product.data.format"));
    private Map<ProductAbstract, List<Review>> products = new HashMap<>();
    private static Map<String, ResourceFormatter> formatters
            = Map.of("en-GB", new ResourceFormatter(Locale.UK),
            "en-US", new ResourceFormatter(Locale.US),
            "ru-RU", new ResourceFormatter(Locale.CHINA));
    private ResourceFormatter formatter;

    private static final Logger logger = Logger.getLogger(ProductFactory.class.getName());


    public ProductFactory(Locale locale) {
        this(locale.toLanguageTag());
    }

    public ProductFactory(String languageTag) {
        changeLocale(languageTag);
    }

    public void changeLocale(String languageTag) {
        formatter = formatters.getOrDefault(languageTag, formatters.get("en-GB"));
    }

    public static Set<String> getSupportedLocales() {
        return formatters.keySet();
    }

    public ProductAbstract createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        ProductAbstract product = new Food(id, name, price, rating, bestBefore);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public ProductAbstract createProduct(int id, String name, BigDecimal price, Rating rating) {
        ProductAbstract product = new Drink(id, name, price, rating);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public ProductAbstract reviewProduct(ProductAbstract product, Rating rating, String comments) {
        return getProductAbstract(rating, comments, product);
    }

    public ProductAbstract reviewProduct(int productId, Rating rating, String comments) throws ProductFactoryException {
        ProductAbstract product = findProduct(productId);
        return getProductAbstract(rating, comments, product);
    }

    private ProductAbstract getProductAbstract(Rating rating, String comments, ProductAbstract product) {
        List<Review> reviews = products.get(product);
        products.remove(product, reviews);
        reviews.add(new Review(rating, comments));
        product = product.applyRating(
                Rateable.convert(
                        (int) Math.round(
                                reviews.stream()
                                        .mapToInt(r -> r.getRating().ordinal())
                                        .average()
                                        .orElse(0)
                        )
                )
        );
        products.put(product, reviews);
        return product;
    }

    public void printProductReport(int id) {
        try {
            printProductReport(findProduct(id));
        } catch (ProductFactoryException e) {
            e.printStackTrace();
        }
    }

//    public Map<String, String> getDiscount(){
//        return products.keySet()
//                .stream()
//                .collect(
//                        Collectors.groupingBy(
//                                product ->product.getDiscount().doubleValue(),
//                                discount ->formatter.moneyFormat.format(discount))
//
//                )
//    }

    public void printProductReport(Predicate<ProductAbstract> filter, Comparator<ProductAbstract> sorter) {
        StringBuilder txt = new StringBuilder();
        products.keySet()
                .stream()
                .sorted(sorter)
                .filter(filter)
                .forEach(p -> txt.append(formatter.formatProduct(p) + '\n'));

//
//        List<ProductAbstract> productList = new ArrayList<>(products.keySet());
//        productList.sort(sorter);
//
//        for (ProductAbstract product : productList) {
//            txt.append(formatter.formatProduct(product));
//            txt.append('\n');
//        }
        System.out.println(txt);
    }

    public void printProductReport(ProductAbstract product) {
        List<Review> reviews = products.get(product);
        Collections.sort(reviews);
        StringBuilder txt = new StringBuilder();
        txt.append(formatter.formatProduct(product));
        txt.append('\n');
        if (reviews.isEmpty()) {
            txt.append(formatter.getText("no reviews") + '\n');
        } else {
            txt.append(reviews.stream()
                    .map(r -> formatter.formatReview(r) + '\n')
                    .collect(Collectors.joining()));
        }

//        for (Review review : reviews) {
//            txt.append(formatter.formatProduct(product));
//            txt.append("\n");
//        }
//        if (reviews.isEmpty()) {
//            txt.append(formatter.getText("no.reviews"));
//            txt.append('\n');
//        }
        System.out.println(txt);
    }

    public void parseProduct(String text){
        try {
            Object[] values = productFormat.parse(text);
            int id = Integer.parseInt((String) values[1]);
            String name = (String) values[2];
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble((String) values[3]));
            Rating rating = Rateable.convert(Integer.parseInt((String) values[4]));
            switch ((String) values[0]){
                case "D":
                    createProduct(id, name, price, rating);
                    break;
                case "F":
                    LocalDate bestBefore = LocalDate.parse((String) values[5]);
                    createProduct(id, name, price, rating, bestBefore);
            }
        } catch (ParseException | NumberFormatException | DateTimeParseException e) {
            logger.log(Level.WARNING, "Error parsing product " + text + e.getMessage());
        }
    }

    public void parseReview(String text) {
        try {
            Object[] values = reviewFormat.parse(text);
            reviewProduct(Integer.parseInt((String)values[0]), Rateable.convert(Integer.parseInt((String)values[1])), (String)values[2]);
        } catch (ParseException | ProductFactoryException e) {
            logger.log(Level.WARNING,  "Error parsing review " + text, e);
        }
    }

    public ProductAbstract findProduct(int id) throws ProductFactoryException {
        return products.keySet()
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductFactoryException("Product with id: " + id + "not found"));
    }

    private static class ResourceFormatter {
        private Locale locale;
        private ResourceBundle resources;
        private DateTimeFormatter dateFormat;
        private NumberFormat moneyFormat;

        private ResourceFormatter(Locale locale) {
            this.locale = locale;
            resources = ResourceBundle.getBundle("com.etroya.poligon", locale);
            dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                    .localizedBy(locale);
            moneyFormat = NumberFormat.getCurrencyInstance(locale);
        }

        private String formatProduct(ProductAbstract product) {
            return MessageFormat.format(resources.getString("product"),
                    product.getName(),
                    moneyFormat.format(product.getPrice()),
                    product.getRating().getStars(),
                    dateFormat.format(product.getBestBefore()));
        }

        private String formatReview(Review review) {
            return MessageFormat.format(resources.getString("review"),
                    review.getRating().getStars(),
                    review.getComments());
        }

        private String getText(String key) {
            return resources.getString(key);
        }

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
