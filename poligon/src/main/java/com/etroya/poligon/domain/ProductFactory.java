package com.etroya.poligon.domain;

import com.etroya.poligon.domain.data.Drink;
import com.etroya.poligon.domain.data.Food;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Instant;
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

    private Path reportsFolder = Path.of(config.getString("reports.folder"));
    private Path dataFolder = Path.of(config.getString("data.folder"));
    private Path tempFolder = Path.of(config.getString("temp.folder"));

    private static final Logger logger = Logger.getLogger(ProductFactory.class.getName());


    public ProductFactory(Locale locale) {
        this(locale.toLanguageTag());
    }

    public ProductFactory(String languageTag) {
        changeLocale(languageTag);
        loadAllData();
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
            logger.log(Level.INFO, e.getMessage());
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
        Path productFile = reportsFolder.resolve(MessageFormat.format(config.getString("report.file"), product.getId()));
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(Files.newOutputStream(productFile, StandardOpenOption.CREATE, "UTF-8")))) {
            out.append(formatter.formatProduct(product) + System.lineSeparator());
            if (reviews.isEmpty()) {
                out.append(formatter.getText("no reviews") + System.lineSeparator());
            } else {
                out.append(reviews.stream()
                        .map(r -> formatter.formatReview(r) + System.lineSeparator())
                        .collect(Collectors.joining()));
            }
        }

//        for (Review review : reviews) {
//            txt.append(formatter.formatProduct(product));
//            txt.append("\n");
//        }
//        if (reviews.isEmpty()) {
//            txt.append(formatter.getText("no.reviews"));
//            txt.append('\n');
//        }
    }

    private ProductAbstract parseProduct(String text) {
        ProductAbstract product =  null;
        try {
            Object[] values = productFormat.parse(text);
            int id = Integer.parseInt((String) values[1]);
            String name = (String) values[2];
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble((String) values[3]));
            Rating rating = Rateable.convert(Integer.parseInt((String) values[4]));
            switch ((String) values[0]) {
                case "D":
                    product = new Drink(id, name, price, rating);
                    break;
                case "F":
                    LocalDate bestBefore = LocalDate.parse((String) values[5]);
                    product = new Food(id, name, price, rating, bestBefore);
            }
        } catch (ParseException | NumberFormatException | DateTimeParseException e) {
            logger.log(Level.WARNING, "Error parsing product " + text + e.getMessage());
        }
        return product;
    }

    private List<Review> loadReviews(ProductAbstract product) {
        List<Review> reviews = null;

        Path file = dataFolder.resolve(
                MessageFormat.format(config.getString("reviews.data.file"), product.getId())
        );
        if(Files.notExists(file)){
            reviews = new ArrayList<>();
        } else {
            try {
                reviews = Files.lines(file, Charset.forName("UTF-8"))
                        .map(text -> parseReview(text))
                        .filter(review -> review != null)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                logger.log(Level.WARNING, "Error loading reviews " + e.getMessage());
            }
        }
        return reviews;
    }

    private Review parseReview(String text) {
        Review review = null;
        try {
            Object[] values = reviewFormat.parse(text);
            review = new Review(Rateable.convert(Integer.parseInt((String) values[0])), (String)  values[1]);
        } catch (ParseException e) {
            logger.log(Level.WARNING, "Error parsing review " + text, e);
        }
        return review;
    }

    private void loadAllData(){
        try {
            products = Files.list(dataFolder)
                    .filter(file -> file.getFileName().toString().startsWith("product"))
                    .map(file -> loadProduct(file))
                    .filter(productAbstract -> productAbstract != null)
                    .collect(Collectors.toMap(productAbstract -> productAbstract,
                            productAbstract -> loadReviews(productAbstract)));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading data " + e.getMessage(), e);
        }
    }

    private ProductAbstract loadProduct(Path file){
        ProductAbstract product = null;
        try {
            product = parseProduct(Files.lines(dataFolder.resolve(file), Charset.forName("UTF-8")).findFirst().orElseThrow());
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error loading product.", e.getMessage());
        }
        return product;
    }

    public void dumpData(){
        try{
            if(Files.notExists(tempFolder)){
                Files.createDirectory(tempFolder);
            }
            Path tempFile = tempFolder.resolve(MessageFormat.format(config.getString("temp.file"), Instant.now()));

        } catch (IOException e){
            logger.log(Level.SEVERE, "Error dumpling the data " + e.getMessage(), e);

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
