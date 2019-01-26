package com.etroya.services;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by mada on 23.01.2019.
 */
@Service
@Primary
@Profile("de")
public class PrimaryDenmarkGreetingService implements GreetingService {

    private GreetingRepository greetingRepository;

    public PrimaryDenmarkGreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public String sayGreeting() {

        return greetingRepository.getDutchGreeting();
    }
}
