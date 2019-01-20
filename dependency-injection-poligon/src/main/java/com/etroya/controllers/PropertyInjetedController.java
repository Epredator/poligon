package com.etroya.controllers;

import com.etroya.services.GreetingServiceImpl;

/**
 * Created by mada on 17.01.2019.
 */
public class PropertyInjetedController {

    public GreetingServiceImpl greetingService;
    
    String sayHello(){
        return getGreetingService().sayGreeting();
    }

    public GreetingServiceImpl getGreetingService(){
        return greetingService;
    }
}
