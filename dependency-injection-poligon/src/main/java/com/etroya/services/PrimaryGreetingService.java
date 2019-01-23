package com.etroya.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by mada on 23.01.2019.
 */
@Service
@Primary
public class PrimaryGreetingService implements GreetingService {
    public PrimaryGreetingService() {
    }

    @Override
    public String sayGreeting() {

        return "Hi, oh! from PrimaryGreetingService";
    }
}
