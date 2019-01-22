package com.etroya.services;

import org.springframework.stereotype.Service;

/**
 * Created by mada on 21.01.2019.
 */
@Service
public class ConstructorGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hi oh!, I was injected via the constructor!";
    }
}
