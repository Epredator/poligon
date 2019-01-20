package com.etroya.controllers;

import org.springframework.stereotype.Controller;

/**
 * Created by mada on 17.01.2019.
 */
@Controller
public class MyController {

    public String hello(){
        System.out.println("Hello !");
        return null;
    }
}
