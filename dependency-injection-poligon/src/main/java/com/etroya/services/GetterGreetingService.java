package com.etroya.services;

import org.springframework.stereotype.Service;

/**
 * Created by mada on 22.01.2019.
 */
@Service
public class GetterGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hi, oh! I was injected by the getter";
    }
}
