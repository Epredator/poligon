package com.etroya.services;

/**
 * Created by mada on 22.01.2019.
 */
public class GetterGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hi, oh! I was injected by the getter";
    }
}
