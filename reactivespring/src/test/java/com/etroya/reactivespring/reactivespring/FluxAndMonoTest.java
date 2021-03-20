package com.etroya.reactivespring.reactivespring;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxAndMonoTest {

    @Test
    void fluxTest() {
    Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive Spring")
//            .concatWith(Flux.error(new RuntimeException("Exception occured")))
            .concatWith(Flux.just("After error"))
            .log();
    stringFlux.subscribe(System.out::println,
            (e) -> System.err.println("Exception is: " + e),
            ()  -> System.out.println("Completed")
            );

    }
}
