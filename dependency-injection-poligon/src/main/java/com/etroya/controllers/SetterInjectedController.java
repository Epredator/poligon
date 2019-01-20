package com.etroya.controllers;

import com.etroya.services.GreetingService;

/**
 * Created by mada on 17.01.2019.
 */
public class SetterInjectedController {
    private GreetingService greetingService;

    public String sayHello() {
        return greetingService.sayGreeting();
    }

    public void setGreetingService(GreetingService greetingService) {

        this.greetingService = greetingService;
    }

}
