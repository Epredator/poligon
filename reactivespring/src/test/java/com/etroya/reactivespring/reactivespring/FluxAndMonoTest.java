package com.etroya.reactivespring.reactivespring;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

    @Test
    void fluxTest() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive Spring")
//            .concatWith(Flux.error(new RuntimeException("Exception occured")))
                .concatWith(Flux.just("After error"))
                .log();
        stringFlux.subscribe(System.out::println,
                (e) -> System.err.println("Exception is: " + e),
                () -> System.out.println("Completed")
        );

    }

    @Test
    void shouldReturnFluxElementsWithoutError() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive Spring")
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring boot")
                .expectNext("Reactive Spring")
                .verifyComplete();

    }
}
