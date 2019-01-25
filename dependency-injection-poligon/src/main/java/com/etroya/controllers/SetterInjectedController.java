package com.etroya.controllers;

import com.etroya.services.GreetingService;
import com.etroya.services.GreetingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * Created by mada on 17.01.2019.
 */
@Controller
public class SetterInjectedController {

    private GreetingServiceImpl greetingService;

    public String sayHello() {
        return greetingService.sayGreeting();
    }

    @Autowired
    public void setGreetingService(GreetingServiceImpl greetingService) {
        this.greetingService = greetingService;
    }
}