package com.etroya.controllers;

import com.etroya.services.GreetingService;
import org.springframework.stereotype.Controller;

/**
 * Created by mada on 17.01.2019.
 */
@Controller
public class MyController {

    private GreetingService greetingService;

    public MyController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String hello(){
        System.out.println("Hello !");
        return greetingService.sayGreeting();
    }
}
