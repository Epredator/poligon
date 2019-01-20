package com.etroya.controllers;

import com.etroya.services.GreetingServiceImpl;

/**
 * Created by mada on 17.01.2019.
 */
public class PropertyInjectedController {

    public GreetingServiceImpl greetingService;

    public String sayHello() {
        return greetingService.sayGreeting();
    }

}
