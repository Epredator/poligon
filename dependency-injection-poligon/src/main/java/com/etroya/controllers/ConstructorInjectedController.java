package com.etroya.controllers;

import com.etroya.services.GreetingService;

/**
 * Created by mada on 20.01.2019.
 */
public class ConstructorInjectedController {
    private GreetingService greetingService;

    public ConstructorInjectedController(GreetingService greetingService){
        this.greetingService = greetingService;
    }

    public String sayHello(){
        return greetingService.sayGreeting();
    }
}
