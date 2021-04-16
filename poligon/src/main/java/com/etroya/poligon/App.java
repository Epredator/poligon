package com.etroya.poligon;

import com.etroya.poligon.domain.GenericSample;
import com.etroya.poligon.domain.ProductAbstract;
import com.etroya.poligon.domain.ProductFactory;
import com.etroya.poligon.domain.ProductNameSorter;
import com.etroya.poligon.domain.data.Food;
import com.etroya.poligon.domain.data.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.etroya.poligon.domain.Condition.HOT;
import static com.etroya.poligon.domain.Rating.FIVE_STAR;
import static com.etroya.poligon.domain.Rating.FOUR_STAR;

public class App {

    public static void main(String[] args) {
        ProductFactory pf = ProductFactory.getInstance();
        AtomicInteger clientCount = new AtomicInteger();

        Callable<String> client = () -> {
            String clientId = "Client " + clientCount.incrementAndGet();
            String threadName = Thread.currentThread().getName();
            int productId = ThreadLocalRandom.current().nextInt(63) + 101;
            String languageTag = ProductFactory.getSupportedLocales()
                    .stream()
                    .skip(ThreadLocalRandom.current().nextInt(4))
                    .findFirst().get();

            StringBuilder log = new StringBuilder();
            log.append(clientId + " " + threadName + "\n-\tstart of log\t-\n");
            log.append(pf.getDiscount(languageTag)
                    .entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + "\t" + entry.getValue())
                    .collect(Collectors.joining("\n")));
            ProductAbstract product = pf.reviewProduct(productId, FOUR_STAR, "Yet another review");
            log.append((product != null) ? "\nProduct " + productId + " reviewed\n" : "\nProduct " + productId + " not reviewed");
            log.append(clientId + "  generated report for  " + productId + " product");
            log.append("\n-\tend of log\t-\n");
            return log.toString();
        };

        List<Callable<String>> clients = Stream.generate(() -> client).limit(5).collect(Collectors.toList());
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            List<Future<String>> results = executorService.invokeAll(clients);
            executorService.shutdown();
            results.stream().forEach(result ->
            {
                try {
                    System.out.println(result.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, "Error retriving client log", e);
                }
            });
        } catch (InterruptedException e) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, "Error invoking client", e);
        }

        Comparator<ProductAbstract> ratingSorter = (pa1, pa2) -> pa2.getRating().ordinal() - pa1.getRating().ordinal();
        Comparator<ProductAbstract> priceSorter = (pa1, pa2) -> pa2.getPrice().compareTo(pa1.getPrice());
//        pf.printProductReport(ratingSorter);
//        pf.printProductReport(priceSorter);
//        pf.printProductReport(ratingSorter.thenComparing(priceSorter));
//        pf.printProductReport((pa1, pa2) -> pa2.getRating().ordinal() - pa1.getRating().ordinal() );
//        pf.printProductReport((pa1, pa2) -> pa2.getPrice().compareTo(pa1.getPrice()));
//        Drink p1 = new Drink(102,"Tea", BigDecimal.valueOf(1.99), FOUR_STAR);

//        ProductAbstract p2 = new Drink(101, LocalDate.now().plusDays(2), "Coffe", BigDecimal.valueOf(0.11), FOUR_STAR);
        ProductAbstract p3 = new Food(102, LocalDate.now().plusDays(2), "Cake", BigDecimal.valueOf(3.99), FIVE_STAR);
        p3.getBestBefore();
        Product p4 = new Product();
        pf.dumpData();
        pf.restoreData();
//        Product p5 = p3.applyRating(THREE_STAR);
//        Drink drink = new Drink();
//        Product product = ProductFactory.createProduct(FOOD);

        Product p = new Product("Books");
        Person jonny = new Person("jonny");
        Product tea = new Product("Tea", HOT);
        jonny.consume(tea.serve(HOT));
//        drink.serve();

        GenericSample<Product> sample = new GenericSample<>();
        sample.setValue(new Product("Cola", BigDecimal.valueOf(2.11)));

        Product[] products = {
                new Product("Tea"),
                new Product("Coffee"),
                new Product("Cake"),
        };

        Arrays.sort(products, new ProductNameSorter());

//        System.out.println(p1.getId() + " " + p1.getName() + " " + p1.getPrice() + " " + p1.getDiscount(p1.getPrice())  + " " + p1.getRating().getStars());
//        System.out.println(p2.getId() + " " + p2.getName() + " " + p2.getPrice() + " " + p2.getDiscount(p2.getPrice()) + " " + p2.getRating().getStars());
//        System.out.println(p3.getId() + " " + p3.getName() + " " + p3.getPrice() + " " + p3.getDiscount(p3.getPrice()) + " " + p3.getRating().getStars());
//        System.out.println(p4.getId() + " " + p4.getName() + " " + p4.getPrice() + " " + p4.getDiscount(p3.getPrice()) + " " + p4.getRating().getStars());
//        System.out.println(p5.getId() + " " + p5.getName() + " " + p5.getPrice() + " " + p5.getDiscount(p3.getPrice()) + " " + p5.getRating().getStars());
        System.out.println(p3);

    }
}
