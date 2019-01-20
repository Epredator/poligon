package com.etroya;

import com.etroya.controllers.ConstructorInjectedController;
import com.etroya.services.GreetingServiceImpl;

import static org.junit.Assert.assertEquals;

/**
 * Created by mada on 20.01.2019.
 */
public class ConstructorInjectedControllerTest {
    private ConstructorInjectedController constructorInjectedController;

    public void setUp(){
        this.constructorInjectedController = new ConstructorInjectedController(new GreetingServiceImpl());
    }

    public void testGreeting(){
        assertEquals(GreetingServiceImpl.HELLO_GURUS, constructorInjectedController.sayHello());
    }
}
